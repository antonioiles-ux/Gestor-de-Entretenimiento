package ec.edu.uce.gestor.Consola;

import ec.edu.uce.gestor.Util.Validaciones;
import ec.edu.uce.gestor.dominio.Valoracion;
import ec.edu.uce.gestor.dominio.Contenido;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuValoracion {

    private static int contadorIdValoracion = 1;

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n===(H4). GESTIONAR VALORACIONES ===");
            System.out.println("1. Crear Valoración");
            System.out.println("2. Consultar Valoración");
            System.out.println("3. Eliminar Valoración");
            System.out.println("4. Actualizar Valoración");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            String entrada = sc.nextLine();
            if (!Validaciones.validarNumeros(entrada)) {
                System.out.println("[ERROR]: Ingrese solo números.");
                continue;
            }
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1: crearValoracion(sc); break;
                case 2: consultarValoracion(); break;
                case 3: eliminarValoracion(sc); break;
                case 4: actualizarValoracion(sc); break;
                case 0: System.out.println("Regresando al menú principal..."); break;
                default: System.out.println("[ERROR]: Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static void crearValoracion(Scanner sc) {
        if (!MenuContenido.hayContenidoRegistrado()) {
            System.out.println("\n[RESTRICCIÓN]: No existen contenidos registrados. Registre primero un contenido en el Módulo H2.");
            return;
        }

        MenuContenido.mostrarResumenContenidos();

        Contenido contenidoSeleccionado = null;
        boolean valido;

        do {
            System.out.print("Ingrese el ID del contenido que desea valorar: ");
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

        Valoracion nueva = new Valoracion();

        do {
            System.out.print("Ingrese calificación para '" + contenidoSeleccionado.getTitulo() + "' (1 al 5): ");
            String calTxt = sc.nextLine();
            valido = Validaciones.validarCalificacion(calTxt) && nueva.setCalificacion(Integer.parseInt(calTxt));
            if (!valido) System.out.println("[ERROR]: Calificación inválida. Debe ser un dígito del 1 al 5.");
        } while (!valido);

        nueva.setIdValoracion(contadorIdValoracion);
        nueva.setFechaValoracion(obtenerFechaActual());
        nueva.setAutor(MenuPrincipal.usuarioActivo); // ASOCIACIÓN

        if (contenidoSeleccionado.agregarValoracion(nueva)) { // COMPOSICIÓN
            contadorIdValoracion++;
            System.out.println("\n[VALORACIÓN CREADA]: Sobre -> " + contenidoSeleccionado.getTitulo());
            System.out.println(" -> " + nueva.toString());
            System.out.printf(" -> Promedio actual: %.1f/5.0%n", contenidoSeleccionado.calcularPromedioValoraciones());
        } else {
            System.out.println("\n[ERROR]: Ya has valorado este contenido anteriormente (usa la opción 4 para actualizar).");
        }
    }

    private static boolean consultarValoracion() {
        boolean hayValoraciones = false;
        System.out.println("\n--- VALORACIONES REGISTRADAS ---");
        for (Contenido c : MenuPrincipal.gestorGlobal.listarContenidos()) {
            for (Valoracion v : c.listarValoraciones()) {
                String autor = (v.getAutor() != null) ? v.getAutor().getNombreCompleto() : "Anónimo";
                System.out.println(" Contenido: " + c.getTitulo() + " | Autor: " + autor + " | " + v.toString());
                hayValoraciones = true;
            }
        }
        if (!hayValoraciones) {
            System.out.println("[RESTRICCIÓN]: No existen valoraciones registradas todavía.");
        }
        return hayValoraciones;
    }

    private static void actualizarValoracion(Scanner sc) {
        if (!consultarValoracion()) {
            return;
        }

        int idBuscar = 0;
        boolean valido;

        do {
            System.out.print("Ingrese el ID de la valoración a modificar: ");
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
            Valoracion val = c.buscarValoracion(idBuscar);
            if (val != null) {
                if (val.getAutor() != null && !val.getAutor().equals(MenuPrincipal.usuarioActivo)) {
                    System.out.println("[ACCESO DENEGADO]: Solo el autor puede modificar esta valoración.");
                    return;
                }
                do {
                    System.out.print("Ingrese nueva calificación (1 al 5): ");
                    String calTxt = sc.nextLine();
                    valido = Validaciones.validarCalificacion(calTxt)
                            && c.editarValoracion(idBuscar, Integer.parseInt(calTxt), obtenerFechaActual());
                    if (!valido) System.out.println("[ERROR]: Calificación inválida (1 al 5).");
                } while (!valido);

                System.out.println("\n[ACTUALIZACIÓN EXITOSA]: " + val.toString());
                return;
            }
        }
        System.out.println("[ERROR]: El ID ingresado no coincide con ninguna valoración.");
    }

    private static void eliminarValoracion(Scanner sc) {
        if (!consultarValoracion()) {
            return;
        }

        int idBuscar = 0;
        boolean valido;

        do {
            System.out.print("Ingrese el ID de la valoración a eliminar: ");
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
            Valoracion val = c.buscarValoracion(idBuscar);
            if (val != null) {
                if (val.getAutor() != null && !val.getAutor().equals(MenuPrincipal.usuarioActivo)) {
                    System.out.println("[ACCESO DENEGADO]: Solo el autor puede eliminar esta valoración.");
                    return;
                }
                c.eliminarValoracion(idBuscar);
                System.out.println("[SISTEMA]: Valoración eliminada correctamente.");
                return;
            }
        }
        System.out.println("[ERROR]: El ID ingresado no coincide con ninguna valoración.");
    }

    private static String obtenerFechaActual() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
}