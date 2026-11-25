package ec.edu.uce.gestor.dominio;

import ec.edu.uce.gestor.Util.Validaciones;
import java.util.Date;
import java.util.Objects;

public class Cuenta {

    // =========================================================================
    // 1. ATRIBUTOS
    // =========================================================================
    private int idCuenta;
    private String correoElectronico;
    private String contrasena;
    private Date fechaRegistro;
    private boolean activa;

    // =========================================================================
    // 2. CONSTRUCTORES
    // =========================================================================
    public Cuenta() {
        this(0, "sin correo", "sin contrasena", null);
    }

    public Cuenta(int idCuenta, String correoElectronico, String contrasena, Date fechaRegistro) {
        this.idCuenta = idCuenta;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        this.activa = true;
    }

    // =========================================================================
    // 3. GETTERS Y SETTERS CON VALIDACIONES
    // =========================================================================
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
        if (!Validaciones.validarCorreo(correoElectronico)) {
            return false;
        }
        this.correoElectronico = correoElectronico.trim();
        return true;
    }

    public String getContrasena() {
        return contrasena;
    }

    public boolean setContrasena(String contrasena) {
        if (!Validaciones.validarContrasena(contrasena)) {
            return false;
        }
        this.contrasena = contrasena.trim();
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

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    // =========================================================================
    // 4. MÉTODOS DE NEGOCIO Y SOBRECARGA
    // =========================================================================
    public boolean validarCredenciales(String correo, String clave) {
        return this.correoElectronico.equals(correo) && this.contrasena.equals(clave);
    }

    public boolean validarCredenciales(String clave) {
        return this.contrasena.equals(clave);
    }

    public boolean validarCredenciales(Cuenta otraCuenta) {
        if (otraCuenta == null) {
            return false;
        }
        return this.correoElectronico.equals(otraCuenta.correoElectronico)
                && this.contrasena.equals(otraCuenta.contrasena);
    }

    // =========================================================================
    // 5. MÉTODOS SOBRESCRITOS (@Override)
    // =========================================================================
    @Override
    public String toString() {
        return "Id Cuenta: " + this.getIdCuenta()
                + ", Correo: " + this.getCorreoElectronico()
                + ", Contraseña: " + this.getContrasena()
                + ", Fecha de registro: " + this.getFechaRegistro();
    }

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
