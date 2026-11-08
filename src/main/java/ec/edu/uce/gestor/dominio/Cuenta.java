package ec.edu.uce.gestor.dominio;

public class Cuenta {

    private int idCuenta;
    private String correoElectronico;
    private String contrasena;
    private String fechaRegistro;

    public Cuenta() {
    }

    public Cuenta(int idCuenta, String correoElectronico, String contrasena, String fechaRegistro) {
        this.idCuenta = idCuenta;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "idCuenta=" + idCuenta +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }
}
