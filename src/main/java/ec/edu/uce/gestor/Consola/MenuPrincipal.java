package ec.edu.uce.gestor.Consola;

import ec.edu.uce.gestor.Util.Validaciones;
import java.util.Scanner;

public class MenuPrincipal {

    public static boolean sesionIniciada = false;
    private Scanner sn = new Scanner(System.in);

    public void mostrarMenuPrincipal() {
        int opcion = -1;

        do {
            System.out.println("\n================================================");
            System.out.println("      GESTOR DE CONTENIDO Y VALORACIONES        ");
            System.out.println("             MENÚ PRINCIPAL - NIVEL 0           ");
            System.out.println(" Estado actual: " + (sesionIniciada ? "[SESIÓN INICIADA]" : "[VISITANTE - Sin sesión]"));
            System.out.println("================================================");

            System.out.println("1. [Módulo H1] Gestionar Cuenta");
            System.out.println("2. [Módulo H2] Gestionar Contenido");
            System.out.println("3. [Módulo H3] Gestionar Solicitudes");
            System.out.println("4. [Módulo H4] Gestionar Valoraciones");
            System.out.println("5. [Módulo H5] Gestionar Opiniones");
            if (sesionIniciada) {
                System.out.println("6. Cerrar Sesión");
            }
            System.out.println("0. Salir del Programa");
            System.out.print("Seleccione una opción de nivel 0: ");

            String entrada = sn.nextLine();
            if (!Validaciones.validarNumeros(entrada)) {
                System.out.println("\n[ERROR]: Ingrese solo números.");
                continue;
            }
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1:
                    MenuCuenta.mostrarMenu();
                    break;

                case 2:
                    // Visitante puede entrar a consultar catálogo; registrar/eliminar se controla adentro
                    MenuContenido.mostrarMenu();
                    break;

                case 3:
                    if (!sesionIniciada) {
                        System.out.println("\n[ACCESO RESTRINGIDO]: Debe iniciar sesión para acceder a este módulo.");
                    } else {
                        MenuSolicitud.mostrarMenu();
                    }
                    break;

                case 4:
                    if (!sesionIniciada) {
                        System.out.println("\n[ACCESO RESTRINGIDO]: Debe iniciar sesión para acceder a este módulo.");
                    } else {
                        MenuValoracion.mostrarMenu();
                    }
                    break;

                case 5:
                    if (!sesionIniciada) {
                        System.out.println("\n[ACCESO RESTRINGIDO]: Debe iniciar sesión para acceder a este módulo.");
                    } else {
                        MenuOpinion.mostrarMenu();
                    }
                    break;

                case 6:
                    if (sesionIniciada) {
                        sesionIniciada = false;
                        System.out.println("\n[SISTEMA]: Sesión cerrada. Los módulos H3, H4 y H5 han sido bloqueados nuevamente.");
                    } else {
                        System.out.println("\n[ERROR]: Opción no válida.");
                    }
                    break;

                case 0:
                    System.out.println("\n[SISTEMA]: Aplicación cerrada limpiamente.");
                    break;

                default:
                    System.out.println("\n[ERROR]: Opción no válida.");
            }
        } while (opcion != 0);
    }

    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.mostrarMenuPrincipal();
    }
}