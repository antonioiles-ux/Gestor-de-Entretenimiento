package ec.edu.uce.gestor.dominio;

import ec.edu.uce.gestor.Util.Validaciones;

import java.util.Objects;
import java.util.Date;

public class Libro extends Contenido {

    private int numeroPaginas;
    private String descripcion;

    public Libro() {
        super();
        this.numeroPaginas = 0;
        this.descripcion = "Sin descripcion";
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
        if (numeroPaginas > 0 && Validaciones.validarNumeros(String.valueOf(numeroPaginas))) {
            this.numeroPaginas = numeroPaginas;
            return true;
        }
        return false;
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
    // Estima el tiempo total de lectura en minutos (aprox. 2 min por página)
    @Override
    public int calcularTiempoConsumo() {
        return this.numeroPaginas * 2;
    }

    // --- SOBRECARGA DE MÉTODOS ---
    // Permite calcular el tiempo indicando la velocidad de lectura en minutos por página

    public int calcularTiempoConsumo(int minutosPorPagina) {
        if (minutosPorPagina <= 0) {
            return 0;
        }
        return this.numeroPaginas * minutosPorPagina;
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

