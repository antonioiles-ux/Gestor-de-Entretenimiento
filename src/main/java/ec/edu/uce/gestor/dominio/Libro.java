package ec.edu.uce.gestor.dominio;

import ec.edu.uce.gestor.Util.Validaciones;

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
        if (descripcion != null && !descripcion.trim().isEmpty()) {
            this.descripcion = descripcion;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return super.toString() + "Número de páginas: " + this.getNumeroPaginas() + ", Descripcion: " +
                this.getDescripcion() + ", ";

    }
}

