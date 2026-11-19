package ec.edu.uce.gestor.dominio;

import ec.edu.uce.gestor.Util.Validaciones;

import java.util.Objects;
import java.util.Date;

public class Libro extends Contenido {

    private int numeroPaginas;
    private String descripcion;

    public Libro() {
        this(0, "sin titulo", "sin autor", "sin genero", null, 0, "Sin descripcion");
    }

    public Libro(int idContenido, String titulo, String autor, String genero, Date fechaPublicacion, int numeroPaginas, String descripcion) {
        super(idContenido, titulo, autor, genero, fechaPublicacion);
        this.numeroPaginas = numeroPaginas;
        this.descripcion = descripcion;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public boolean setNumeroPaginas(int numeroPaginas) {
        if (numeroPaginas <= 0) {
            return false;
        }
        this.numeroPaginas = numeroPaginas;
        return true;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean setDescripcion(String descripcion) {
        if (!Validaciones.validarTextoConLetras(descripcion)) {
            return false;
        }
        this.descripcion = descripcion.trim();
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "Número de páginas: " + this.getNumeroPaginas() + ", Descripcion: " +
                this.getDescripcion() + ", ";
    }

    // --- POLIMORFISMO / SOBREESCRITURA (Heredado de Contenido) ---
    @Override
    public int calcularTiempoConsumo() {
        return this.numeroPaginas * 2;
    }

    // --- SOBRECARGA DE MÉTODOS (incluye referencia de objeto) ---
    public int calcularTiempoConsumo(int minutosPorPagina) {
        if (minutosPorPagina <= 0) {
            return 0;
        }
        return this.numeroPaginas * minutosPorPagina;
    }

    public boolean esMasExtensoQue(Libro otroLibro) {
        if (otroLibro == null) {
            return false;
        }
        return this.numeroPaginas > otroLibro.numeroPaginas;
    }

    // --- EQUALS Y HASHCODE ---
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Libro l)) {
            return false;
        }
        return this.numeroPaginas == l.numeroPaginas && super.equals(l);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numeroPaginas);
    }
}
