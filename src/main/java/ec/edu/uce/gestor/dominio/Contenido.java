package ec.edu.uce.gestor.dominio;

import ec.edu.uce.gestor.Util.Validaciones;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Contenido {
    private int idContenido;
    private String titulo;
    private String autor;
    private String genero;
    private Date fechaPublicacion;

    // RELACIÓN: COMPOSICIÓN (Si se elimina el Contenido, desaparecen sus opiniones y valoraciones)
    private Opinion[] opiniones;
    private int cantidadOpiniones;

    private Valoracion[] valoraciones;
    private int cantidadValoraciones;

    public Contenido() {
        this(0, "sin titulo", "sin autor", "sin genero", null);
    }

    public Contenido(int idContenido, String titulo, String autor, String genero, Date fechaPublicacion) {
        this.idContenido = idContenido;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.fechaPublicacion = fechaPublicacion;
        this.opiniones = new Opinion[50];
        this.cantidadOpiniones = 0;
        this.valoraciones = new Valoracion[50];
        this.cantidadValoraciones = 0;
    }

    // ---------- GET ----------
    public int getIdContenido() {
        return idContenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public int getCantidadOpiniones() {
        return cantidadOpiniones;
    }

    public int getCantidadValoraciones() {
        return cantidadValoraciones;
    }

    // ---------- SET CON VALIDACIONES ----------
    public boolean setIdContenido(int idContenido) {
        if (idContenido <= 0) {
            return false;
        }
        this.idContenido = idContenido;
        return true;
    }

    public boolean setTitulo(String titulo) {
        if (!Validaciones.validarLetras(titulo)) {
            return false;
        }
        this.titulo = titulo.trim();
        return true;
    }

    public boolean setAutor(String autor) {
        if (!Validaciones.validarLetras(autor)) {
            return false;
        }
        this.autor = autor.trim();
        return true;
    }

    public boolean setGenero(String genero) {
        if (!Validaciones.validarLetras(genero)) {
            return false;
        }
        this.genero = genero.trim();
        return true;
    }

    public boolean setFechaPublicacion(Date fechaPublicacion) {
        if (fechaPublicacion == null) {
            return false;
        }
        this.fechaPublicacion = fechaPublicacion;
        return true;
    }

    @Override
    public String toString() {
        return "ID CONTENIDO:" + this.getIdContenido() +
                "  Titulo:" + this.getTitulo() + "  Autor:" + this.getAutor() +
                "  Genero:" + this.getGenero() + "  Fecha Publicacion:" +
                this.getFechaPublicacion();
    }

    // ---------- POLIMORFISMO (método base que se sobrescribe) ----------
    public int calcularTiempoConsumo() {
        return 0;
    }

    // ---------- SOBRECARGA DE MÉTODOS ----------
    public String obtenerFicha() {
        return "[" + this.idContenido + "] " + this.titulo + " (" + this.genero + ")";
    }

    public String obtenerFicha(boolean incluirAutor) {
        if (incluirAutor) {
            return obtenerFicha() + " - Autor: " + this.autor;
        }
        return obtenerFicha();
    }

    // Sobrecarga con referencia de objeto
    public boolean tieneMismoGenero(Contenido otro) {
        if (otro == null || this.genero == null) {
            return false;
        }
        return this.genero.equalsIgnoreCase(otro.genero);
    }

    // =========================================================================
    // COMPOSICIÓN 1: GESTIÓN DE OPINIONES (Opinion[]), clave de búsqueda: comentario
    // =========================================================================

    // CREAR
    public boolean agregarOpinion(Opinion opinion) {
        if (opinion == null || buscarOpinion(opinion.getComentario()) != null) {
            return false;
        }
        if (cantidadOpiniones == opiniones.length) {
            opiniones = Arrays.copyOf(opiniones, opiniones.length * 2);
        }
        opinion.setContenido(this); // vincula la opinión con el contenido dueño
        opiniones[cantidadOpiniones] = opinion;
        cantidadOpiniones++;
        return true;
    }

    // LISTAR (copia para proteger el arreglo interno)
    public Opinion[] listarOpiniones() {
        return Arrays.copyOf(opiniones, cantidadOpiniones);
    }

    // BUSCAR por comentario
    public Opinion buscarOpinion(String comentario) {
        if (comentario == null) {
            return null;
        }
        for (Opinion op : listarOpiniones()) {
            if (op.getComentario().equalsIgnoreCase(comentario.trim())) {
                return op;
            }
        }
        return null;
    }

    // EDITAR
    public boolean editarOpinion(String comentario, String nuevoComentario, String nuevaFechaEdicion) {
        Opinion op = buscarOpinion(comentario);
        if (op == null || buscarOpinion(nuevoComentario) != null) {
            return false; // no existe, o el nuevo comentario ya está usado
        }
        return op.actualizarComentario(nuevoComentario, nuevaFechaEdicion);
    }

    // ELIMINAR (desplaza los elementos a la izquierda)
    public boolean eliminarOpinion(String comentario) {
        if (comentario == null) {
            return false;
        }
        int posicion = 0;
        for (Opinion op : listarOpiniones()) {
            if (op.getComentario().equalsIgnoreCase(comentario.trim())) {
                for (int j = posicion; j < cantidadOpiniones - 1; j++) {
                    opiniones[j] = opiniones[j + 1];
                }
                opiniones[cantidadOpiniones - 1] = null;
                cantidadOpiniones--;
                return true;
            }
            posicion++;
        }
        return false;
    }

    // =========================================================================
    // COMPOSICIÓN 2: GESTIÓN DE VALORACIONES (Valoracion[]), clave de búsqueda: autor
    // =========================================================================

    // CREAR
    public boolean agregarValoracion(Valoracion valoracion) {
        if (valoracion == null || valoracion.getAutor() == null
                || buscarValoracion(valoracion.getAutor().getNombreCompleto()) != null) {
            return false;
        }
        if (cantidadValoraciones == valoraciones.length) {
            valoraciones = Arrays.copyOf(valoraciones, valoraciones.length * 2);
        }
        valoracion.setContenido(this); // vincula la valoración con el contenido dueño
        valoraciones[cantidadValoraciones] = valoracion;
        cantidadValoraciones++;
        return true;
    }

    // LISTAR (copia para proteger el arreglo interno)
    public Valoracion[] listarValoraciones() {
        return Arrays.copyOf(valoraciones, cantidadValoraciones);
    }

    // BUSCAR por autor (nombre completo del usuario que hizo la valoración)
    public Valoracion buscarValoracion(String autor) {
        if (autor == null) {
            return null;
        }
        for (Valoracion val : listarValoraciones()) {
            if (val.getAutor().getNombreCompleto().equals(autor)) {
                return val;
            }
        }
        return null;
    }

    // EDITAR
    public boolean editarValoracion(String autor, int nuevaCalificacion, String nuevaFecha) {
        Valoracion val = buscarValoracion(autor);
        if (val == null) {
            return false;
        }
        return val.actualizarValoracion(nuevaCalificacion, nuevaFecha);
    }

    // ELIMINAR (desplaza los elementos a la izquierda)
    public boolean eliminarValoracion(String autor) {
        if (autor == null) {
            return false;
        }
        int posicion = 0;
        for (Valoracion val : listarValoraciones()) {
            if (val.getAutor().getNombreCompleto().equals(autor)) {
                for (int j = posicion; j < cantidadValoraciones - 1; j++) {
                    valoraciones[j] = valoraciones[j + 1];
                }
                valoraciones[cantidadValoraciones - 1] = null;
                cantidadValoraciones--;
                return true;
            }
            posicion++;
        }
        return false;
    }

    public double calcularPromedioValoraciones() {
        if (cantidadValoraciones == 0) {
            return 0.0;
        }
        int suma = 0;
        for (Valoracion val : listarValoraciones()) {
            suma += val.getCalificacion();
        }
        return (double) suma / cantidadValoraciones;
    }

    // ---------- EQUALS Y HASHCODE ----------
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Contenido c)) {
            return false;
        }
        return this.idContenido == c.idContenido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContenido);
    }
}