package ec.edu.uce.gestor.dominio.dominio;

public class Videojuego extends Contenido {
    private String plataforma;
    private String clasificacionEdad;

    public Videojuego() {
        super();
        this.plataforma = "Sin plataforma";
        this.clasificacionEdad = "Sin Clasificacion";
    }

    public Videojuego(String plataforma, String clasificacionEdad){
        this.plataforma = plataforma;
        this.clasificacionEdad = clasificacionEdad;
    }

    public String getPlataforma(){
        return plataforma;
    }
    public boolean setPlataforma(String plataforma) {
        if (plataforma == null) {
            return false ;
        }
        this.plataforma = plataforma;
        return true;
    }

    public String getClasificacionEdad(){
        return clasificacionEdad;
    }
    public boolean setClasificacionEdad(String clasificacionEdad) {
        if (clasificacionEdad == null) {
            return false;
        }
        this.clasificacionEdad = clasificacionEdad;
        return true;
    }

    @Override
    public String toString(){
        return "Plataforma: "+this.getPlataforma()+", clasificacionEdad: "+this.getClasificacionEdad()+", "+super.toString();
    }
}
