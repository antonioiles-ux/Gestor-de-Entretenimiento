package ec.edu.uce.gestor.dominio;

import java.util.Objects;
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


    // --- SOBRECARGA DE MÉTODOS ---
    public boolean validarCredenciales(String correo, String clave) {
        return this.correoElectronico.equals(correo) && this.contrasena.equals(clave);
    }

    public boolean validarCredenciales(String clave) {
        return this.contrasena.equals(clave);
    }

    // --- EQUALS Y HASHCODE ---
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Cuenta c)) {
            return false;
        }
        return this.idCuenta == c.idCuenta
                && Objects.equals(this.correoElectronico, c.correoElectronico);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCuenta, correoElectronico);
    }

}

