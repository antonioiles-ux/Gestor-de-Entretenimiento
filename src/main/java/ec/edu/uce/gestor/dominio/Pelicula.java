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

    public Pelicula(int idContenido, String titulo, String autor, String descripcion, Date fechaLanzamiento, int duracion, String clasificacion) {
        super(idContenido, titulo, autor, descripcion, fechaLanzamiento);
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


}