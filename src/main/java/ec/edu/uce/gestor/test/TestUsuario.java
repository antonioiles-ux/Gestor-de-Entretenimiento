package ec.edu.uce.gestor.test;

import ec.edu.uce.gestor.dominio.Usuario;

public class TestUsuario {

    public static void main(String[] args) {

        // Crear objeto usando constructor con parámetros
        Usuario usuario = new Usuario(
                "Juan Carlos Pérez",
                1
        );

        System.out.println("=== DATOS INICIALES ===");
        System.out.println("Nombre Completo: " + usuario.getNombreCompleto());
        System.out.println("ID Usuario: " + usuario.getIdUsuario());

        // Modificar datos usando setters
        usuario.setNombreCompleto("María Fernanda Gómez");
        usuario.setIdUsuario(2);

        System.out.println("\n=== DATOS MODIFICADOS ===");
        System.out.println("Nombre Completo: " + usuario.getNombreCompleto());
        System.out.println("ID Usuario: " + usuario.getIdUsuario());
    }
}
