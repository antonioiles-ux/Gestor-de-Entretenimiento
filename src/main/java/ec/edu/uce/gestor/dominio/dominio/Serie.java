package ec.edu.uce.gestor.dominio.dominio;

import java.util.Date;
public class Serie extends Contenido {
    private int numeroTemporadas;
    private int numeroEpisodios;

    // 1. Constructor por defecto
    public Serie() {
        super();
        this.numeroTemporadas = 0;
        this.numeroEpisodios = 0;
    }

    // 2. Constructor completo de 7 parámetros
    public Serie(int idContenido, String titulo, String autor, String descripcion, Date fechaLanzamiento, int numeroTemporadas, int numeroEpisodios) {
        super(idContenido, titulo, autor, descripcion, fechaLanzamiento); // Pasa los datos al padre
        this.numeroTemporadas = numeroTemporadas;
        this.numeroEpisodios = numeroEpisodios;
    }

    // Getters y Setters
    public int getNumeroTemporadas() {

        return numeroTemporadas;
    }

    public boolean setNumeroTemporadas(int numeroTemporadas) {
        if (numeroTemporadas > 0) {
            this.numeroTemporadas = numeroTemporadas;
            return true;
        }
        return false;
    }

    public int getNumeroEpisodios() {
        return numeroEpisodios;
    }

    public boolean setNumeroEpisodios(int numeroEpisodios) {
        if (numeroEpisodios > 0) {
            this.numeroEpisodios = numeroEpisodios;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "Temporadas: " + this.getNumeroTemporadas() + ", Episodios: " + this.getNumeroEpisodios() + ", ";
    }

}