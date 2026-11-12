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
    public boolean setIdOpinion(int idOpinion){
        if (idOpinion <= 0){
            return false;
        }
        this.idOpinion = idOpinion;
        return true;
    }

    public String getComentario(){
        return comentario;
    }
    public boolean setComentario(String comentario){
        if (comentario == ""){
            return false;
        }
        this.comentario = comentario;
        return true;
    }

    public String getFechaRegistro(){
        return fechaRegistro;
    }
    public boolean setFechaRegistro(String fechaRegistro){
        if (fechaRegistro == ""){
            return false;
        }
        this.fechaRegistro = fechaRegistro;
        return true;
    }

    public String getFechaEdicion(){
        return fechaEdicion;
    }
    public boolean setFechaEdicion(String fechaEdicion){
        if (fechaEdicion == ""){
            return false;
        }
        this.fechaEdicion = fechaEdicion;
        return true;
    }
}
