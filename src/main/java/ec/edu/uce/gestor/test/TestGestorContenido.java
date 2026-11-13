package ec.edu.uce.gestor.test;

import ec.edu.uce.gestor.dominio.GestorContenido;

public class TestGestorContenido {

    public static void main(String[] args) {

        // Crear objeto utilizando el constructor con parámetros
        GestorContenido gestor =
                new GestorContenido(
                        "Carlos Pérez",
                        101,
                        5001
                );

        System.out.println("=== DATOS INICIALES ===");
        System.out.println("Nombre Completo: " + gestor.getNombreCompleto());
        System.out.println("ID Usuario: " + gestor.getIdUsuario());
        System.out.println("ID Gestor: " + gestor.getIdGestor());

        // Modificar datos mediante setters
        gestor.setNombreCompleto("Ana Gómez");
        gestor.setIdUsuario(102);
        gestor.setIdGestor(5002);

        System.out.println("\n=== DATOS MODIFICADOS ===");
        System.out.println("Nombre Completo: " + gestor.getNombreCompleto());
        System.out.println("ID Usuario: " + gestor.getIdUsuario());
        System.out.println("ID Gestor: " + gestor.getIdGestor());
    }
}