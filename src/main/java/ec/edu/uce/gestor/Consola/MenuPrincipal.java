package ec.edu.uce.gestor.Consola;

import java.util.Scanner;

public class MenuPrincipal {
    private Scanner sn = new Scanner(System.in);

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n        GESTOR DE CONTENIDO Y VALORACIONES      ");
            System.out.println("               MENÚ PRINCIPAL - NIVEL 0                ");

            System.out.println("1. [Módulo H1] Gestionar Cuenta");
            System.out.println("2. [Módulo H2] Gestionar Contenido");
            System.out.println("3. [Módulo H3] Gestionar Solicitudes");
            System.out.println("4. [Módulo H4] Gestionar Valoraciones");
            System.out.println("5. [Módulo H5] Gestionar Opiniones");
            System.out.println("6. Cerrar Sesión y Salir del Programa");
            System.out.print("Seleccione una opción de nivel 0: ");
            opcion = sn.nextInt();
            sn.nextLine();

            switch (opcion) {
                case 1: MenuCuenta.mostrarMenu();
                    break;
                case 2: MenuContenido.mostrarMenu();
                    break;
                case 3: MenuSolicitud.mostrarMenu();
                    break;
                case 4: MenuValoracion.mostrarMenu();
                    break;
                case 5: MenuOpinion.mostrarMenu();
                    break;
                case 6: System.out.println("\n[SISTEMA]: Aplicación cerrada limpiamente.");
                    break;
                default: System.out.println("\n[ERROR]: Opción no válida.");
            }
        } while (opcion != 6);
    }

    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.mostrarMenuPrincipal();
    }
}