package ec.edu.uce.gestor.dominio;

import java.util.Date;

public class Cuenta{
    private int idCuenta;
    private String correoElectronico;
    private String contrasena;
    private Date fechaRegistro;

    public Cuenta() {
        this.idCuenta = 0;
        this.correoElectronico = "sin correo";
        this.contrasena = "sin contrasena";
        this.fechaRegistro = null;
    }

    public Cuenta(int idCuenta, String correoElectronico, String contrasena, Date fechaRegistro) {
        this.idCuenta = idCuenta;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public boolean setIdCuenta(int idCuenta) {
        if (idCuenta <= 0) {
            return false;
        }
        this.idCuenta = idCuenta;

        return true;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public boolean setCorreoElectronico(String correoElectronico) {
        if (correoElectronico == null || correoElectronico.equals("") || !correoElectronico.contains("@")) {
            return false;
        }
        this.correoElectronico = correoElectronico;

        return true;
    }

    public String getContrasena() {
        return contrasena;
    }

    public boolean setContrasena(String contrasena) {
        if (contrasena == null || contrasena.length() < 6) {
            return false;
        }
        this.contrasena = contrasena;

        return true;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public boolean setFechaRegistro(Date fechaRegistro) {
        if (fechaRegistro == null) {
            return false;
        }
        this.fechaRegistro = fechaRegistro;

        return true;
    }

    @Override
    public String toString() {
        return "Id Cuenta: " + this.getIdCuenta() + ", Correo: " + this.getCorreoElectronico() + ", Contraseña: " + this.getContrasena() +
                ", Fecha de registro: " + this.getFechaRegistro();
    }

}

