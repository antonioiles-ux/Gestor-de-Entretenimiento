package ec.edu.uce.gestor.dominio;

import java.util.Objects;
import java.util.Date;

public class Serie extends Contenido {
    private int numeroTemporadas;
    private int numeroEpisodios;

    public Serie() {
        this(0, "sin titulo", "sin autor", "sin genero", null, 0, 0);
    }

    public Serie(int idContenido, String titulo, String autor, String genero, Date fechaPublicacion, int numeroTemporadas, int numeroEpisodios) {
        super(idContenido, titulo, autor, genero, fechaPublicacion);
        this.numeroTemporadas = numeroTemporadas;
        this.numeroEpisodios = numeroEpisodios;
    }

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

    // --- POLIMORFISMO / SOBREESCRITURA (Heredado de Contenido) ---
    @Override
    public int calcularTiempoConsumo() {
        return this.numeroEpisodios * 45;
    }

    // --- SOBRECARGA DE MÉTODOS (incluye referencia de objeto) ---
    public int calcularTiempoConsumo(int minutosPorEpisodio) {
        if (minutosPorEpisodio <= 0) {
            return 0;
        }
        return this.numeroEpisodios * minutosPorEpisodio;
    }

    public boolean tieneMasTemporadasQue(Serie otraSerie) {
        if (otraSerie == null) {
            return false;
        }
        return this.numeroTemporadas > otraSerie.numeroTemporadas;
    }

    // --- EQUALS Y HASHCODE ---
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Serie s)) {
            return false;
        }
        return this.numeroTemporadas == s.numeroTemporadas
                && this.numeroEpisodios == s.numeroEpisodios
                && super.equals(s);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroTemporadas, numeroEpisodios);
    }
}