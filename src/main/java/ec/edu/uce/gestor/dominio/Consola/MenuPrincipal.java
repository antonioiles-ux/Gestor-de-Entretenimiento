package ec.edu.uce.gestor.dominio.Consola;

import java.util.Scanner;

public class Menu {
    private Scanner sn = new Scanner(System.in);

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("        GESTOR DE CONTENIDO Y VALORACIONES      ");
            System.out.println("               MENÚ PRINCIPAL - NIVEL 0                ");

            System.out.println("1. [Módulo H1] Gestionar Cuenta");
            System.out.println("2. [Módulo H2] Gestionar Contenido");
            System.out.println("3. [Módulo H3] Gestionar Solicitudes");
            System.out.println("4. [Módulo H4] Gestionar Valoraciones");
            System.out.println("5. [Módulo H5] Gestionar Opiniones");
            System.out.println("6. Cerrar Sesión y Salir del Programa");
            System.out.print("Seleccione una opción de nivel 0: ");
            opcion = sn.nextInt(); sn.nextLine();

            switch(opcion) {
                case 1: SubMenu.mostrarSubMenuCuenta(); break;
                case 2: SubMenu.mostrarSubMenuContenido(); break;
                case 3: SubMenu.mostrarSubMenuSolicitudes(); break;
                case 4: SubMenu.mostrarSubMenuValoraciones(); break;
                case 5: SubMenu.mostrarSubMenuOpiniones(); break;
                case 6: System.out.println("\n[SISTEMA]: Aplicación cerrada limpiamente."); break;
                default: System.out.println("\n[ERROR]: Opción no válida.");
            }
        } while(opcion != 6);
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mostrarMenuPrincipal();
    }
}