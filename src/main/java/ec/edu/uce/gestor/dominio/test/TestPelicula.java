package ec.edu.uce.gestor.dominio.test;

import ec.edu.uce.gestor.dominio.dominio.Pelicula;

public class TestPelicula {

    public static void main(String[] args) {

        // Crear objeto usando constructor con parámetros
        Pelicula pelicula = new Pelicula(
                120,
                "PG-13"
        );

        System.out.println("=== DATOS INICIALES ===");
        System.out.println("Duración: " + pelicula.getDuracion() + " minutos");
        System.out.println("Clasificación: " + pelicula.getClasificacion());

        // Modificar datos usando setters
        pelicula.setDuracion(150);
        pelicula.setClasificacion("+18");

        System.out.println("\n=== DATOS MODIFICADOS ===");
        System.out.println("Duración: " + pelicula.getDuracion() + " minutos");
        System.out.println("Clasificación: " + pelicula.getClasificacion());
    }
}
