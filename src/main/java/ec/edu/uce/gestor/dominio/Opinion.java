package ec.edu.uce.gestor.dominio;

import ec.edu.uce.gestor.Util.Validaciones;
import java.util.Objects;

public class Opinion {
    private int idOpinion;
    private String comentario;
    private String fechaRegistroOp;
    private String fechaEdicion;
    // RELACIÓN: ASOCIACIÓN (Referencias a objetos existentes: autor y contenido opinado)
    private Usuario autor;
    private Contenido contenido;


    public Opinion() {
        this(0, "sin comentario", "sin fecha de registro", "sin fecha de edicion");
    }

    public Opinion(int idOpinion, String comentario, String fechaRegistroOp, String fechaEdicion) {
        this.idOpinion = idOpinion;
        this.comentario = comentario;
        this.fechaRegistroOp = fechaRegistroOp;
        this.fechaEdicion = fechaEdicion;
    }

    // Constructor sobrecargado con Asociación
    public Opinion(int idOpinion, String comentario, String fechaRegistroOp, String fechaEdicion,
                   Usuario autor, Contenido contenido) {
        this(idOpinion, comentario, fechaRegistroOp, fechaEdicion);
        this.autor = autor;
        this.contenido = contenido;
    }

    public int getIdOpinion() {
        return idOpinion;
    }

    public boolean setIdOpinion(int idOpinion) {
        if (idOpinion <= 0) {
            return false;
        }
        this.idOpinion = idOpinion;
        return true;
    }

    public String getComentario() {
        return comentario;
    }

    public boolean setComentario(String comentario) {
        if (comentario == null || comentario.trim().isEmpty()) {
            return false;
        }
        this.comentario = comentario;
        return true;
    }

    public String getFechaRegistroOp() {
        return fechaRegistroOp;
    }

    public boolean setFechaRegistro(String fechaRegistroOp) {
        if (!Validaciones.validarFecha(fechaRegistroOp)) {
            return false;
        }
        this.fechaRegistroOp = fechaRegistroOp;
        return true;
    }

    public String getFechaEdicion() {
        return fechaEdicion;
    }

    public boolean setFechaEdicion(String fechaEdicion) {
        if (!Validaciones.validarFecha(fechaEdicion)) {
            return false;
        }
        this.fechaEdicion = fechaEdicion;
        return true;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }


    @Override
    public String toString() {
        return "ID OPINION:" + this.getIdOpinion() + "  Comentario:" + this.getComentario()
                + "  Fecha de Registro:" + this.getFechaRegistroOp() + "  Fecha de Edicion:" + this.getFechaEdicion();
    }

    // --- SOBRECARGA DE MÉTODOS (incluye referencia de objeto) ---
    public boolean actualizarComentario(String nuevoComentario) {
        return setComentario(nuevoComentario);
    }

    public boolean actualizarComentario(String nuevoComentario, String nuevaFechaEdicion) {
        return setComentario(nuevoComentario) && setFechaEdicion(nuevaFechaEdicion);
    }

    public boolean copiarDesde(Opinion otraOpinion) {
        if (otraOpinion == null) {
            return false;
        }
        return setComentario(otraOpinion.comentario);
    }

    // --- EQUALS Y HASHCODE ---
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Opinion o)) {
            return false;
        }
        return this.idOpinion == o.idOpinion
                && Objects.equals(this.comentario, o.comentario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOpinion, comentario);
    }
}