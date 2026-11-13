package ec.edu.uce.gestor.dominio.Consola;

import java.util.Scanner;

public class MenuOpinion {

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("\n=== GESTIONAR OPINIONES ===");
            System.out.println("1. Consultar Opiniones");
            System.out.println("2. Escribir Opinión");
            System.out.println("3. Editar Opinión");
            System.out.println("4. Eliminar Opinión");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("Mostrando opiniones...");
                    break;

                case 2:
                    System.out.println("Opinión registrada.");
                    break;

                case 3:
                    System.out.println("Opinión editada.");
                    break;

                case 4:
                    System.out.println("Opinión eliminada.");
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
