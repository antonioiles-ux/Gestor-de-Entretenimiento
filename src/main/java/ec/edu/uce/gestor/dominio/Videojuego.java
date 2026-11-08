package ec.edu.uce.gestor.dominio;

public class Videojuego extends Contenido {
    private String plataforma;
    private String clasificacionEdad;

    public Videojuego() {
    }

    public Videojuego(String plataforma, String clasificacionEdad){
        this.plataforma = plataforma;
        this.clasificacionEdad = clasificacionEdad;
    }

    public String getPlataforma(){
        return plataforma;
    }
    public void setPlataforma(String plataforma){
        this.plataforma = plataforma;
    }

    public String getClasificacionEdad(){
        return clasificacionEdad;
    }
    public void setClasificacionEdad(String clasificacionEdad){
        this.clasificacionEdad = clasificacionEdad;
    }
}
