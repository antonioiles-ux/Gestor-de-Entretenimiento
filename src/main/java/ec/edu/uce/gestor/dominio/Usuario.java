package ec.edu.uce.gestor.dominio;

public class Usuario {

    private String nombreCompleto;
    private int idUsuario;

    public Usuario() {
        this.nombreCompleto = "sin nombre";
        this.idUsuario = 0;
    }

    public Usuario(String nombreCompleto, int idUsuario) {
        this.nombreCompleto = nombreCompleto;
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public boolean setNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.equals("")) {
            return false;
        }
        this.nombreCompleto = nombreCompleto;

        return true;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public boolean setIdUsuario(int idUsuario) {
        if (idUsuario <= 0) {
            return false;
        }
        this.idUsuario = idUsuario;

        return true;
    }

    @Override
    public String toString() {
        return "Id: " + this.getIdUsuario() + ", Nombre: " + this.getNombreCompleto();
    }
}