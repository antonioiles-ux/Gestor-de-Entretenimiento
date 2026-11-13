package ec.edu.uce.gestor.Consola;

import java.util.Scanner;

public class MenuValoracion {

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("\n=== GESTIONAR VALORACIONES ===");
            System.out.println("1. Crear Valoración");
            System.out.println("2. Consultar Valoración");
            System.out.println("3. Eliminar Valoración");
            System.out.println("4. Actualizar Valoración");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("Valoración creada.");
                    break;

                case 2:
                    System.out.println("Consultando valoración...");
                    break;

                case 3:
                    System.out.println("Valoración eliminada.");
                    break;

                case 4:
                    System.out.println("Valoración actualizada.");
                    break;

                case 0:
                    System.out.println("Regresando...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }
}
