package ec.edu.uce.gestor.test;

import ec.edu.uce.gestor.dominio.Contenido;
import java.util.Date;

public class TestContenido {

    public static void main(String[] args) {

        // Crear objeto usando el constructor con parámetros
        Contenido contenido = new Contenido(
                1,
                "Cien Años de Soledad",
                "Gabriel García Márquez",
                "Novela",
                new Date()
        );

        // Mostrar datos iniciales
        System.out.println("=== DATOS INICIALES ===");
        System.out.println("ID: " + contenido.getIdContenido());
        System.out.println("Título: " + contenido.getTitulo());
        System.out.println("Autor: " + contenido.getAutor());
        System.out.println("Género: " + contenido.getGenero());
        System.out.println("Fecha de publicación: " + contenido.getFechaPublicacion());

        // Modificar datos con setters
        contenido.setIdContenido(2);
        contenido.setTitulo("Don Quijote de la Mancha");
        contenido.setAutor("Miguel de Cervantes");
        contenido.setGenero("Literatura Clásica");

        // Mostrar datos actualizados
        System.out.println("\n=== DATOS MODIFICADOS ===");
        System.out.println("ID: " + contenido.getIdContenido());
        System.out.println("Título: " + contenido.getTitulo());
        System.out.println("Autor: " + contenido.getAutor());
        System.out.println("Género: " + contenido.getGenero());
        System.out.println("Fecha de publicación: " + contenido.getFechaPublicacion());
    }
}