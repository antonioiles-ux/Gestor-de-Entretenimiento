package ec.edu.uce.gestor.dominio;

import ec.edu.uce.gestor.Util.Validaciones;

public class Usuario {

    private String nombreCompleto;
    private int idUsuario;

    public Usuario() {
        this.nombreCompleto = "sin nombre";
        this.idUsuario = 0;
    }

    public Usuario(String nombreCompleto, int idUsuario) {
        if (!setNombreCompleto(nombreCompleto)) {
            this.nombreCompleto = "sin nombre";
        }
        if (!setIdUsuario(idUsuario)) {
            this.idUsuario = 0;
        }
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public boolean setNombreCompleto(String nombreCompleto) {
        if (!Validaciones.validarLetras(nombreCompleto)) {
            return false;
        }
        this.nombreCompleto = nombreCompleto.trim();
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