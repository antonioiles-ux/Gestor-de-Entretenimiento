package ec.edu.uce.gestor.dominio;
import java.util.Objects;
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
    public Serie(int idContenido, String titulo, String autor, String genero, Date fechaPublicacion, int numeroTemporadas, int numeroEpisodios) {
        super(idContenido, titulo, autor, genero, fechaPublicacion); // Pasa los datos al padre
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


    // --- POLIMORFISMO / SOBREESCRITURA (Heredado de Contenido) ---
    // Estima el tiempo total en minutos asumiendo 45 minutos por episodio
    @Override
    public int calcularTiempoConsumo() {
        return this.numeroEpisodios * 45;
    }

    // --- SOBRECARGA DE MÉTODOS ---
    // Calcula el tiempo total especificando la duración promedio por episodio
    public int calcularTiempoConsumo(int minutosPorEpisodio) {
        if (minutosPorEpisodio <= 0) {
            return 0;
        }
        return this.numeroEpisodios * minutosPorEpisodio;
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