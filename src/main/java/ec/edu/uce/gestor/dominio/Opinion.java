package ec.edu.uce.gestor.dominio;

public class Opinion {
    private int idOpinion;
    private String comentario;
    private String fechaRegistro;
    private String fechaEdicion;

    public Opinion() {
        this.idOpinion = 0;
        this.comentario = "sin comentario";
        this.fechaRegistro = "sin fecha de registro";
        this.fechaEdicion = "sin fecha de edicion";
    }

    public Opinion(int idOpinion, String comentario, String fechaRegistro, String fechaEdicion){
        this.idOpinion = idOpinion;
        this.comentario = comentario;
        this.fechaRegistro = fechaRegistro;
        this.fechaEdicion = fechaEdicion;
    }

    public int getIdOpinion(){
        return idOpinion;
    }
    public void setIdOpinion(int idOpinion){
        this.idOpinion = idOpinion;
    }

    public String getComentario(){
        return comentario;
    }
    public void setComentario(String comentario){
        this.comentario = comentario;
    }

    public String getFechaRegistro(){
        return fechaRegistro;
    }
    public void setFechaRegistro(String fechaRegistro){
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaEdicion(){
        return fechaEdicion;
    }
    public void setFechaEdicion(String fechaEdicion){
        this.fechaEdicion = fechaEdicion;
    }
}
