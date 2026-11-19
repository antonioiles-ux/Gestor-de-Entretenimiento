package ec.edu.uce.gestor.dominio;
import ec.edu.uce.gestor.Util.Validaciones;

import java.util.Objects;
import java.util.Date;

public class Contenido {
    private int idContenido;
    private String titulo;
    private String autor;
    private String genero;
    private Date fechaPublicacion;

    public Contenido(){
        this.idContenido = 0;
        this.titulo = "sin titulo";
        this.autor = "sin autor";
        this.genero = "sin genero";
        this.fechaPublicacion = null;
    }

    public Contenido(int idContenido, String titulo, String autor, String genero, Date fechaPublicacion){
        this.idContenido = idContenido;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.fechaPublicacion = fechaPublicacion;
    }
    //GET
    public int getIdContenido(){
        return idContenido;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getAutor(){
        return autor;
    }
    public String getGenero(){
        return genero;
    }
    public Date getFechaPublicacion(){
        return fechaPublicacion;
    }

    //SET VALIDACIONES
    public boolean setIdContenido(int idContenido){
        if (idContenido <= 0){
            return false;
        }
        this.idContenido = idContenido;
        return true;
    }

    public boolean setTitulo(String titulo){
        if (!Validaciones.validarLetras(titulo)) {
            return false;
        }
        this.titulo = titulo.trim();
        return true;
    }

    public boolean setAutor(String autor){
        if (!Validaciones.validarLetras(autor)) {
            return false;
        }
        this.autor = autor.trim();
        return true;
    }

    public boolean setGenero(String genero){
        if (!Validaciones.validarLetras(genero)) {
            return false;
        }
        this.genero = genero.trim();
        return true;
    }

    public boolean setFechaPublicacion(Date fechaPublicacion){
        if (fechaPublicacion == null) {
            return false;
        }
        this.fechaPublicacion = fechaPublicacion;
        return true;
    }

    @Override
    public String toString(){
        return "ID CONTENIDO:" +this.getIdContenido()+
                "  Titulo:" +this.getTitulo()+"  Autor:" +this.getAutor()+
                "  Genero:" +this.getGenero()+"  Fecha Publicacion:" +
                this.getFechaPublicacion();
    }



    // --- POLIMORFISMO (Metodo base que sobreescribir---
    public int calcularTiempoConsumo() {
        return 0;
    }

    // --- SOBRECARGA DE MÉTODOS ---
    public String obtenerFicha() {
        return "[" + this.idContenido + "] " + this.titulo + " (" + this.genero + ")";
    }

    public String obtenerFicha(boolean incluirAutor) {
        if (incluirAutor) {
            return obtenerFicha() + " - Autor: " + this.autor;
        }
        return obtenerFicha();
    }

    // --- EQUALS Y HASHCODE ---
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true; // mismo objeto en memoria
        }
        if (!(otro instanceof Contenido c)) {
            return false; // null u otra clase
        }
        return this.idContenido == c.idContenido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContenido);
    }

}