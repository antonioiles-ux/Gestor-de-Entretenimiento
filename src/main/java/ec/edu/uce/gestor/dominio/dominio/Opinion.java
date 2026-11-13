package ec.edu.uce.gestor.dominio.dominio;

public class Opinion {
    private int idOpinion;
    private String comentario;
    private String fechaRegistroOp;
    private String fechaEdicion;

    public Opinion() {
        this.idOpinion = 0;
        this.comentario = "sin comentario";
        this.fechaRegistroOp = "sin fecha de registro";
        this.fechaEdicion = "sin fecha de edicion";
    }

    public Opinion(int idOpinion, String comentario, String fechaRegistroOp, String fechaEdicion){
        this.idOpinion = idOpinion;
        this.comentario = comentario;
        this.fechaRegistroOp = fechaRegistroOp;
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

    public String getFechaRegistroOp(){
        return fechaRegistroOp;
    }
    public boolean setFechaRegistro(String fechaRegistroOp){
        if (fechaRegistroOp == ""){
            return false;
        }
        this.fechaRegistroOp = fechaRegistroOp;
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

    @Override
    public String toString(){
        return "ID OPINION:" +this.getIdOpinion()+"  Comentario:" +this.getComentario()+"  Fecha de Registro:" +this.getFechaRegistroOp()+"  Fecha de Edicion:" +this.getFechaEdicion();
    }
}
