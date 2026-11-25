package ec.edu.uce.gestor.dominio;
import java.util.Objects;

public class GestorContenido {
    private int idGestor;

    // RELACIÓN: AGREGACIÓN (Catálogo polimórfico de contenidos)
    private Contenido[] catalogo;
    private int cantidadContenidos;

    // RELACIÓN: ASOCIACIÓN (Usuarios registrados en el sistema)
    private Usuario[] usuarios;
    private int cantidadUsuarios;

    public GestorContenido() {
        this(0);
    }

    public GestorContenido(int idGestor) {

        this.idGestor = idGestor;
        this.catalogo = new Contenido[50];
        this.cantidadContenidos = 0;
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

    //Duplicaciones en catalogos
    private boolean esDuplicadoEnCatalogo(Contenido contenido) {

        for (Contenido actual : catalogo) {

            if (actual == null) {
                continue;
            }

            if (actual.getIdContenido() ==
                    contenido.getIdContenido()) {
                return true;
            }

            if (actual.getTitulo() != null
                    && contenido.getTitulo() != null
                    && actual.getAutor() != null
                    && contenido.getAutor() != null) {

                boolean mismoTitulo =
                        actual.getTitulo().trim()
                                .equalsIgnoreCase(
                                        contenido.getTitulo().trim());

                boolean mismoAutor =
                        actual.getAutor().trim()
                                .equalsIgnoreCase(
                                        contenido.getAutor().trim());

                if (mismoTitulo && mismoAutor) {
                    return true;
                }
            }
        }
        return false;
    }

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
    // ASOCIACIÓN: GESTIÓN DE CONTENIDOS (Contenido[]) - CRUD
    // =========================================================================

    public boolean agregarContenido(Contenido contenido) {
        if (contenido == null || cantidadContenidos == catalogo.length
                || esDuplicadoEnCatalogo(contenido)) {
            return false;
        }
        catalogo[cantidadContenidos++] = contenido;
        return true;
    }

    public Contenido buscarContenido(int idContenido) {

        for (Contenido contenido : catalogo) {

            if (contenido == null) {
                continue;
            }

            if (contenido.getIdContenido() == idContenido) {
                return contenido;
            }
        }

        return null;
    }

    public boolean editarContenido(int idContenido, String nuevoTitulo) {
        Contenido c = buscarContenido(idContenido);
        if (c == null) {
            return false;
        }
        return c.setTitulo(nuevoTitulo);
    }

    public boolean eliminarContenido(int idContenido) {
        for (int i = 0; i < cantidadContenidos; i++) {
            if (catalogo[i].getIdContenido() == idContenido) {
                for (int j = i; j < cantidadContenidos - 1; j++) {
                    catalogo[j] = catalogo[j + 1];
                }
                catalogo[--cantidadContenidos] = null;
                return true;
            }
        }
        return false;
    }

    public Contenido[] listarContenidos() {
        Contenido[] copia = new Contenido[cantidadContenidos];
        for (int i = 0; i < cantidadContenidos; i++) {
            copia[i] = catalogo[i];
        }
        return copia;
    }

    public int getCantidadContenidos() {
        return cantidadContenidos;
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
        for (Usuario usuario : usuarios) {

            if (usuario == null) {
                continue;
            }

            if (usuario.getIdUsuario() == idUsuario) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        if (correo == null) {
            return null;
        }
        for (Usuario usuario : usuarios) {

            if (usuario == null) {
                continue;
            }

            Cuenta c = usuario.getCuenta();

            if (c != null && correo.trim().equalsIgnoreCase(c.getCorreoElectronico())) {
                return usuario;
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
