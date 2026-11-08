package ec.edu.uce.gestor.test;

import ec.edu.uce.gestor.dominio.Serie;

public class TestSerie {

    public static void main(String[] args) {

        // Crear objeto usando constructor con parámetros
        Serie serie = new Serie(
                5,
                62
        );

        System.out.println("=== DATOS INICIALES ===");
        System.out.println("Número de Temporadas: " + serie.getNumeroTemporadas());
        System.out.println("Número de Episodios: " + serie.getNumeroEpisodios());

        // Modificar datos usando setters
        serie.setNumeroTemporadas(7);
        serie.setNumeroEpisodios(100);

        System.out.println("\n=== DATOS MODIFICADOS ===");
        System.out.println("Número de Temporadas: " + serie.getNumeroTemporadas());
        System.out.println("Número de Episodios: " + serie.getNumeroEpisodios());
    }
}
