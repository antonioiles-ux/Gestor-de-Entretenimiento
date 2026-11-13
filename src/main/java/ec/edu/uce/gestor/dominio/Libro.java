package ec.edu.uce.gestor.dominio;

import ec.edu.uce.gestor.Util.Validaciones;

public class Libro extends Contenido {

    private int numeroPaginas;
    private String descripcion;

    public Libro() {
    }

    public Libro(int numeroPaginas, String descripcion) {
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
        return "Número de páginas: " + this.getNumeroPaginas() + ", Descripcion: " + this.getDescripcion() + ", " + super.toString();

    }

}