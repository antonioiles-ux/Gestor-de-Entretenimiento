package ec.edu.uce.gestor.Consola;

import java.util.Scanner;

public class MenuContenido {

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("\n===(H2)GESTIONAR CONTENIDO DE ENTRETENIMIENTO ===");
            System.out.println("1. Registrar Contenido");
            System.out.println("2. Eliminar Contenido Erróneo");
            System.out.println("3. Consultar Catálogo");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    menuRegistrarContenido(sc);
                    break;

                case 2:
                    System.out.println("Contenido eliminado correctamente.");
                    break;

                case 3:
                    menuCatalogo(sc);
                    break;

                case 0:
                    System.out.println("Regresando...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static void menuRegistrarContenido(Scanner sc) {

        int opcion;

        do {

            System.out.println("\n=== REGISTRAR CONTENIDO ===");
            System.out.println("1. Registrar Libro");
            System.out.println("2. Registrar Película");
            System.out.println("3. Registrar Serie");
            System.out.println("4. Registrar Videojuego");
            System.out.println("0. Regresar");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("Libro registrado.");
                    break;

                case 2:
                    System.out.println("Película registrada.");
                    break;

                case 3:
                    System.out.println("Serie registrada.");
                    break;

                case 4:
                    System.out.println("Videojuego registrado.");
                    break;
            }

        } while (opcion != 0);
    }

    private static void menuCatalogo(Scanner sc) {

        int opcion;

        do {

            System.out.println("\n=== CONSULTAR CATÁLOGO ===");
            System.out.println("1. Visualizar Series");
            System.out.println("2. Visualizar Libros");
            System.out.println("3. Visualizar Películas");
            System.out.println("4. Visualizar Videojuegos");
            System.out.println("5. Buscar Contenido");
            System.out.println("0. Regresar");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("Mostrando series...");
                    break;

                case 2:
                    System.out.println("Mostrando libros...");
                    break;

                case 3:
                    System.out.println("Mostrando películas...");
                    break;

                case 4:
                    System.out.println("Mostrando videojuegos...");
                    break;

                case 5:
                    menuBuscarContenido(sc);
                    break;
            }

        } while (opcion != 0);
    }

    private static void menuBuscarContenido(Scanner sc) {

        int opcion;

        do {

            System.out.println("\n=== BUSCAR CONTENIDO ===");
            System.out.println("1. Buscar por Título");
            System.out.println("2. Buscar por Género");
            System.out.println("3. Buscar por Autor");
            System.out.println("0. Regresar");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("Buscando por título...");
                    break;

                case 2:
                    System.out.println("Buscando por género...");
                    break;

                case 3:
                    System.out.println("Buscando por autor...");
                    break;
            }

        } while (opcion != 0);
    }
}
