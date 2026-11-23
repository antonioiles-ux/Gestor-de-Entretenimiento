package ec.edu.uce.gestor.Consola;

import ec.edu.uce.gestor.Util.Validaciones;
import ec.edu.uce.gestor.dominio.Contenido;
import ec.edu.uce.gestor.dominio.GestorContenido;
import ec.edu.uce.gestor.dominio.Opinion;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuOpinion {

    private static int contadorIdOpinion = 1;

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n=== GESTIONAR OPINIONES ===");
            System.out.println("1. Consultar Opiniones");
            System.out.println("2. Escribir Opinión");
            System.out.println("3. Editar Opinión");
            System.out.println("4. Eliminar Opinión");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            String entrada = sc.nextLine();
            if (!Validaciones.validarNumeros(entrada)) {
                System.out.println("[ERROR]: Ingrese solo números.");
                continue;
            }
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1:
                    consultarOpinion();
                    break;

                case 2:
                    if (MenuPrincipal.esAdmin) {
                        System.out.println("\n[AVISO DE ROL]: El Administrador solo consulta o elimina opiniones (moderación).");
                    } else {
                        escribirOpinion(sc);
                    }
                    break;

                case 3:
                    if (MenuPrincipal.esAdmin) {
                        System.out.println("\n[AVISO DE ROL]: El Administrador no edita reseñas de usuarios; solo puede eliminarlas si son inapropiadas.");
                    } else {
                        editarOpinion(sc);
                    }
                    break;

                case 4:
                    eliminarOpinion(sc);
                    break;

                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("[ERROR]: Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static void escribirOpinion(Scanner sc) {
        if (!MenuContenido.hayContenidoRegistrado()) {
            System.out.println("\n[RESTRICCIÓN]: No existen contenidos registrados. Registre primero un contenido.");
            return;
        }

        MenuContenido.mostrarResumenContenidos();

        Contenido contenidoSeleccionado = null;
        boolean valido;

        do {
            System.out.print("Ingrese el ID del contenido sobre el que desea opinar: ");
            String idContTxt = sc.nextLine();
            if (Validaciones.validarNumeros(idContTxt) && Integer.parseInt(idContTxt) > 0) {
                contenidoSeleccionado = MenuContenido.obtenerContenidoPorId(Integer.parseInt(idContTxt));
                if (contenidoSeleccionado == null) {
                    System.out.println("[ERROR]: No existe ningún contenido con ese ID en la lista.");
                    valido = false;
                } else {
                    valido = true;
                }
            } else {
                System.out.println("[ERROR]: ID inválido.");
                valido = false;
            }
        } while (!valido);

        Opinion nueva = new Opinion();

        do {
            System.out.print("Ingrese su comentario sobre '" + contenidoSeleccionado.getTitulo() + "': ");
            String comentarioTxt = sc.nextLine();
            valido = Validaciones.validarLetras(comentarioTxt) && nueva.setComentario(comentarioTxt);
            if (!valido) System.out.println("[ERROR]: El comentario solo puede contener letras y espacios.");
        } while (!valido);

        nueva.setIdOpinion(contadorIdOpinion);
        nueva.setFechaRegistro(obtenerFechaActual());
        nueva.setAutor(MenuPrincipal.usuarioActivo); // ASOCIACIÓN

        if (contenidoSeleccionado.agregarOpinion(nueva)) { // COMPOSICIÓN
            contadorIdOpinion++;
            System.out.println("\n[OPINIÓN REGISTRADA]: Sobre -> " + contenidoSeleccionado.getTitulo());
            System.out.println(" -> " + nueva.toString());
        } else {
            System.out.println("\n[ERROR]: Ya publicaste exactamente ese mismo comentario en este contenido.");
        }
    }

    private static boolean consultarOpinion() {
        boolean hayOpiniones = false;
        System.out.println("\n--- OPINIONES REGISTRADAS ---");
        for (Contenido c : MenuPrincipal.gestorGlobal.listarContenidos()) {
            for (Opinion op : c.listarOpiniones()) {
                String autor = (op.getAutor() != null) ? op.getAutor().getNombreCompleto() : "Anónimo";
                System.out.println(" Contenido: " + c.getTitulo() + " | Autor: " + autor + " | " + op.toString());
                hayOpiniones = true;
            }
        }
        if (!hayOpiniones) {
            System.out.println("[RESTRICCIÓN]: No existen opiniones registradas todavía.");
        }
        return hayOpiniones;
    }

    private static void editarOpinion(Scanner sc) {
        if (!consultarOpinion()) {
            return;
        }

        int idBuscar = 0;
        boolean valido;

        do {
            System.out.print("Ingrese el ID de la opinión a editar: ");
            String idTxt = sc.nextLine();
            if (Validaciones.validarNumeros(idTxt) && Integer.parseInt(idTxt) > 0) {
                idBuscar = Integer.parseInt(idTxt);
                valido = true;
            } else {
                System.out.println("[ERROR]: ID inválido.");
                valido = false;
            }
        } while (!valido);

        for (Contenido c : MenuPrincipal.gestorGlobal.listarContenidos()) {
            Opinion op = c.buscarOpinion(idBuscar);
            if (op != null) {
                if (op.getAutor() != null && !op.getAutor().equals(MenuPrincipal.usuarioActivo)) {
                    System.out.println("[ACCESO DENEGADO]: Solo el autor puede editar esta opinión.");
                    return;
                }
                do {
                    System.out.print("Ingrese nuevo comentario: ");
                    String nuevoComentario = sc.nextLine();
                    valido = Validaciones.validarLetras(nuevoComentario)
                            && c.editarOpinion(idBuscar, nuevoComentario, obtenerFechaActual());
                    if (!valido) System.out.println("[ERROR]: El comentario solo puede contener letras y espacios.");
                } while (!valido);

                System.out.println("\n[EDICIÓN EXITOSA]: " + op.toString());
                return;
            }
        }
        System.out.println("[ERROR]: El ID ingresado no coincide con ninguna opinión.");
    }

    private static void eliminarOpinion(Scanner sc) {
        if (!consultarOpinion()) {
            return;
        }

        int idBuscar = 0;
        boolean valido;

        do {
            System.out.print("Ingrese el ID de la opinión a eliminar: ");
            String idTxt = sc.nextLine();
            if (Validaciones.validarNumeros(idTxt) && Integer.parseInt(idTxt) > 0) {
                idBuscar = Integer.parseInt(idTxt);
                valido = true;
            } else {
                System.out.println("[ERROR]: ID inválido.");
                valido = false;
            }
        } while (!valido);

        for (Contenido c : MenuPrincipal.gestorGlobal.listarContenidos()) {
            Opinion op = c.buscarOpinion(idBuscar);
            if (op != null) {
                if (!MenuPrincipal.esAdmin && op.getAutor() != null
                        && !op.getAutor().equals(MenuPrincipal.usuarioActivo)) {
                    System.out.println("[ACCESO DENEGADO]: No puedes eliminar opiniones de otro usuario.");
                    return;
                }
                if (MenuPrincipal.esAdmin) {
                    MenuPrincipal.gestorGlobal.eliminarOpinion(op, "Moderación administrativa");
                }
                c.eliminarOpinion(idBuscar);
                System.out.println("[SISTEMA]: Opinión eliminada correctamente.");
                return;
            }
        }
        System.out.println("[ERROR]: El ID ingresado no coincide con ninguna opinión.");
    }

    private static String obtenerFechaActual() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
}