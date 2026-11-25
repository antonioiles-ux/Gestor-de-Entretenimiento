package ec.edu.uce.gestor.dominio;
import java.util.Objects;

public class GestorContenido {
    private int idGestor;

    // RELACIÓN: ASOCIACIÓN (Usuarios registrados en el sistema)
    private Usuario[] usuarios;
    private int cantidadUsuarios;

    public GestorContenido() {
        this(0);
    }

    public GestorContenido(int idGestor) {

        this.idGestor = idGestor;
        this.usuarios = new Usuario[50];
        this.cantidadUsuarios = 0;
    }

    public int getIdGestor() {
        return idGestor;
    }

    public boolean setIdGestor(int idGestor) {
        if (idGestor <= 0) {
            return false;
        }
        this.idGestor = idGestor;
        return true;
    }

    // Métodos del Diagrama de Clases
    public boolean suspenderCuenta() {
        return this.idGestor > 0;
    }

    public boolean reactivarCuenta() {
        return this.idGestor > 0;
    }

    public boolean eliminarCuenta() {
        return this.idGestor > 0;
    }

    public boolean eliminarOpinion() {
        return this.idGestor > 0;
    }

    @Override
    public String toString() {
        return "Gestor Contenido"+ ", Id gestor: " +idGestor;
    }

    // --- EQUALS Y HASHCODE ---
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof GestorContenido g)) {
            return false;
        }
        return this.idGestor == g.idGestor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGestor);
    }

// =========================================================================
    // MÉTODOS AUXILIARES PARA VALIDAR DUPLICADOS
    // =========================================================================

    //Duplicacion de usuarios
    private boolean esUsuarioDuplicado(Usuario usuario) {

        if (buscarUsuario(usuario.getIdUsuario()) != null) {
            return true;
        }

        if (usuario.getCuenta() != null) {

            String correo = usuario.getCuenta().getCorreoElectronico();

            if (correo != null && !correo.equalsIgnoreCase("sin correo") && buscarUsuarioPorCorreo(correo) != null) {
                return true;
            }
        }

        return false;
    }

    // =========================================================================
    // ASOCIACIÓN: GESTIÓN DE USUARIOS (Usuario[]) - CRUD
    // =========================================================================

    public boolean agregarUsuario(Usuario usuario) {
        if (usuario == null || cantidadUsuarios == usuarios.length
                || esUsuarioDuplicado(usuario)) {
            return false;
        }
        usuarios[cantidadUsuarios++] = usuario;
        return true;
    }

    public Usuario buscarUsuario(int idUsuario) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getIdUsuario() == idUsuario) {
                return usuarios[i];
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        if (correo == null) {
            return null;
        }
        for (int i = 0; i < cantidadUsuarios; i++) {
            Cuenta c = usuarios[i].getCuenta();
            if (c != null && correo.trim().equalsIgnoreCase(c.getCorreoElectronico())) {
                return usuarios[i];
            }
        }
        return null;
    }

    public boolean actualizarNombreUsuario(int idUsuario, String nuevoNombre) {
        Usuario u = buscarUsuario(idUsuario);
        if (u == null) {
            return false;
        }
        return u.setNombreCompleto(nuevoNombre);
    }

    public boolean eliminarUsuario(int idUsuario) {
        for (int i = 0; i < cantidadUsuarios; i++) {
            if (usuarios[i].getIdUsuario() == idUsuario) {
                for (int j = i; j < cantidadUsuarios - 1; j++) {
                    usuarios[j] = usuarios[j + 1];
                }
                usuarios[--cantidadUsuarios] = null;
                return true;
            }
        }
        return false;
    }

    public Usuario[] listarUsuarios() {
        Usuario[] copia = new Usuario[cantidadUsuarios];
        for (int i = 0; i < cantidadUsuarios; i++) {
            copia[i] = usuarios[i];
        }
        return copia;
    }

    public int getCantidadUsuarios() {
        return cantidadUsuarios;
    }

    // Sobrecargas para suspender/reactivar usuarios específicos del arreglo
    public boolean suspenderCuenta(int idUsuario) {
        Usuario u = buscarUsuario(idUsuario);
        if (this.idGestor <= 0 || u == null || u.getCuenta() == null) {
            return false;
        }
        u.getCuenta().setActiva(false);
        return true;
    }

    public boolean reactivarCuenta(int idUsuario) {
        Usuario u = buscarUsuario(idUsuario);
        if (this.idGestor <= 0 || u == null || u.getCuenta() == null) {
            return false;
        }
        u.getCuenta().setActiva(true);
        return true;
    }

}
