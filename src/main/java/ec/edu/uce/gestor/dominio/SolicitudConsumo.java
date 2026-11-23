package ec.edu.uce.gestor.dominio;

import java.util.Date;
import java.util.Objects;

public class SolicitudConsumo {

    private int idSolicitud;
    private String estado;
    private Date fechaSolicitud;
    private String tipoContenido;

    // RELACIÓN: ASOCIACIÓN (Usuario que realiza la solicitud)
    private Usuario solicitante;

    // RELACIÓN: AGREGACIÓN (Contenidos del catálogo incluidos en la solicitud; existen independientemente)
    private Contenido[] contenidos;
    private int cantidadContenidos;

    public SolicitudConsumo() {
        this(0, "Pendiente", new Date(), "Sin asignar");
    }


    public SolicitudConsumo(int idSolicitud, String estado, Date fechaSolicitud, String tipoContenido) {
        this.idSolicitud = idSolicitud;
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
        this.tipoContenido = tipoContenido;
        this.solicitante = null;
        this.contenidos = new Contenido[20];
        this.cantidadContenidos = 0;
    }

    // Constructor sobrecargado con Asociación
    public SolicitudConsumo(int idSolicitud, String estado, Date fechaSolicitud, String tipoContenido, Usuario solicitante) {
        this(idSolicitud, estado, fechaSolicitud, tipoContenido);
        this.solicitante = solicitante;
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


    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    // =========================================================================
    // AGREGACIÓN: GESTIÓN DE CONTENIDOS EN LA SOLICITUD (Contenido[])
    // =========================================================================

    // CREAR
    public boolean agregarContenido(Contenido contenido) {
        if (contenido == null || cantidadContenidos == contenidos.length
                || buscarContenido(contenido.getIdContenido()) != null) {
            return false;
        }
        contenidos[cantidadContenidos] = contenido;
        cantidadContenidos++;
        return true;
    }

    // BUSCAR
    public Contenido buscarContenido(int idContenido) {
        for (int i = 0; i < cantidadContenidos; i++) {
            if (contenidos[i].getIdContenido() == idContenido) {
                return contenidos[i];
            }
        }
        return null;
    }

    // EDITAR (reemplazar un contenido de la solicitud por otro del catálogo)
    public boolean reemplazarContenido(int idContenidoActual, Contenido nuevoContenido) {
        if (nuevoContenido == null || buscarContenido(nuevoContenido.getIdContenido()) != null) {
            return false;
        }
        for (int i = 0; i < cantidadContenidos; i++) {
            if (contenidos[i].getIdContenido() == idContenidoActual) {
                contenidos[i] = nuevoContenido;
                return true;
            }
        }
        return false;
    }

    // ELIMINAR (con desplazamiento a la izquierda)
    public boolean eliminarContenido(int idContenido) {
        for (int i = 0; i < cantidadContenidos; i++) {
            if (contenidos[i].getIdContenido() == idContenido) {
                for (int j = i; j < cantidadContenidos - 1; j++) {
                    contenidos[j] = contenidos[j + 1];
                }
                contenidos[cantidadContenidos - 1] = null;
                cantidadContenidos--;
                return true;
            }
        }
        return false;
    }

    // LISTAR (copia exacta)
    public void visualizarContenido() {

        for (int i = 0; i < cantidadContenidos; i++) {
            System.out.println(contenidos[i]);
        }
    }

    public Contenido[] listarContenidos() {
        Contenido[] copia = new Contenido[cantidadContenidos];
        for (int i = 0; i < cantidadContenidos; i++) {
            copia[i] = contenidos[i];
        }
        return copia;
    }

    public int getCantidadContenidos() {
        return cantidadContenidos;
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