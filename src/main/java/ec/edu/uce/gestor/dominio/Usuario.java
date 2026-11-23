package ec.edu.uce.gestor.dominio;

import ec.edu.uce.gestor.Util.Validaciones;
import java.util.Objects;

public class Usuario {

    private String nombreCompleto;
    private int idUsuario;

    // RELACIÓN UNO A UNO (objeto): cada usuario tiene UNA cuenta
    private Cuenta cuenta;

    // RELACIÓN UNO A MUCHOS (arreglo): un usuario tiene varias solicitudes
    private SolicitudConsumo[] solicitudes;
    private int cantidadSolicitudes;

    public Usuario() {
        this.nombreCompleto = "sin nombre";
        this.idUsuario = 0;
        this.solicitudes = new SolicitudConsumo[50];
        this.cantidadSolicitudes = 0;
    }

    public Usuario(String nombreCompleto, int idUsuario) {
        this();
        setNombreCompleto(nombreCompleto);
        setIdUsuario(idUsuario);
    }

    // Constructor con la cuenta (uno a uno)
    public Usuario(String nombreCompleto, int idUsuario, Cuenta cuenta) {
        this(nombreCompleto, idUsuario);
        this.cuenta = cuenta;
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
    // CRUD DE SOLICITUDES (SolicitudConsumo[])
    // =========================================================

    // CREAR (con validación de duplicados)
    public boolean agregarSolicitud(SolicitudConsumo solicitud) {
        if (solicitud == null || cantidadSolicitudes == solicitudes.length
                || esSolicitudDuplicada(solicitud)) {
            return false;
        }
        solicitud.setSolicitante(this); // vincula la solicitud con su dueño
        solicitudes[cantidadSolicitudes++] = solicitud;
        return true;
    }

    // CONSULTAR
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
        SolicitudConsumo s = buscarSolicitud(idSolicitud);
        if (s == null) {
            return false;
        }
        return s.setEstado(nuevoEstado);
    }

    // ELIMINAR (desplaza a la izquierda)
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

    // LISTAR (copia para proteger el arreglo interno)
    public SolicitudConsumo[] listarSolicitudes() {
        SolicitudConsumo[] copia = new SolicitudConsumo[cantidadSolicitudes];
        for (int i = 0; i < cantidadSolicitudes; i++) {
            copia[i] = solicitudes[i];
        }
        return copia;
    }

    // VALIDACIÓN DE DUPLICADOS: mismo ID, o mismo tipo de contenido mientras siga pendiente
    private boolean esSolicitudDuplicada(SolicitudConsumo nueva) {
        for (int i = 0; i < cantidadSolicitudes; i++) {
            SolicitudConsumo s = solicitudes[i];
            if (s.getIdSolicitud() == nueva.getIdSolicitud()) {
                return true;
            }
            if (s.getTipoContenido().equalsIgnoreCase(nueva.getTipoContenido())
                    && s.getEstado().equalsIgnoreCase("pendiente")
                    && nueva.getEstado().equalsIgnoreCase("pendiente")) {
                return true;
            }
        }
        return false;
    }

    // ---------- TOSTRING ----------
    @Override
    public String toString() {
        return "Id: " + this.getIdUsuario() + ", Nombre: " + this.getNombreCompleto();
    }

    // ---------- EQUALS Y HASHCODE (solo por ID, evita recursión) ----------
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Usuario u)) {
            return false;
        }
        return this.idUsuario == u.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }
}