package ec.edu.uce.gestor.dominio;

public class Valoracion {
    private int idValoracion;
    private int calificacion;
    private String fechaValoracion;

    public Valoracion() {
    }

    public Valoracion(int idValoracion, int calificacion, String fechaValoracion) {
        this.idValoracion = idValoracion;
        this.calificacion = calificacion;
        this.fechaValoracion = fechaValoracion;
    }

    public int getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(int idValoracion) {
        this.idValoracion = idValoracion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getFechaValoracion() {
        return fechaValoracion;
    }

    public void setFechaValoracion(String fechaValoracion) {
        this.fechaValoracion = fechaValoracion;
    }
}
