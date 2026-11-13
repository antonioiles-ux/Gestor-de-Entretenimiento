package ec.edu.uce.gestor.Consola;

import java.util.Scanner;

public class MenuSolicitud {

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("\n=== GESTIONAR SOLICITUD DE CONSUMO ===");
            System.out.println("1. Crear Solicitud");
            System.out.println("2. Consultar Solicitud");
            System.out.println("3. Eliminar Solicitud");
            System.out.println("4. Actualizar Solicitud");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1:
                    System.out.println("Solicitud creada.");
                    break;

                case 2:
                    System.out.println("Consultando solicitud.");
                    break;

                case 3:
                    System.out.println("Solicitud eliminada.");
                    break;

                case 4:
                    System.out.println("Solicitud actualizada.");
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