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
    private static Opinion opinionRegistrada = null;
    private static String contenidoOpinadoInfo = "";

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n===(H5). GESTIONAR OPINIONES ===");
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
            System.out.println("\n[RESTRICCIÓN]: No existen contenidos registrados. Registre primero un contenido en el Módulo H2.");
            return;
        }

        MenuContenido.mostrarResumenContenidos();

        Contenido tempContenido = new Contenido();
        String tituloSeleccionado = null;
        boolean valido;

        do {
            System.out.print("Ingrese el ID del contenido sobre el que desea opinar: ");
            String idContTxt = sc.nextLine();
            valido = Validaciones.validarNumeros(idContTxt) && tempContenido.setIdContenido(Integer.parseInt(idContTxt));
            if (valido) {
                tituloSeleccionado = MenuContenido.obtenerTituloPorId(tempContenido.getIdContenido());
                if (tituloSeleccionado == null) {
                    System.out.println("[ERROR]: No existe ningún contenido con ese ID en la lista.");
                    valido = false;
                }
            } else {
                System.out.println("[ERROR]: ID inválido.");
            }
        } while (!valido);

        Opinion nueva = new Opinion();

        do {
            System.out.print("Ingrese su comentario sobre '" + tituloSeleccionado + "': ");
            valido = nueva.setComentario(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: El comentario no puede estar vacío.");
        } while (!valido);

        nueva.setIdOpinion(contadorIdOpinion++);
        nueva.setFechaRegistro(obtenerFechaActual());

        opinionRegistrada = nueva;
        contenidoOpinadoInfo = tituloSeleccionado;

        System.out.println("\n[OPINIÓN REGISTRADA]: Sobre -> " + contenidoOpinadoInfo);
        System.out.println(" -> " + opinionRegistrada.toString());
    }

    private static void consultarOpinion() {
        if (opinionRegistrada == null) {
            System.out.println("\n[RESTRICCIÓN]: No existen opiniones registradas todavía.");
            return;
        }
        System.out.println("\n--- OPINIÓN REGISTRADA (H5.1) ---");
        System.out.println(" Contenido: " + contenidoOpinadoInfo + " | " + opinionRegistrada.toString());
    }

    private static void editarOpinion(Scanner sc) {
        if (opinionRegistrada == null) {
            System.out.println("\n[RESTRICCIÓN]: No existen opiniones registradas para editar.");
            return;
        }

        System.out.println("\n--- OPINIÓN DISPONIBLE ---");
        System.out.println(" [ID: " + opinionRegistrada.getIdOpinion() + "] " + contenidoOpinadoInfo
                + " | Comentario actual: \"" + opinionRegistrada.getComentario() + "\"");

        Opinion temp = new Opinion();
        boolean valido;

        do {
            System.out.print("Ingrese el ID de la opinión a editar: ");
            String idTxt = sc.nextLine();
            valido = Validaciones.validarNumeros(idTxt) && temp.setIdOpinion(Integer.parseInt(idTxt));
            if (!valido) System.out.println("[ERROR]: ID inválido.");
        } while (!valido);

        if (opinionRegistrada.getIdOpinion() != temp.getIdOpinion()) {
            System.out.println("[ERROR]: El ID ingresado no coincide con la opinión mostrada.");
            return;
        }

        do {
            System.out.print("Ingrese nuevo comentario: ");
            valido = opinionRegistrada.setComentario(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: El comentario no puede estar vacío.");
        } while (!valido);

        opinionRegistrada.setFechaEdicion(obtenerFechaActual());
        System.out.println("\n[EDICIÓN EXITOSA]: " + opinionRegistrada.toString());
    }

    private static void eliminarOpinion(Scanner sc) {
        if (opinionRegistrada == null) {
            System.out.println("\n[RESTRICCIÓN]: No existen opiniones registradas para eliminar.");
            return;
        }

        System.out.println("\n--- OPINIÓN DISPONIBLE ---");
        System.out.println(" [ID: " + opinionRegistrada.getIdOpinion() + "] " + contenidoOpinadoInfo
                + " | Comentario: \"" + opinionRegistrada.getComentario() + "\"");

        Opinion temp = new Opinion();
        boolean valido;

        do {
            System.out.print("Ingrese el ID de la opinión a eliminar: ");
            String idTxt = sc.nextLine();
            valido = Validaciones.validarNumeros(idTxt) && temp.setIdOpinion(Integer.parseInt(idTxt));
            if (!valido) System.out.println("[ERROR]: ID inválido.");
        } while (!valido);

        if (opinionRegistrada.getIdOpinion() == temp.getIdOpinion()) {
            if (MenuPrincipal.esAdmin) {
                GestorContenido gestor = new GestorContenido();
                gestor.eliminarOpinion();
            }
            opinionRegistrada = null;
            contenidoOpinadoInfo = "";
            System.out.println("[SISTEMA]: Opinión eliminada correctamente.");
        } else {
            System.out.println("[ERROR]: El ID ingresado no coincide con la opinión mostrada.");
        }
    }

    private static String obtenerFechaActual() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
}