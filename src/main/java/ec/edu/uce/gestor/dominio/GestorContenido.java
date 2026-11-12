package ec.edu.uce.gestor.dominio;

public class GestorContenido extends Usuario {
    private int idGestor;

    public GestorContenido() {
    }

    public GestorContenido(String nombreCompleto, int idUsuario, int idGestor) {
        super(nombreCompleto, idUsuario);
        this.idGestor = idGestor;
    }

    public int getIdGestor() {
        return idGestor;
    }

    public void setIdGestor(int idGestor) {
        this.idGestor = idGestor;
    }

    @Override
    public String toString(){
        return "IdGestor: "+ this.getIdGestor();
    }
}
