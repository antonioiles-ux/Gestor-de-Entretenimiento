package ec.edu.uce.gestor.dominio;

import java.util.Date;
import ec.edu.uce.gestor.Util.Validaciones;
import java.util.Objects;

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
        if (!Validaciones.validarClasificacionEdad(clasificacionEdad)) {
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


    // --- POLIMORFISMO / SOBREESCRITURA (Heredado de Contenido) ---
    // Estima una campaña base de 10 horas (600 minutos)
    @Override
    public int calcularTiempoConsumo() {
        return 600;
    }

    // --- SOBRECARGA DE MÉTODOS ---
    // Calcula el tiempo total en minutos según las horas jugadas ingresadas
    public int calcularTiempoConsumo(int horasJugadas) {
        if (horasJugadas <= 0) {
            return 0;
        }
        return horasJugadas * 60;
    }

    // --- EQUALS Y HASHCODE ---
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Videojuego v)) {
            return false;
        }
        return Objects.equals(this.plataforma, v.plataforma)
                && Objects.equals(this.clasificacionEdad, v.clasificacionEdad)
                && super.equals(v);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), plataforma, clasificacionEdad);
    }
}
