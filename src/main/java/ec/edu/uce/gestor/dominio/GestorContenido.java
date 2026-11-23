package ec.edu.uce.gestor.dominio;
import java.util.Objects;

public class GestorContenido extends Usuario {
    private int idGestor;

    // RELACIÓN: AGREGACIÓN (Catálogo polimórfico de contenidos)
    private Contenido[] catalogo;
    private int cantidadContenidos;

    // RELACIÓN: ASOCIACIÓN (Usuarios registrados en el sistema)
    private Usuario[] usuarios;
    private int cantidadUsuarios;

    public GestorContenido() {
        this("sin nombre", 0, 0);
    }

    public GestorContenido(String nombreCompleto, int idUsuario, int idGestor) {
        super(nombreCompleto, idUsuario);
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
        return super.toString() + ", Id gestor: " + this.getIdGestor();
    }

    // --- POLIMORFISMO / SOBREESCRITURA (Heredado de Usuario) ---
    @Override
    public String obtenerRol() {
        return "Administrador / Gestor de Contenidos";
    }

    // --- SOBRECARGA DE MÉTODOS ---
    public boolean eliminarOpinion(String motivo) {
        return this.idGestor > 0 && motivo != null && !motivo.trim().isEmpty();
    }

    // Sobrecarga 2: con referencia de objeto Opinion + motivo (no genera ambigüedad con null)
    public boolean eliminarOpinion(Opinion opinion, String motivo) {
        return this.idGestor > 0 && opinion != null && opinion.getIdOpinion() > 0
                && motivo != null && !motivo.trim().isEmpty();
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
        return this.idGestor == g.idGestor && super.equals(g);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idGestor);
    }

// =========================================================================
    // MÉTODOS AUXILIARES PARA VALIDAR DUPLICADOS
    // =========================================================================

    private boolean esDuplicadoEnCatalogo(Contenido contenido) {
        for (int i = 0; i < cantidadContenidos; i++) {
            Contenido actual = catalogo[i];
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

    private boolean esUsuarioDuplicado(Usuario usuario) {
        if (buscarUsuario(usuario.getIdUsuario()) != null) {
            return true;
        }
        if (usuario.getCuenta() != null) {
            String correo = usuario.getCuenta().getCorreoElectronico();
            if (correo != null && !correo.equalsIgnoreCase("sin correo")
                    && buscarUsuarioPorCorreo(correo) != null) {
                return true;
            }
        }
        if (usuario instanceof GestorContenido nuevoGestor && nuevoGestor.getIdGestor() > 0) {
            for (int i = 0; i < cantidadUsuarios; i++) {
                if (usuarios[i] instanceof GestorContenido g
                        && g.getIdGestor() == nuevoGestor.getIdGestor()) {
                    return true;
                }
            }
        }
        return false;
    }

    // =========================================================================
    // AGREGACIÓN: CATÁLOGO POLIMÓRFICO (Contenido[])
    // =========================================================================

    @Override
    public boolean agregarContenido(Contenido contenido) {
        if (contenido == null || cantidadContenidos == catalogo.length
                || esDuplicadoEnCatalogo(contenido)) {
            return false;
        }
        catalogo[cantidadContenidos++] = contenido;
        return true;
    }

    @Override
    public Contenido buscarContenido(int idContenido) {
        for (int i = 0; i < cantidadContenidos; i++) {
            if (catalogo[i].getIdContenido() == idContenido) {
                return catalogo[i];
            }
        }
        return null;
    }

    @Override
    public boolean editarContenido(int idContenido, String nuevoTitulo) {
        Contenido c = buscarContenido(idContenido);
        if (c == null) {
            return false;
        }
        return c.setTitulo(nuevoTitulo);
    }

    @Override
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

    @Override
    public Contenido[] listarContenidos() {
        Contenido[] copia = new Contenido[cantidadContenidos];
        for (int i = 0; i < cantidadContenidos; i++) {
            copia[i] = catalogo[i];
        }
        return copia;
    }

    @Override
    public int getCantidadContenidos() {
        return cantidadContenidos;
    }

    // =========================================================================
    // ASOCIACIÓN: GESTIÓN DE USUARIOS (Usuario[])
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
