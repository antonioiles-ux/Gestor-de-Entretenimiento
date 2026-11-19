package ec.edu.uce.gestor.dominio;

import java.util.Date;
import ec.edu.uce.gestor.Util.Validaciones;
import java.util.Objects;

public class Pelicula extends Contenido {
    private int duracion;
    private String clasificacion;

    public Pelicula() {
        this(0, "sin titulo", "sin autor", "sin genero", null, 0, "sin clasificacion");
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

    public boolean setClasificacion(String clasificacion) {
        if (!Validaciones.validarClasificacionEdad(clasificacion)) {
            return false;
        }
        this.clasificacion = clasificacion.trim();
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "Duracion: " + this.getDuracion() + ", Clasificacion: " + this.getClasificacion() + ", ";
    }

    // --- POLIMORFISMO / SOBREESCRITURA (Heredado de Contenido) ---
    @Override
    public int calcularTiempoConsumo() {
        return this.duracion;
    }

    // --- SOBRECARGA DE MÉTODOS (incluye referencia de objeto) ---
    public int calcularTiempoConsumo(int repeticiones) {
        if (repeticiones <= 0) {
            return 0;
        }
        return this.duracion * repeticiones;
    }

    public boolean tieneMismaClasificacion(Pelicula otraPelicula) {
        if (otraPelicula == null || this.clasificacion == null) {
            return false;
        }
        return this.clasificacion.equalsIgnoreCase(otraPelicula.clasificacion);
    }

    // --- EQUALS Y HASHCODE ---
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Pelicula p)) {
            return false;
        }
        return this.duracion == p.duracion
                && Objects.equals(this.clasificacion, p.clasificacion)
                && super.equals(p);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), duracion, clasificacion);
    }
}