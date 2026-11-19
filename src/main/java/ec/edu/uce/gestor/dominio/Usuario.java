package ec.edu.uce.gestor.dominio;

import ec.edu.uce.gestor.Util.Validaciones;
import java.util.Objects;

public class Usuario {

    private String nombreCompleto;
    private int idUsuario;

    public Usuario() {
        this.nombreCompleto = "sin nombre";
        this.idUsuario = 0;
    }

    public Usuario(String nombreCompleto, int idUsuario) {
        this();
        setNombreCompleto(nombreCompleto);
        setIdUsuario(idUsuario);
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

    // --- POLIMORFISMO (Metodo base que sobreescribe GestorContenido) ---
    public String obtenerRol() {
        return "Usuario Registrado";
    }

    // --- SOBRECARGA DE MÉTODOS (incluye referencia de objeto) ---
    public String mostrarPerfil() {
        return this.toString();
    }

    public String mostrarPerfil(boolean incluirRol) {
        if (incluirRol) {
            return this.toString() + ", Rol: " + obtenerRol();
        }
        return mostrarPerfil();
    }

    public boolean esMismoUsuario(Usuario otroUsuario) {
        if (otroUsuario == null) {
            return false;
        }
        return this.idUsuario == otroUsuario.idUsuario;
    }

    // --- EQUALS Y HASHCODE ---
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Usuario u)) {
            return false;
        }
        return this.idUsuario == u.idUsuario
                && Objects.equals(this.nombreCompleto, u.nombreCompleto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, nombreCompleto);
    }
}