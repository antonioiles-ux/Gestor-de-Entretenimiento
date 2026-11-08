package ec.edu.uce.gestor.test;

import ec.edu.uce.gestor.dominio.Opinion;

public class TestOpinion {

    public static void main(String[] args) {

        // Crear objeto usando constructor con parámetros
        Opinion opinion = new Opinion(
                1,
                "Excelente libro, muy recomendado.",
                "07/11/2026",
                "07/11/2026"
        );

        System.out.println("=== DATOS INICIALES ===");
        System.out.println("ID Opinión: " + opinion.getIdOpinion());
        System.out.println("Comentario: " + opinion.getComentario());
        System.out.println("Fecha Registro: " + opinion.getFechaRegistro());
        System.out.println("Fecha Edición: " + opinion.getFechaEdicion());

        // Modificar datos usando setters
        opinion.setIdOpinion(2);
        opinion.setComentario("Contenido actualizado y mejorado.");
        opinion.setFechaRegistro("08/11/2026");
        opinion.setFechaEdicion("09/11/2026");

        System.out.println("\n=== DATOS MODIFICADOS ===");
        System.out.println("ID Opinión: " + opinion.getIdOpinion());
        System.out.println("Comentario: " + opinion.getComentario());
        System.out.println("Fecha Registro: " + opinion.getFechaRegistro());
        System.out.println("Fecha Edición: " + opinion.getFechaEdicion());
    }
}
