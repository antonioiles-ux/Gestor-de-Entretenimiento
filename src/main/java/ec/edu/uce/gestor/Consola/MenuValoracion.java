package ec.edu.uce.gestor.Consola;

import ec.edu.uce.gestor.Util.Validaciones;
import ec.edu.uce.gestor.dominio.Contenido;
import ec.edu.uce.gestor.dominio.Valoracion;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuValoracion {

    private static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy");
    private static int contadorIdValoracion = 1;

    public static void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n===(H4). GESTIONAR VALORACIONES ===");
            System.out.println("1. Crear Valoración");
            System.out.println("2. Consultar Valoraciones");
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
                case 1 -> crearValoracion(sc);
                case 2 -> consultarValoracion();
                case 3 -> eliminarValoracion(sc);
                case 4 -> actualizarValoracion(sc);
                case 0 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("[ERROR]: Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static void crearValoracion(Scanner sc) {
        if (!MenuContenido.hayContenidoRegistrado()) {
            System.out.println("\n[RESTRICCIÓN]: No existen contenidos registrados. Registre primero un contenido en el Módulo H2.");
            return;
        }

        MenuContenido.mostrarResumenContenidos();
        Contenido contenidoSeleccionado = solicitarContenidoCatalogo(sc, "valorar");
        if (contenidoSeleccionado == null) {
            return;
        }

        Valoracion nueva = new Valoracion();
        int calificacion = solicitarCalificacion(sc, "Ingrese calificación para '" + contenidoSeleccionado.getTitulo() + "' (1 al 5): ");

        nueva.setIdValoracion(contadorIdValoracion);
        nueva.setCalificacion(calificacion);
        nueva.setFechaValoracion(obtenerFechaActual());
        nueva.setAutor(MenuPrincipal.usuarioActivo);

        if (contenidoSeleccionado.agregarValoracion(nueva)) {
            contadorIdValoracion++;
            System.out.println("\n[VALORACIÓN CREADA]: Sobre -> " + contenidoSeleccionado.getTitulo());
            System.out.println(" -> " + nueva);
            System.out.printf(" -> Promedio actual: %.1f/5.0%n", contenidoSeleccionado.calcularPromedioValoraciones());
        } else {
            System.out.println("\n[ERROR]: Ya has valorado este contenido anteriormente (usa la opción 4 para actualizar).");
        }
    }

    private static void consultarValoracion() {
        boolean hayValoraciones = false;
        System.out.println("\n--- VALORACIONES REGISTRADAS ---");

        for (Contenido c : MenuPrincipal.gestorGlobal.listarContenidos()) {
            for (Valoracion v : c.listarValoraciones()) {
                String autor = (v.getAutor() != null) ? v.getAutor().getNombreCompleto() : "Anónimo";
                System.out.println(" Contenido: " + c.getTitulo() + " | Autor: " + autor + " | " + v);
                hayValoraciones = true;
            }
        }

        if (!hayValoraciones) {
            System.out.println("[RESTRICCIÓN]: No existen valoraciones registradas todavía.");
        }
    }

    private static void actualizarValoracion(Scanner sc) {
        Contenido contenido = seleccionarContenidoValoradoPorUsuario(sc, "modificar");
        if (contenido == null) {
            return;
        }

        String autorActual = MenuPrincipal.usuarioActivo.getNombreCompleto();
        int nuevaCalificacion = solicitarCalificacion(sc, "Ingrese nueva calificación (1 al 5): ");

        if (contenido.editarValoracion(autorActual, nuevaCalificacion, obtenerFechaActual())) {
            Valoracion actualizada = contenido.buscarValoracion(autorActual);
            System.out.println("\n[ACTUALIZACIÓN EXITOSA]: " + actualizada);
            System.out.printf(" -> Nuevo promedio: %.1f/5.0%n", contenido.calcularPromedioValoraciones());
        } else {
            System.out.println("[ERROR]: No se pudo actualizar la valoración.");
        }
    }

    private static void eliminarValoracion(Scanner sc) {
        Contenido contenido = seleccionarContenidoValoradoPorUsuario(sc, "eliminar");
        if (contenido == null) {
            return;
        }

        String autorActual = MenuPrincipal.usuarioActivo.getNombreCompleto();
        if (contenido.eliminarValoracion(autorActual)) {
            System.out.println("[SISTEMA]: Valoración eliminada correctamente.");
        } else {
            System.out.println("[ERROR]: No se pudo eliminar la valoración.");
        }
    }



    private static Contenido seleccionarContenidoValoradoPorUsuario(Scanner sc, String accion) {
        if (MenuPrincipal.usuarioActivo == null) {
            System.out.println("[ERROR]: No hay un usuario activo.");
            return null;
        }

        String autorActual = MenuPrincipal.usuarioActivo.getNombreCompleto();
        boolean tieneValoraciones = false;

        System.out.println("\n--- MIS VALORACIONES ---");
        for (Contenido c : MenuPrincipal.gestorGlobal.listarContenidos()) {
            Valoracion val = c.buscarValoracion(autorActual);
            if (val != null) {
                System.out.println(" [ID Contenido: " + c.getIdContenido() + "] " + c.getTitulo() + " -> " + val);
                tieneValoraciones = true;
            }
        }

        if (!tieneValoraciones) {
            System.out.println("[RESTRICCIÓN]: Aún no has registrado ninguna valoración para " + accion + ".");
            return null;
        }

        while (true) {
            System.out.print("Ingrese el ID del contenido cuya valoración desea " + accion + ": ");
            String idTxt = sc.nextLine();
            if (!Validaciones.validarNumeros(idTxt) || Integer.parseInt(idTxt) <= 0) {
                System.out.println("[ERROR]: ID inválido.");
                continue;
            }

            Contenido seleccionado = MenuContenido.obtenerContenidoPorId(Integer.parseInt(idTxt));
            if (seleccionado == null || seleccionado.buscarValoracion(autorActual) == null) {
                System.out.println("[ERROR]: No tienes ninguna valoración registrada en ese contenido.");
                continue;
            }
            return seleccionado;
        }
    }

    private static Contenido solicitarContenidoCatalogo(Scanner sc, String accion) {
        while (true) {
            System.out.print("Ingrese el ID del contenido que desea " + accion + ": ");
            String idContTxt = sc.nextLine();
            if (!Validaciones.validarNumeros(idContTxt) || Integer.parseInt(idContTxt) <= 0) {
                System.out.println("[ERROR]: ID inválido.");
                continue;
            }

            Contenido seleccionado = MenuContenido.obtenerContenidoPorId(Integer.parseInt(idContTxt));
            if (seleccionado == null) {
                System.out.println("[ERROR]: No existe ningún contenido con ese ID en la lista.");
                continue;
            }
            return seleccionado;
        }
    }

    private static int solicitarCalificacion(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String calTxt = sc.nextLine();
            if (Validaciones.validarCalificacion(calTxt)) {
                return Integer.parseInt(calTxt);
            }
            System.out.println("[ERROR]: Calificación inválida. Debe ser un dígito del 1 al 5.");
        }
    }

    private static String obtenerFechaActual() {
        return FORMATO_FECHA.format(new Date());
    }
}