package ec.edu.uce.gestor.Consola;

import ec.edu.uce.gestor.Util.Validaciones;
import ec.edu.uce.gestor.dominio.SolicitudConsumo;
import java.util.Date;
import java.util.Scanner;

public class MenuSolicitud {

    private static int contadorIdSolicitud = 1;
    private static SolicitudConsumo solicitudRegistrada = null;

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n===(H3). GESTIONAR SOLICITUD DE CONSUMO ===");
            System.out.println("1. Crear Solicitud");
            System.out.println("2. Consultar Solicitud (por Tipo de Contenido)");
            System.out.println("3. Eliminar Solicitud");
            System.out.println("4. Actualizar Solicitud");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            String entrada = sc.nextLine();
            if (!Validaciones.validarNumeros(entrada)) {
                System.out.println("[ERROR]: Ingrese solo números.");
                continue;
            }
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1: crearSolicitud(sc); break;
                case 2: consultarSolicitud(sc); break;
                case 3: eliminarSolicitud(sc); break;
                case 4: actualizarSolicitud(sc); break;
                case 0: System.out.println("Regresando al menú principal..."); break;
                default: System.out.println("[ERROR]: Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static void crearSolicitud(Scanner sc) {
        SolicitudConsumo nueva = new SolicitudConsumo();
        boolean valido;

        System.out.println("\n--- CREAR SOLICITUD (H3.4) ---");

        do {
            System.out.print("Ingrese tipo de contenido (solo letras, ej. Pelicula, Libro): ");
            valido = nueva.setTipoContenido(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Tipo de contenido inválido (solo letras y espacios).");
        } while (!valido);

        do {
            System.out.print("Ingrese estado inicial (solo letras, ej. Pendiente): ");
            valido = nueva.setEstado(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Estado inválido (solo letras y espacios).");
        } while (!valido);

        nueva.setIdSolicitud(contadorIdSolicitud++);
        nueva.setFechaSolicitud(new Date());

        solicitudRegistrada = nueva;
        System.out.println("\n[SOLICITUD CREADA]:");
        System.out.println(" -> ID: " + solicitudRegistrada.getIdSolicitud()
                + " | Tipo: " + solicitudRegistrada.getTipoContenido()
                + " | Estado: " + solicitudRegistrada.getEstado()
                + " | Fecha: " + solicitudRegistrada.getFechaSolicitud());
    }

    private static void consultarSolicitud(Scanner sc) {
        if (solicitudRegistrada == null) {
            System.out.println("\n[RESTRICCIÓN]: No existen solicitudes registradas todavía.");
            return;
        }

        SolicitudConsumo temp = new SolicitudConsumo();
        boolean valido;

        do {
            System.out.print("Ingrese el nombre del tipo de contenido a consultar (ej. Pelicula): ");
            valido = temp.setTipoContenido(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Nombre inválido (solo letras y espacios).");
        } while (!valido);

        if (solicitudRegistrada.getTipoContenido().equalsIgnoreCase(temp.getTipoContenido().trim())) {
            System.out.println("\n--- SOLICITUD ENCONTRADA (H3.2) ---");
            System.out.println(" -> ID: " + solicitudRegistrada.getIdSolicitud()
                    + " | Tipo: " + solicitudRegistrada.getTipoContenido()
                    + " | Estado: " + solicitudRegistrada.getEstado()
                    + " | Fecha: " + solicitudRegistrada.getFechaSolicitud());
        } else {
            System.out.println("[INFO]: No se encontró ninguna solicitud para '" + temp.getTipoContenido() + "'.");
        }
    }

    private static void actualizarSolicitud(Scanner sc) {
        if (solicitudRegistrada == null) {
            System.out.println("\n[RESTRICCIÓN]: No existen solicitudes registradas para actualizar.");
            return;
        }

        // Mostramos la solicitud con su ID para facilitar la modificación
        System.out.println("\n--- SOLICITUD DISPONIBLE ---");
        System.out.println(" [ID: " + solicitudRegistrada.getIdSolicitud() + "] Tipo: "
                + solicitudRegistrada.getTipoContenido() + " | Estado actual: " + solicitudRegistrada.getEstado());

        SolicitudConsumo temp = new SolicitudConsumo();
        boolean valido;

        do {
            System.out.print("Ingrese el ID de la solicitud a modificar: ");
            String idTxt = sc.nextLine();
            valido = Validaciones.validarNumeros(idTxt) && temp.setIdSolicitud(Integer.parseInt(idTxt));
            if (!valido) System.out.println("[ERROR]: ID inválido.");
        } while (!valido);

        if (solicitudRegistrada.getIdSolicitud() != temp.getIdSolicitud()) {
            System.out.println("[ERROR]: El ID ingresado no coincide con la solicitud mostrada.");
            return;
        }

        do {
            System.out.print("Ingrese nuevo estado (solo letras, ej. Consumido, Aprobada): ");
            valido = solicitudRegistrada.setEstado(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Estado inválido (solo letras).");
        } while (!valido);

        System.out.println("\n[ACTUALIZACIÓN EXITOSA]: Nuevo estado -> " + solicitudRegistrada.getEstado());
    }

    private static void eliminarSolicitud(Scanner sc) {
        if (solicitudRegistrada == null) {
            System.out.println("\n[RESTRICCIÓN]: No existen solicitudes registradas para eliminar.");
            return;
        }

        System.out.println("\n--- SOLICITUD DISPONIBLE ---");
        System.out.println(" [ID: " + solicitudRegistrada.getIdSolicitud() + "] Tipo: "
                + solicitudRegistrada.getTipoContenido() + " | Estado: " + solicitudRegistrada.getEstado());

        SolicitudConsumo temp = new SolicitudConsumo();
        boolean valido;

        do {
            System.out.print("Ingrese el ID de la solicitud a eliminar: ");
            String idTxt = sc.nextLine();
            valido = Validaciones.validarNumeros(idTxt) && temp.setIdSolicitud(Integer.parseInt(idTxt));
            if (!valido) System.out.println("[ERROR]: ID inválido.");
        } while (!valido);

        if (solicitudRegistrada.getIdSolicitud() == temp.getIdSolicitud()) {
            solicitudRegistrada = null;
            System.out.println("[SISTEMA]: Solicitud eliminada correctamente.");
        } else {
            System.out.println("[ERROR]: El ID ingresado no coincide con la solicitud mostrada.");
        }
    }
}