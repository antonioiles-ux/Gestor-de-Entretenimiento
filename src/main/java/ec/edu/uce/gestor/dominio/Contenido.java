package ec.edu.uce.gestor.dominio;

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
    }

    public Contenido(int idContenido, String titulo, String autor, String genero, Date fechaPublicacion){
        this.idContenido = idContenido;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getIdContenido(){
        return idContenido;
    }

    public boolean setIdContenido(int idContenido){
        if (idContenido <= 0){
            return false;
        }
        this.idContenido = idContenido;
        return true;
    }

    public String getTitulo(){
        return titulo;
    }

    public boolean setTitulo(String titulo){
        if (titulo == "") {
            return false;
        }
        this.titulo = titulo;
        return true;
    }

    public String getAutor(){
        return autor;
    }

    public boolean setAutor(String autor){
        if (autor == "") {
            return false;
        }
        this.autor = autor;
        return true;
    }

    public String getGenero(){
        return genero;
    }

    public boolean setGenero(String genero){
        if (genero == "") {
            return false;
        }
        this.genero = genero;
        return true;
    }

    public Date getFechaPublicacion(){
        return fechaPublicacion;
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
        return "ID CONTENIDO:" +this.getIdContenido()+"  Titulo:" +this.getTitulo()+"  Autor:" +this.getAutor()+"  Genero:" +this.getGenero()+"  Fecha Publicacion:" +this.getFechaPublicacion();
    }

}