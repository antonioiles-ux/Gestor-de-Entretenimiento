package ec.edu.uce.gestor.dominio;

import ec.edu.uce.gestor.Util.Validaciones;
import java.util.Date;
import java.util.Objects;

public class Usuario {

    private String nombreCompleto;
    private int idUsuario;

    // RELACIÓN: COMPOSICIÓN (1 a 1): cada usuario crea y posee su propia Cuenta
    private Cuenta cuenta;

    // RELACIÓN: ASOCIACIÓN / UNO A MUCHOS (arreglo): un usuario tiene varias solicitudes
    private SolicitudConsumo[] solicitudes;
    private int cantidadSolicitudes;

    // RELACIÓN: ASOCIACIÓN / UNO A MUCHOS (arreglo): contenidos registrados por el usuario
    private Contenido[] contenidos;
    private int cantidadContenidos;

    public Usuario() {
        this.nombreCompleto = "sin nombre";
        this.idUsuario = 0;
        this.cuenta = new Cuenta();
        this.solicitudes = new SolicitudConsumo[50];
        this.cantidadSolicitudes = 0;
        this.contenidos = new Contenido[50];
        this.cantidadContenidos = 0;
    }

    public Usuario(String nombreCompleto, int idUsuario) {
        this();
        setNombreCompleto(nombreCompleto);
        setIdUsuario(idUsuario);
    }

    // Constructor sobrecargado con la cuenta
    public Usuario(String nombreCompleto, int idUsuario, Cuenta cuenta) {
        this(nombreCompleto, idUsuario);
        if (cuenta != null) {
            this.cuenta = cuenta;
        }
    }

    // ---------- GET / SET ----------
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

    public int getCantidadContenidos() {
        return cantidadContenidos;
    }

    // ---------- POLIMORFISMO ----------
    public String obtenerRol() {
        return "Usuario Registrado";
    }

    // ---------- SOBRECARGA ----------
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

    // =========================================================
    // MÉTODOS AUXILIARES PARA VALIDAR DUPLICADOS
    // =========================================================

    private boolean esSolicitudDuplicada(SolicitudConsumo solicitud) {
        for (int i = 0; i < cantidadSolicitudes; i++) {
            SolicitudConsumo actual = solicitudes[i];

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

    protected boolean esContenidoDuplicado(Contenido contenido) {
        for (int i = 0; i < cantidadContenidos; i++) {
            Contenido actual = contenidos[i];

            if (actual.getIdContenido() == contenido.getIdContenido()) {
                return true;
            }

            if (actual.getTitulo() != null && contenido.getTitulo() != null
                    && actual.getAutor() != null && contenido.getAutor() != null
                    && !contenido.getTitulo().equalsIgnoreCase("sin titulo")) {

                boolean mismoTitulo = actual.getTitulo().trim().equalsIgnoreCase(contenido.getTitulo().trim());
                boolean mismoAutor = actual.getAutor().trim().equalsIgnoreCase(contenido.getAutor().trim());

                if (mismoTitulo && mismoAutor) {
                    if (actual instanceof Videojuego v1 && contenido instanceof Videojuego v2) {
                        if (v1.getPlataforma() != null && v2.getPlataforma() != null
                                && v1.getPlataforma().trim().equalsIgnoreCase(v2.getPlataforma().trim())) {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // =========================================================
    // CRUD 1: SOLICITUDES (SolicitudConsumo[])
    // =========================================================

    // CREAR
    public boolean agregarSolicitud(SolicitudConsumo solicitud) {
        if (solicitud == null || cantidadSolicitudes == solicitudes.length
                || esSolicitudDuplicada(solicitud)) {
            return false;
        }
        solicitud.setSolicitante(this);
        solicitudes[cantidadSolicitudes++] = solicitud;
        return true;
    }

    // BUSCAR
    public SolicitudConsumo buscarSolicitud(int idSolicitud) {
        for (int i = 0; i < cantidadSolicitudes; i++) {
            if (solicitudes[i].getIdSolicitud() == idSolicitud) {
                return solicitudes[i];
            }
        }
        return null;
    }

    // EDITAR
    public boolean editarSolicitud(int idSolicitud, String nuevoEstado) {
        return actualizarEstadoSolicitud(idSolicitud, nuevoEstado);
    }

    public boolean actualizarEstadoSolicitud(int idSolicitud, String nuevoEstado) {
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

    // ELIMINAR
    public boolean eliminarSolicitud(int idSolicitud) {
        for (int i = 0; i < cantidadSolicitudes; i++) {
            if (solicitudes[i].getIdSolicitud() == idSolicitud) {
                for (int j = i; j < cantidadSolicitudes - 1; j++) {
                    solicitudes[j] = solicitudes[j + 1];
                }
                solicitudes[--cantidadSolicitudes] = null;
                return true;
            }
        }
        return false;
    }

    // LISTAR (copia defensiva)
    public SolicitudConsumo[] listarSolicitudes() {
        SolicitudConsumo[] copia = new SolicitudConsumo[cantidadSolicitudes];
        for (int i = 0; i < cantidadSolicitudes; i++) {
            copia[i] = solicitudes[i];
        }
        return copia;
    }

    // =========================================================
    // CRUD 2: CONTENIDOS DEL USUARIO (Contenido[])
    // =========================================================

    // CREAR
    public boolean agregarContenido(Contenido contenido) {
        if (contenido == null || cantidadContenidos == contenidos.length
                || esContenidoDuplicado(contenido)) {
            return false;
        }
        contenidos[cantidadContenidos++] = contenido;
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

    // EDITAR
    public boolean editarContenido(int idContenido, String nuevoTitulo) {
        Contenido c = buscarContenido(idContenido);
        if (c == null) {
            return false;
        }
        return c.setTitulo(nuevoTitulo);
    }

    // ELIMINAR
    public boolean eliminarContenido(int idContenido) {
        for (int i = 0; i < cantidadContenidos; i++) {
            if (contenidos[i].getIdContenido() == idContenido) {
                for (int j = i; j < cantidadContenidos - 1; j++) {
                    contenidos[j] = contenidos[j + 1];
                }
                contenidos[--cantidadContenidos] = null;
                return true;
            }
        }
        return false;
    }

    // LISTAR (copia defensiva)
    public Contenido[] listarContenidos() {
        Contenido[] copia = new Contenido[cantidadContenidos];
        for (int i = 0; i < cantidadContenidos; i++) {
            copia[i] = contenidos[i];
        }
        return copia;
    }

    // ---------- TOSTRING ----------
    @Override
    public String toString() {
        return "Id: " + this.getIdUsuario() + ", Nombre: " + this.getNombreCompleto();
    }

    // ---------- EQUALS Y HASHCODE ----------
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