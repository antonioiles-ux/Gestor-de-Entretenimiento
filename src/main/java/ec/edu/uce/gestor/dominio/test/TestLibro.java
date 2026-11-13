package ec.edu.uce.gestor.dominio.test;

import ec.edu.uce.gestor.dominio.dominio.Libro;

public class TestLibro {

    public static void main(String[] args) {

        // Crear objeto usando constructor con parámetros
        Libro libro = new Libro(
                350,
                "Novela de realismo mágico"
        );

        System.out.println("=== DATOS INICIALES ===");
        System.out.println("Número de páginas: " + libro.getNumeroPaginas());
        System.out.println("Descripción: " + libro.getDescripcion());

        // Modificar datos usando setters
        libro.setNumeroPaginas(500);
        libro.setDescripcion("Obra literaria clásica");

        System.out.println("\n=== DATOS MODIFICADOS ===");
        System.out.println("Número de páginas: " + libro.getNumeroPaginas());
        System.out.println("Descripción: " + libro.getDescripcion());
    }
}
