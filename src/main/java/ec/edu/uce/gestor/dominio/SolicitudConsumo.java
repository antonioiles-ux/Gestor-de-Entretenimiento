package ec.edu.uce.gestor.dominio;

import java.util.Date;

public class SolicitudConsumo {

    private int idSolicitud;
    private String estado;
    private Date fechaSolicitud;
    private String tipoContenido;

    // Constructor por defecto (¡Aquí estaba el error de null!)
    public SolicitudConsumo() {
        this.idSolicitud = 0;
        this.estado = "Pendiente";
        this.fechaSolicitud = new Date();
        this.tipoContenido = "Sin asignar";
    }

    public SolicitudConsumo(int idSolicitud, String estado, Date fechaSolicitud, String tipoContenido) {
        this.idSolicitud = idSolicitud;
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
        this.tipoContenido = tipoContenido;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    // Setters cambiados a boolean
    public boolean setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
        return true;
    }

    public String getEstado() {
        return estado;
    }

    public boolean setEstado(String estado) {
        this.estado = estado;
        return true;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public boolean setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
        return true;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public boolean setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
        return true;
    }
}
