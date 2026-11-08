package ec.edu.uce.gestor.dominio;

import java.util.Date;

public class Contenido {
    private int idContenido;
    private String titulo;
    private String autor;
    private String genero;
    private Date fechaPublicacion;

    public Contenido(){
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

    public void setIdContenido(int idContenido){
        this.idContenido = idContenido;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getAutor(){
        return autor;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public String getGenero(){
        return genero;
    }

    public void setGenero(String genero){
        this.genero = genero;
    }

    public Date getFechaPublicacion(){
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion){
        this.fechaPublicacion = fechaPublicacion;
    }

}
