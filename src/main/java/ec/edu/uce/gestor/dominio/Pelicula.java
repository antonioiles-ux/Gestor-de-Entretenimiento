package ec.edu.uce.gestor.dominio;

import java.util.Date;

public class Pelicula extends Contenido {
    private int duracion;
    private String clasificacion;

    public Pelicula() {
        super();
        this.duracion = 0;
        this.clasificacion = "sin clasificacion";
    }

    public Pelicula(int idContenido, String titulo, String autor, String genero, Date fechaPublicacion, int duracion, String clasificacion) {
        super(idContenido, titulo, autor, genero, fechaPublicacion);
        this.duracion = duracion;
        this.clasificacion = clasificacion;
    }

    public int getDuracion(){
        return duracion;
    }
    public boolean setDuracion(int duracion){
        if (duracion <= 0){
            return false;
        }
        this.duracion = duracion;
        return true;
    }

    public String getClasificacion(){
        return clasificacion;
    }
    public boolean setClasificacion(String clasificacion){
        if (clasificacion == ""){
            return false;
        }
        this.clasificacion = clasificacion;
        return true;
    }
    @Override
    public String toString() {
        return super.toString()+ "Pelicula{" + "duracion=" + this.getDuracion() + ", clasificacion='" + this.getClasificacion() + '\'' + "} ";
    }

}