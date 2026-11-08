package ec.edu.uce.gestor.test;

import ec.edu.uce.gestor.dominio.Valoracion;

public class TestValoracion {

    public static void main(String[] args) {

        // Crear objeto usando el constructor con parámetros
        Valoracion valoracion = new Valoracion(
                1,
                5,
                "07/11/2026"
        );

        System.out.println("=== DATOS INICIALES ===");
        System.out.println("ID Valoración: " + valoracion.getIdValoracion());
        System.out.println("Calificación: " + valoracion.getCalificacion());
        System.out.println("Fecha Valoración: " + valoracion.getFechaValoracion());

        // Modificar datos usando setters
        valoracion.setIdValoracion(2);
        valoracion.setCalificacion(4);
        valoracion.setFechaValoracion("08/11/2026");

        System.out.println("\n=== DATOS MODIFICADOS ===");
        System.out.println("ID Valoración: " + valoracion.getIdValoracion());
        System.out.println("Calificación: " + valoracion.getCalificacion());
        System.out.println("Fecha Valoración: " + valoracion.getFechaValoracion());
    }
}