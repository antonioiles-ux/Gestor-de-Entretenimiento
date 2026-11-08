package ec.edu.uce.gestor.dominio;

public class Pelicula extends Contenido {

    private int duracion;
    private String clasificacion;

    public Pelicula() {
    }

    public Pelicula(int duracion, String clasificacion) {
        this.duracion = duracion;
        this.clasificacion = clasificacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
}