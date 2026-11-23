package ec.edu.uce.gestor.Consola;

import ec.edu.uce.gestor.Util.Validaciones;
import ec.edu.uce.gestor.dominio.SolicitudConsumo;
import java.util.Date;
import java.util.Scanner;
import ec.edu.uce.gestor.dominio.Contenido;

public class MenuSolicitud {

    private static int contadorIdSolicitud = 1;

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

        System.out.println("\n--- CREAR SOLICITUD ---");

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

        // AGREGACIÓN: Asociar un contenido real del catálogo si existen registrados
        if (MenuContenido.hayContenidoRegistrado()) {
            MenuContenido.mostrarResumenContenidos();
            System.out.print("Ingrese el ID de un contenido para adjuntar (o 0 para omitir): ");
            String idContTxt = sc.nextLine();
            if (Validaciones.validarNumeros(idContTxt) && Integer.parseInt(idContTxt) > 0) {
                Contenido c = MenuContenido.obtenerContenidoPorId(Integer.parseInt(idContTxt));
                if (c != null) {
                    nueva.agregarContenido(c);
                }
            }
        }

        nueva.setIdSolicitud(contadorIdSolicitud);
        nueva.setFechaSolicitud(new Date());

        if (MenuPrincipal.usuarioActivo.agregarSolicitud(nueva)) {
            contadorIdSolicitud++;
            System.out.println("\n[SOLICITUD CREADA]:");
            System.out.println(" -> ID: " + nueva.getIdSolicitud()
                    + " | Tipo: " + nueva.getTipoContenido()
                    + " | Estado: " + nueva.getEstado()
                    + " | Contenidos adjuntos: " + nueva.getCantidadContenidos());
        } else {
            System.out.println("\n[ERROR]: Ya tienes una solicitud Pendiente duplicada para ese contenido/tipo.");
        }
    }

    private static void consultarSolicitud(Scanner sc) {
        SolicitudConsumo[] lista = MenuPrincipal.usuarioActivo.listarSolicitudes();
        if (lista.length == 0) {
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

        boolean encontrada = false;
        System.out.println("\n--- SOLICITUDES ENCONTRADAS ---");
        for (SolicitudConsumo s : lista) {
            if (s.tieneMismoTipoContenido(temp)) {
                System.out.println(" -> ID: " + s.getIdSolicitud()
                        + " | Tipo: " + s.getTipoContenido()
                        + " | Estado: " + s.getEstado()
                        + " | Fecha: " + s.getFechaSolicitud());
                for (Contenido c : s.listarContenidos()) {
                    System.out.println("    * Contenido adjunto: " + c.getTitulo());
                }
                encontrada = true;
            }
        }
        if (!encontrada) {
            System.out.println("[INFO]: No se encontró ninguna solicitud para '" + temp.getTipoContenido() + "'.");
        }
    }

    private static void actualizarSolicitud(Scanner sc) {
        SolicitudConsumo[] lista = MenuPrincipal.usuarioActivo.listarSolicitudes();
        if (lista.length == 0) {
            System.out.println("\n[RESTRICCIÓN]: No existen solicitudes registradas para actualizar.");
            return;
        }

        System.out.println("\n--- MIS SOLICITUDES ---");
        for (SolicitudConsumo s : lista) {
            System.out.println(" [ID: " + s.getIdSolicitud() + "] Tipo: "
                    + s.getTipoContenido() + " | Estado actual: " + s.getEstado());
        }

        int idBuscar = 0;
        boolean valido;

        do {
            System.out.print("Ingrese el ID de la solicitud a modificar: ");
            String idTxt = sc.nextLine();
            if (Validaciones.validarNumeros(idTxt) && Integer.parseInt(idTxt) > 0) {
                idBuscar = Integer.parseInt(idTxt);
                valido = true;
            } else {
                System.out.println("[ERROR]: ID inválido.");
                valido = false;
            }
        } while (!valido);

        if (MenuPrincipal.usuarioActivo.buscarSolicitud(idBuscar) == null) {
            System.out.println("[ERROR]: El ID ingresado no coincide con ninguna de tus solicitudes.");
            return;
        }

        do {
            System.out.print("Ingrese nuevo estado (solo letras, ej. Consumido, Aprobada): ");
            valido = MenuPrincipal.usuarioActivo.actualizarEstadoSolicitud(idBuscar, sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Estado inválido (solo letras).");
        } while (!valido);

        System.out.println("\n[ACTUALIZACIÓN EXITOSA]: Nuevo estado -> "
                + MenuPrincipal.usuarioActivo.buscarSolicitud(idBuscar).getEstado());
    }

    private static void eliminarSolicitud(Scanner sc) {
        SolicitudConsumo[] lista = MenuPrincipal.usuarioActivo.listarSolicitudes();
        if (lista.length == 0) {
            System.out.println("\n[RESTRICCIÓN]: No existen solicitudes registradas para eliminar.");
            return;
        }

        System.out.println("\n--- MIS SOLICITUDES ---");
        for (SolicitudConsumo s : lista) {
            System.out.println(" [ID: " + s.getIdSolicitud() + "] Tipo: "
                    + s.getTipoContenido() + " | Estado: " + s.getEstado());
        }

        int idBuscar = 0;
        boolean valido;

        do {
            System.out.print("Ingrese el ID de la solicitud a eliminar: ");
            String idTxt = sc.nextLine();
            if (Validaciones.validarNumeros(idTxt) && Integer.parseInt(idTxt) > 0) {
                idBuscar = Integer.parseInt(idTxt);
                valido = true;
            } else {
                System.out.println("[ERROR]: ID inválido.");
                valido = false;
            }
        } while (!valido);

        if (MenuPrincipal.usuarioActivo.eliminarSolicitud(idBuscar)) {
            System.out.println("[SISTEMA]: Solicitud eliminada correctamente.");
        } else {
            System.out.println("[ERROR]: El ID ingresado no coincide con ninguna de tus solicitudes.");
        }
    }
}