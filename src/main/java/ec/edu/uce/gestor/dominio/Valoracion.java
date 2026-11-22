package ec.edu.uce.gestor.dominio;

import java.util.Objects;

public class Valoracion {

    private int idValoracion;
    private int calificacion;
    private String fechaValoracion;
    private Usuario autor;
    private Contenido contenido;

    public Valoracion() {
        this(0, 1, "sin fecha");
    }

    public Valoracion(int idValoracion, int calificacion, String fechaValoracion) {
        this.idValoracion = idValoracion;
        this.calificacion = calificacion;
        this.fechaValoracion = fechaValoracion;
    }

    // Constructor sobrecargado con Asociación
    public Valoracion(int idValoracion, int calificacion, String fechaValoracion,
                      Usuario autor, Contenido contenido) {
        this(idValoracion, calificacion, fechaValoracion);
        this.autor = autor;
        this.contenido = contenido;
    }


    public int getIdValoracion() {
        return idValoracion;
    }

    public boolean setIdValoracion(int idValoracion) {
        if (idValoracion <= 0) {
            return false;
        }
        this.idValoracion = idValoracion;
        return true;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public boolean setCalificacion(int calificacion) {
        if (calificacion < 1 || calificacion > 5) {
            return false;
        }
        this.calificacion = calificacion;
        return true;
    }

    public String getFechaValoracion() {
        return fechaValoracion;
    }

    public boolean setFechaValoracion(String fechaValoracion) {
        if (fechaValoracion == null || fechaValoracion.equals("")) {
            return false;
        }
        this.fechaValoracion = fechaValoracion;
        return true;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }


    @Override
    public String toString() {
        return "Id valoracion: " + this.getIdValoracion() + ", Calificacion: " + this.getCalificacion() +
                ", Fecha de valoracion: " + this.getFechaValoracion();
    }

    // --- SOBRECARGA DE MÉTODOS (incluye referencia de objeto) ---
    public boolean actualizarValoracion(int nuevaCalificacion) {
        return setCalificacion(nuevaCalificacion);
    }

    public boolean actualizarValoracion(int nuevaCalificacion, String nuevaFecha) {
        return setCalificacion(nuevaCalificacion) && setFechaValoracion(nuevaFecha);
    }

    public boolean esMejorQue(Valoracion otraValoracion) {
        if (otraValoracion == null) {
            return false;
        }
        return this.calificacion > otraValoracion.calificacion;
    }

    // --- EQUALS Y HASHCODE ---
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Valoracion v)) {
            return false;
        }
        return this.idValoracion == v.idValoracion
                && this.calificacion == v.calificacion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idValoracion, calificacion);
    }
}