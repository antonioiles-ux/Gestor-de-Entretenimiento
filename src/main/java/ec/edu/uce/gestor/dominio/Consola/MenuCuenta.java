package ec.edu.uce.gestor.dominio.Consola;

import java.util.Scanner;

public class MenuCuenta {

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {

            System.out.println("=== GESTIONAR CUENTA ===");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrar Usuario");
            System.out.println("0. Regresar");

            opcion = sc.nextInt();

        } while(opcion != 0);
    }
}
