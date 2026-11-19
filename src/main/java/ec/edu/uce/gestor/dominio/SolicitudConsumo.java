package ec.edu.uce.gestor.dominio;

import java.util.Date;
import java.util.Objects;

public class SolicitudConsumo {

    private int idSolicitud;
    private String estado;
    private Date fechaSolicitud;
    private String tipoContenido;

    // Constructor por defecto
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
        if (idSolicitud > 0) { // Solo acepta valores mayores a 0
            this.idSolicitud = idSolicitud;
            return true;
        }
        return false; // Retorna false si es negativo o cero (rechaza el cambio)
    }

    public String getEstado() {
        return estado;
    }

    public boolean setEstado(String estado) {
        // Si el estado es nulo, vacío, o contiene números, rechazarlo
        if (estado == null || estado.matches(".*\\d.*")) {
            return false; // Retorna false (lo que hará que el assertFalse de la prueba pase)
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
        // Si contiene símbolos o caracteres especiales (ej. '@'), rechazarlo
        // Puedes usar una expresión regular que solo permita letras y espacios
        if (tipoContenido == null || !tipoContenido.matches("[a-zA-ZáéíóÁÉÍÓúñÑ ]+")) {
            return false;
        }
        this.tipoContenido = tipoContenido;
        return true;
    }

        @Override
        public String toString() {
            return "id: "+this.getIdSolicitud()+"Estado: "+ this.getEstado()+"Fecha: "+ this.getFechaSolicitud()+"Tipo de contenido:"+ this.getTipoContenido()+ super.toString();
        }


    // --- SOBRECARGA DE MÉTODOS ---
    public boolean actualizarEstado(String nuevoEstado) {
        return setEstado(nuevoEstado);
    }

    public boolean actualizarEstado(String nuevoEstado, Date nuevaFecha) {
        return setEstado(nuevoEstado) && setFechaSolicitud(nuevaFecha);
    }

    // --- EQUALS Y HASHCODE (Regla de negocio: mismo idSolicitud y tipoContenido) ---
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