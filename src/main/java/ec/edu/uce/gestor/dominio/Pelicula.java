package ec.edu.uce.gestor.dominio;

public class Pelicula extends Contenido {

    private int duracion;
    private String clasificacion;

    public Pelicula() {
        //por defecto
        this.duracion = 90;
        this.clasificacion = "General";
    }

    public Pelicula(int duracion, String clasificacion) {
        this.duracion = duracion;
        this.clasificacion = clasificacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public Boolean setDuracion(int duracion) {
        if (duracion > 0) {
            this.duracion = duracion;
            return true;
        }
        return false;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public boolean setClasificacion(String clasificacion) {
        if (clasificacion != null && !clasificacion.trim().isEmpty()) {
            this.clasificacion = clasificacion;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Duracion: " + this.getDuracion() + " min, Clasificacion: " + this.getClasificacion() + ", " + super.toString();
    }
}