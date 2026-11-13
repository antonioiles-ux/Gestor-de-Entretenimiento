package ec.edu.uce.gestor.dominio.dominio;

public class GestorContenido extends Usuario {
    private int idGestor;

    public GestorContenido() {
        super();
        this.idGestor = 0;
    }

    public GestorContenido(String nombreCompleto, int idUsuario, int idGestor) {
        super(nombreCompleto, idUsuario);
        this.idGestor = idGestor;
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

    @Override
    public String toString() {
        return "Id gestor: " + this.getIdGestor() + ", " + super.toString();
    }
}
