package ec.edu.uce.gestor.test;

import ec.edu.uce.gestor.dominio.Videojuego;

public class TestVideojuego {

    public static void main(String[] args) {

        // Crear objeto usando el constructor con parámetros
        Videojuego videojuego = new Videojuego(
                "PC",
                "+18"
        );

        System.out.println("=== DATOS INICIALES ===");
        System.out.println("Plataforma: " + videojuego.getPlataforma());
        System.out.println("Clasificación por Edad: " + videojuego.getClasificacionEdad());

        // Modificar datos usando setters
        videojuego.setPlataforma("PlayStation 5");
        videojuego.setClasificacionEdad("+16");

        System.out.println("\n=== DATOS MODIFICADOS ===");
        System.out.println("Plataforma: " + videojuego.getPlataforma());
        System.out.println("Clasificación por Edad: " + videojuego.getClasificacionEdad());
    }
}
