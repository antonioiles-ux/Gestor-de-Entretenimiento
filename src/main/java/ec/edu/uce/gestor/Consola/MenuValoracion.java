package ec.edu.uce.gestor.Consola;

import ec.edu.uce.gestor.Util.Validaciones;
import ec.edu.uce.gestor.dominio.Contenido;
import ec.edu.uce.gestor.dominio.Valoracion;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuValoracion {

    private static int contadorIdValoracion = 1;
    private static Valoracion valoracionRegistrada = null;
    private static String contenidoValoradoInfo = "";

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
        // Restricción: debe existir al menos un contenido registrado
        if (!MenuContenido.hayContenidoRegistrado()) {
            System.out.println("\n[RESTRICCIÓN]: No existen contenidos registrados. Registre primero un contenido en el Módulo H2.");
            return;
        }

        // Mostramos los contenidos disponibles con su ID
        MenuContenido.mostrarResumenContenidos();

        Contenido tempContenido = new Contenido();
        String tituloSeleccionado = null;
        boolean valido;

        do {
            System.out.print("Ingrese el ID del contenido que desea valorar: ");
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

        Valoracion nueva = new Valoracion();

        do {
            System.out.print("Ingrese calificación para '" + tituloSeleccionado + "' (1 al 5): ");
            String calTxt = sc.nextLine();
            valido = Validaciones.validarCalificacion(calTxt) && nueva.setCalificacion(Integer.parseInt(calTxt));
            if (!valido) System.out.println("[ERROR]: Calificación inválida. Debe ser un dígito del 1 al 5.");
        } while (!valido);

        nueva.setIdValoracion(contadorIdValoracion++);
        nueva.setFechaValoracion(obtenerFechaActual());

        valoracionRegistrada = nueva;
        contenidoValoradoInfo = tituloSeleccionado;

        System.out.println("\n[VALORACIÓN CREADA]: Sobre -> " + contenidoValoradoInfo);
        System.out.println(" -> " + valoracionRegistrada.toString());
    }

    private static void consultarValoracion() {
        if (valoracionRegistrada == null) {
            System.out.println("\n[RESTRICCIÓN]: No existen valoraciones registradas todavía.");
            return;
        }
        System.out.println("\n--- VALORACIÓN REGISTRADA (H4.2) ---");
        System.out.println(" Contenido: " + contenidoValoradoInfo + " | " + valoracionRegistrada.toString());
    }

    private static void actualizarValoracion(Scanner sc) {
        if (valoracionRegistrada == null) {
            System.out.println("\n[RESTRICCIÓN]: No existen valoraciones registradas para actualizar.");
            return;
        }

        // Mostramos la valoración con su ID antes de pedirlo
        System.out.println("\n--- VALORACIÓN DISPONIBLE ---");
        System.out.println(" [ID: " + valoracionRegistrada.getIdValoracion() + "] " + contenidoValoradoInfo
                + " | Calificación actual: " + valoracionRegistrada.getCalificacion());

        Valoracion temp = new Valoracion();
        boolean valido;

        do {
            System.out.print("Ingrese el ID de la valoración a modificar: ");
            String idTxt = sc.nextLine();
            valido = Validaciones.validarNumeros(idTxt) && temp.setIdValoracion(Integer.parseInt(idTxt));
            if (!valido) System.out.println("[ERROR]: ID inválido.");
        } while (!valido);

        if (valoracionRegistrada.getIdValoracion() != temp.getIdValoracion()) {
            System.out.println("[ERROR]: El ID ingresado no coincide con la valoración mostrada.");
            return;
        }

        do {
            System.out.print("Ingrese nueva calificación (1 al 5): ");
            String calTxt = sc.nextLine();
            valido = Validaciones.validarCalificacion(calTxt) && valoracionRegistrada.setCalificacion(Integer.parseInt(calTxt));
            if (!valido) System.out.println("[ERROR]: Calificación inválida (1 al 5).");
        } while (!valido);

        valoracionRegistrada.setFechaValoracion(obtenerFechaActual());
        System.out.println("\n[ACTUALIZACIÓN EXITOSA]: " + valoracionRegistrada.toString());
    }

    private static void eliminarValoracion(Scanner sc) {
        if (valoracionRegistrada == null) {
            System.out.println("\n[RESTRICCIÓN]: No existen valoraciones registradas para eliminar.");
            return;
        }

        System.out.println("\n--- VALORACIÓN DISPONIBLE ---");
        System.out.println(" [ID: " + valoracionRegistrada.getIdValoracion() + "] " + contenidoValoradoInfo
                + " | Calificación: " + valoracionRegistrada.getCalificacion());

        Valoracion temp = new Valoracion();
        boolean valido;

        do {
            System.out.print("Ingrese el ID de la valoración a eliminar: ");
            String idTxt = sc.nextLine();
            valido = Validaciones.validarNumeros(idTxt) && temp.setIdValoracion(Integer.parseInt(idTxt));
            if (!valido) System.out.println("[ERROR]: ID inválido.");
        } while (!valido);

        if (valoracionRegistrada.getIdValoracion() == temp.getIdValoracion()) {
            valoracionRegistrada = null;
            contenidoValoradoInfo = "";
            System.out.println("[SISTEMA]: Valoración eliminada correctamente.");
        } else {
            System.out.println("[ERROR]: El ID ingresado no coincide con la valoración mostrada.");
        }
    }

    private static String obtenerFechaActual() {
        return new SimpleDateFormat("dd/MM/yyyy").format(new Date());
    }
}