package ec.edu.uce.gestor.dominio;

import java.util.Date;
import java.util.Objects;

public class SolicitudConsumo {

    private int idSolicitud;
    private String estado;
    private Date fechaSolicitud;
    private String tipoContenido;

    public SolicitudConsumo() {
        this(0, "Pendiente", new Date(), "Sin asignar");
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

    public boolean setIdSolicitud(int idSolicitud) {
        if (idSolicitud > 0) {
            this.idSolicitud = idSolicitud;
            return true;
        }
        return false;
    }

    public String getEstado() {
        return estado;
    }

    public boolean setEstado(String estado) {
        if (estado == null || estado.matches(".*\\d.*")) {
            return false;
        }
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
        if (tipoContenido == null || !tipoContenido.matches("[a-zA-ZáéíóÁÉÍÓúñÑ ]+")) {
            return false;
        }
        this.tipoContenido = tipoContenido;
        return true;
    }

    @Override
    public String toString() {
        return "id: " + this.getIdSolicitud() + "Estado: " + this.getEstado() + "Fecha: " + this.getFechaSolicitud() + "Tipo de contenido:" + this.getTipoContenido();
    }

    // --- SOBRECARGA DE MÉTODOS (incluye referencia de objeto) ---
    public boolean actualizarEstado(String nuevoEstado) {
        return setEstado(nuevoEstado);
    }

    public boolean actualizarEstado(String nuevoEstado, Date nuevaFecha) {
        return setEstado(nuevoEstado) && setFechaSolicitud(nuevaFecha);
    }

    public boolean tieneMismoTipoContenido(SolicitudConsumo otraSolicitud) {
        if (otraSolicitud == null || this.tipoContenido == null) {
            return false;
        }
        return this.tipoContenido.equalsIgnoreCase(otraSolicitud.tipoContenido);
    }

    // --- EQUALS Y HASHCODE ---
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof SolicitudConsumo s)) {
            return false;
        }
        return this.idSolicitud == s.idSolicitud
                && Objects.equals(this.tipoContenido, s.tipoContenido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSolicitud, tipoContenido);
    }
}