package ec.edu.uce.gestor.dominio;

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

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString(){
        return "Numero paginas: "+this.getNumeroPaginas()+" Descripcion: "+ this.getDescripcion();
    }
}