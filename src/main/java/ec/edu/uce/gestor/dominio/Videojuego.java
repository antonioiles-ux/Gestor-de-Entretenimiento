package ec.edu.uce.gestor.dominio;

import java.util.Date;
import ec.edu.uce.gestor.Util.Validaciones;

public class Videojuego extends Contenido {
    private String plataforma;
    private String clasificacionEdad;

    //Conctructor por defecto
    public Videojuego() {
        super();
        this.plataforma = "Sin plataforma";
        this.clasificacionEdad = "Sin Clasificacion";
    }

    public Videojuego(int idContenido, String titulo, String autor, String genero, Date fechaPublicacion, String plataforma, String clasificacionEdad) {
        super(idContenido, titulo, autor, genero, fechaPublicacion);
        if (!setPlataforma(plataforma)) {
            this.plataforma = "Sin plataforma";
        }
        if (!setClasificacionEdad(clasificacionEdad)) {
            this.clasificacionEdad = "Sin Clasificacion";
        }
    }
    //gett
    public String getPlataforma(){
        return plataforma;
    }
    public String getClasificacionEdad(){
        return clasificacionEdad;
    }

    //set
    public boolean setPlataforma(String plataforma) {
        if (!Validaciones.validarTextoConLetras(plataforma)) {
            return false;
        }
        this.plataforma = plataforma.trim();
        return true;
    }

    public boolean setClasificacionEdad(String clasificacionEdad) {
        if (!Validaciones.validarTextoConLetras(clasificacionEdad)) {
            return false;
        }
        this.clasificacionEdad = clasificacionEdad.trim();
        return true;
    }

    @Override
    public String toString(){
        return super.toString() + "Plataforma: " + this.getPlataforma() + ", clasificacionEdad: " +
                this.getClasificacionEdad()+ ", ";
    }
}
