package ec.edu.uce.gestor.dominio;

import java.util.Date;

public class Videojuego extends Contenido {
    private String plataforma;
    private String clasificacionEdad;

    //Conctructor por defecto
    public Videojuego() {
        super();
        this.plataforma = "Sin plataforma";
        this.clasificacionEdad = "Sin Clasificacion";
    }

    public Videojuego(int idContenido, String titulo, String autor, String genero, Date fechaPublicacion, String plataforma, String clasificacionEdad){
        super(idContenido, titulo, autor, genero, fechaPublicacion);
        this.plataforma = plataforma;
        this.clasificacionEdad = clasificacionEdad;
    }

    public String getPlataforma(){
        return plataforma;
    }

    public boolean setPlataforma(String plataforma) {
        if (plataforma == null || plataforma.trim().isEmpty()) {
            return false;
        }
        this.plataforma = plataforma;
        return true;
    }

    public String getClasificacionEdad(){
        return clasificacionEdad;
    }
    public boolean setClasificacionEdad(String clasificacionEdad) {
        if (clasificacionEdad == null || clasificacionEdad.trim().isEmpty()) {
            return false;
        }
        this.clasificacionEdad = clasificacionEdad;
        return true;
    }

    @Override
    public String toString(){
        return super.toString() + "Plataforma: " + this.getPlataforma() + ", clasificacionEdad: " +
                this.getClasificacionEdad()+ ", ";
    }
}
