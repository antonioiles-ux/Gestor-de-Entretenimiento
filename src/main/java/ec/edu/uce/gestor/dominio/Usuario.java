package ec.edu.uce.gestor.dominio;

public class Usuario {

    private String nombreCompleto;
    private int idUsuario;

    public Usuario() {
    }

    public Usuario(String nombreCompleto, int idUsuario) {
        this.nombreCompleto = nombreCompleto;
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
