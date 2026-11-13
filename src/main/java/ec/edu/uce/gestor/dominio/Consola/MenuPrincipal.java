package ec.edu.uce.gestor.dominio.Consola;

import java.util.Scanner;

public class MenuPrincipal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("1. Gestionar Cuenta");
            System.out.println("2. Gestionar Contenido");
            System.out.println("3. Gestionar Solicitudes");
            System.out.println("4. Gestionar Valoraciones");
            System.out.println("5. Gestionar Opiniones");
            System.out.println("0. Salir");

            opcion = sc.nextInt();

            switch(opcion) {

                case 1:
                    MenuCuenta.mostrarMenu();
                    break;

                case 2:
                    MenuContenido.mostrarMenu();
                    break;

                case 3:
                    MenuSolicitud.mostrarMenu();
                    break;

                case 4:
                    MenuValoracion.mostrarMenu();
                    break;

                case 5:
                    MenuOpinion.mostrarMenu();
                    break;
            }

        } while(opcion != 0);
    }
}