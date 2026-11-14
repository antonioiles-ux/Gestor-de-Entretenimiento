package ec.edu.uce.gestor.dominio;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Opinion {
    private int idOpinion;
    private String comentario;
    private Date fechaRegistro;
    private Date fechaEdicion;

    public Opinion() {
        this.idOpinion = 0;
        this.comentario = "sin comentario";
        this.fechaRegistro = null;
        this.fechaEdicion = null;
    }

    // Opinión nueva: la fecha de registro es automática
    public Opinion(int idOpinion, String comentario) {
        this.idOpinion = idOpinion;
        this.comentario = comentario;
        this.fechaRegistro = new Date();
        this.fechaEdicion = null;
    }

    // Opinión ya guardada (carga de datos y pruebas con fechas fijas)
    public Opinion(int idOpinion, String comentario, Date fechaRegistro, Date fechaEdicion) {
        this.idOpinion = idOpinion;
        this.comentario = comentario;
        this.fechaRegistro = fechaRegistro;
        this.fechaEdicion = fechaEdicion;
    }

    public int getIdOpinion() {
        return idOpinion;
    }
    public boolean setIdOpinion(int idOpinion) {
        if (idOpinion <= 0) {
            return false;
        }
        this.idOpinion = idOpinion;
        return true;
    }

    public String getComentario() {
        return comentario;
    }
    public boolean setComentario(String comentario) {
        if (comentario == null || comentario.isEmpty()) {
            return false;
        }
        this.comentario = comentario;
        this.fechaEdicion = new Date();   // automática al editar
        return true;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public Date getFechaEdicion() {
        return fechaEdicion;
    }

    private String formatear(Date fecha, String textoPorDefecto) {
        if (fecha == null) {
            return textoPorDefecto;
        }
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    @Override
    public String toString() {
        return "Id opinion:" + this.getIdOpinion()
                + "  Comentario:" + this.getComentario()
                + "  Fecha de Registro:" + formatear(this.getFechaRegistro(), "sin fecha de registro")
                + "  Fecha de Edicion:" + formatear(this.getFechaEdicion(), "sin fecha de edicion");
    }
}