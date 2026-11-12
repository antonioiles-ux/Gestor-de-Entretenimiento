package ec.edu.uce.gestor.dominio;

public class Valoracion {

    private int idValoracion;
    private int calificacion;
    private String fechaValoracion;

    public Valoracion() {
        this.idValoracion = 0;
        this.calificacion = 1;
        this.fechaValoracion = "sin fecha";
    }

    public Valoracion(int idValoracion, int calificacion, String fechaValoracion) {
        this.idValoracion = idValoracion;
        this.calificacion = calificacion;
        this.fechaValoracion = fechaValoracion;
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
}