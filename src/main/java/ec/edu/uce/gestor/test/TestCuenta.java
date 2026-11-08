package ec.edu.uce.gestor.test;

import ec.edu.uce.gestor.dominio.Cuenta;

public class TestCuenta {

    public static void main(String[] args) {

        // Crear objeto usando constructor con parámetros
        Cuenta cuenta = new Cuenta(
                1,
                "usuario@gmail.com",
                "123456",
                "07/11/2026"
        );

        System.out.println("=== DATOS INICIALES ===");
        System.out.println("ID Cuenta: " + cuenta.getIdCuenta());
        System.out.println("Correo: " + cuenta.getCorreoElectronico());
        System.out.println("Contraseña: " + cuenta.getContrasena());
        System.out.println("Fecha Registro: " + cuenta.getFechaRegistro());

        // Modificar valores usando setters
        cuenta.setIdCuenta(2);
        cuenta.setCorreoElectronico("nuevo@gmail.com");
        cuenta.setContrasena("abcdef");
        cuenta.setFechaRegistro("08/11/2026");

        System.out.println("\n=== DATOS MODIFICADOS ===");
        System.out.println("ID Cuenta: " + cuenta.getIdCuenta());
        System.out.println("Correo: " + cuenta.getCorreoElectronico());
        System.out.println("Contraseña: " + cuenta.getContrasena());
        System.out.println("Fecha Registro: " + cuenta.getFechaRegistro());
    }
}
