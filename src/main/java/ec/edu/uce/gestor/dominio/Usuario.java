package ec.edu.uce.gestor.dominio;

import ec.edu.uce.gestor.Util.Validaciones;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Usuario {

    // =========================================================================
    // 1. ATRIBUTOS
    // =========================================================================
    private String nombreCompleto;
    private int idUsuario;

    // Composición (1 a 1): cada usuario crea y posee su propia Cuenta
    private Cuenta cuenta;

    // Asociación (1 a muchos): solicitudes de consumo del usuario
    private SolicitudConsumo[] solicitudes;
    private int cantidadSolicitudes;

    // =========================================================================
    // 2. CONSTRUCTORES
    // =========================================================================
    public Usuario() {
        this(50);
    }

    public Usuario(int capacidadSolicitudes) {
        if (capacidadSolicitudes <= 0) {
            capacidadSolicitudes = 50;
        }
        this.nombreCompleto = "sin nombre";
        this.idUsuario = 0;
        this.cuenta = new Cuenta();
        this.solicitudes = new SolicitudConsumo[capacidadSolicitudes];
        this.cantidadSolicitudes = 0;
    }

    public Usuario(String nombreCompleto, int idUsuario) {
        this(50);
        setNombreCompleto(nombreCompleto);
        setIdUsuario(idUsuario);
    }

    public Usuario(String nombreCompleto, int idUsuario, Cuenta cuenta) {
        this(nombreCompleto, idUsuario);
        if (cuenta != null) {
            this.cuenta = cuenta;
        }
    }

    // =========================================================================
    // 3. GETTERS Y SETTERS
    // =========================================================================
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public boolean setNombreCompleto(String nombreCompleto) {
        if (!Validaciones.validarLetras(nombreCompleto)) {
            return false;
        }
        this.nombreCompleto = nombreCompleto.trim();
        return true;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public boolean setIdUsuario(int idUsuario) {
        if (idUsuario <= 0) {
            return false;
        }
        this.idUsuario = idUsuario;
        return true;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public boolean setCuenta(Cuenta cuenta) {
        if (cuenta == null) {
            return false;
        }
        this.cuenta = cuenta;
        return true;
    }

    public int getCantidadSolicitudes() {
        return cantidadSolicitudes;
    }

    // =========================================================================
    // 4. MÉTODOS SOBRECARGA
    // =========================================================================
    public String obtenerRol() {
        return "Usuario Registrado";
    }

    public String mostrarPerfil() {
        return this.toString();
    }

    public String mostrarPerfil(boolean incluirRol) {
        if (incluirRol) {
            return this.toString() + ", Rol: " + obtenerRol();
        }
        return mostrarPerfil();
    }

    public boolean esMismoUsuario(Usuario otroUsuario) {
        if (otroUsuario == null) {
            return false;
        }
        return this.idUsuario == otroUsuario.idUsuario;
    }

    private boolean esSolicitudDuplicada(SolicitudConsumo solicitud) {
        for (SolicitudConsumo actual : listarSolicitudes()) {
            if (actual.getIdSolicitud() == solicitud.getIdSolicitud()) {
                return true;
            }
            if ("Pendiente".equalsIgnoreCase(actual.getEstado())
                    && "Pendiente".equalsIgnoreCase(solicitud.getEstado())
                    && actual.getTipoContenido() != null
                    && solicitud.getTipoContenido() != null
                    && !actual.getTipoContenido().equalsIgnoreCase("Sin asignar")
                    && actual.getTipoContenido().trim().equalsIgnoreCase(solicitud.getTipoContenido().trim())) {

                if (actual.getCantidadContenidos() == 0 && solicitud.getCantidadContenidos() == 0) {
                    return true;
                }
                for (Contenido c : solicitud.listarContenidos()) {
                    if (actual.buscarContenido(c.getTitulo(), c.getAutor()) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // =========================================================================
    // 5. CRUD DE SOLICITUDES DE CONSUMO (ASOCIACIÓN: SolicitudConsumo[])
    // =========================================================================

    // CREAR
    public boolean agregarSolicitud(SolicitudConsumo solicitud) {
        if (solicitud == null || cantidadSolicitudes == solicitudes.length
                || esSolicitudDuplicada(solicitud)) {
            return false;
        }
        solicitud.setSolicitante(this);
        solicitudes[cantidadSolicitudes] = solicitud;
        cantidadSolicitudes++;
        return true;
    }

    // LISTAR (con Arrays.copyOf para evitar huecos null)
    public SolicitudConsumo[] listarSolicitudes() {
        return Arrays.copyOf(solicitudes, cantidadSolicitudes);
    }

    // BUSCAR (por atributo natural: tipo de contenido)
    public SolicitudConsumo buscarSolicitud(String tipoContenido) {
        if (tipoContenido == null || tipoContenido.isBlank()) {
            return null;
        }
        for (SolicitudConsumo s : solicitudes) {
            if (s != null && tipoContenido.trim().equalsIgnoreCase(s.getTipoContenido())) {
                return s;
            }
        }
        return null;
    }

    // BUSCAR (sobrecarga por referencia de objeto)
    public SolicitudConsumo buscarSolicitud(SolicitudConsumo solicitud) {
        if (solicitud == null) {
            return null;
        }
        for (SolicitudConsumo s : solicitudes) {
            if (s != null && s.equals(solicitud)) {
                return s;
            }
        }
        return null;
    }

    // BUSCAR (sobrecarga por ID)
    public SolicitudConsumo buscarSolicitud(int idSolicitud) {
        for (SolicitudConsumo s : solicitudes) {
            if (s != null && s.getIdSolicitud() == idSolicitud) {
                return s;
            }
        }
        return null;
    }

    // ACTUALIZAR (por atributo natural: tipo de contenido)
    public boolean actualizarEstadoSolicitud(String tipoContenido, String nuevoEstado) {
        if (nuevoEstado == null || nuevoEstado.isBlank()) {
            return false;
        }
        SolicitudConsumo encontrada = buscarSolicitud(tipoContenido);
        if (encontrada == null) {
            return false;
        }
        if (encontrada.setEstado(nuevoEstado)) {
            encontrada.setFechaSolicitud(new Date());
            return true;
        }
        return false;
    }

    // ACTUALIZAR (sobrecarga por referencia de objeto)
    public boolean actualizarEstadoSolicitud(SolicitudConsumo solicitud, String nuevoEstado) {
        if (nuevoEstado == null || nuevoEstado.isBlank()) {
            return false;
        }
        SolicitudConsumo encontrada = buscarSolicitud(solicitud);
        if (encontrada == null) {
            return false;
        }
        if (encontrada.setEstado(nuevoEstado)) {
            encontrada.setFechaSolicitud(new Date());
            return true;
        }
        return false;
    }

    // ACTUALIZAR (sobrecarga por ID)
    public boolean actualizarEstadoSolicitud(int idSolicitud, String nuevoEstado) {
        if (nuevoEstado == null || nuevoEstado.isBlank()) {
            return false;
        }
        SolicitudConsumo encontrada = buscarSolicitud(idSolicitud);
        if (encontrada == null) {
            return false;
        }
        if (encontrada.setEstado(nuevoEstado)) {
            encontrada.setFechaSolicitud(new Date());
            return true;
        }
        return false;
    }

    public boolean editarSolicitud(int idSolicitud, String nuevoEstado) {
        return actualizarEstadoSolicitud(idSolicitud, nuevoEstado);
    }

    // ELIMINAR (por atributo natural con desplazamiento a la izquierda)
    public boolean eliminarSolicitud(String tipoContenido) {
        if (tipoContenido == null || tipoContenido.isBlank()) {
            return false;
        }
        for (int i = 0; i < cantidadSolicitudes; i++) {
            if (solicitudes[i] != null && tipoContenido.trim().equalsIgnoreCase(solicitudes[i].getTipoContenido())) {
                for (int j = i; j < cantidadSolicitudes - 1; j++) {
                    solicitudes[j] = solicitudes[j + 1];
                }
                solicitudes[cantidadSolicitudes - 1] = null;
                cantidadSolicitudes--;
                return true;
            }
        }
        return false;
    }

    // ELIMINAR (sobrecarga por referencia de objeto)
    public boolean eliminarSolicitud(SolicitudConsumo solicitud) {
        if (solicitud == null) {
            return false;
        }
        for (int i = 0; i < cantidadSolicitudes; i++) {
            if (solicitudes[i] != null && solicitudes[i].equals(solicitud)) {
                for (int j = i; j < cantidadSolicitudes - 1; j++) {
                    solicitudes[j] = solicitudes[j + 1];
                }
                solicitudes[cantidadSolicitudes - 1] = null;
                cantidadSolicitudes--;
                return true;
            }
        }
        return false;
    }

    // ELIMINAR (sobrecarga por ID)
    public boolean eliminarSolicitud(int idSolicitud) {
        for (int i = 0; i < cantidadSolicitudes; i++) {
            if (solicitudes[i] != null && solicitudes[i].getIdSolicitud() == idSolicitud) {
                for (int j = i; j < cantidadSolicitudes - 1; j++) {
                    solicitudes[j] = solicitudes[j + 1];
                }
                solicitudes[cantidadSolicitudes - 1] = null;
                cantidadSolicitudes--;
                return true;
            }
        }
        return false;
    }

    // =========================================================================
    // 6. MÉTODOS SOBRESCRITOS (@Override)
    // =========================================================================
    @Override
    public String toString() {
        return "Id: " + this.getIdUsuario() + ", Nombre: " + this.getNombreCompleto();
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Usuario u)) {
            return false;
        }
        return this.idUsuario == u.idUsuario
                && Objects.equals(this.nombreCompleto, u.nombreCompleto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombreCompleto);
    }
}