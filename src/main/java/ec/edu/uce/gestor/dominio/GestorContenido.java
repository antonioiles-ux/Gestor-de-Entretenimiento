package ec.edu.uce.gestor.dominio;
import java.util.Objects;

public class GestorContenido extends Usuario {
    private int idGestor;

    public GestorContenido() {
        this("sin nombre", 0, 0);
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

    // Métodos del Diagrama de Clases
    public boolean suspenderCuenta() {
        return this.idGestor > 0;
    }

    public boolean reactivarCuenta() {
        return this.idGestor > 0;
    }

    public boolean eliminarCuenta() {
        return this.idGestor > 0;
    }

    public boolean eliminarOpinion() {
        return this.idGestor > 0;
    }

    @Override
    public String toString() {
        return super.toString() + ", Id gestor: " + this.getIdGestor();
    }

    // --- POLIMORFISMO / SOBREESCRITURA (Heredado de Usuario) ---
    @Override
    public String obtenerRol() {
        return "Administrador / Gestor de Contenidos";
    }

    // --- SOBRECARGA DE MÉTODOS ---
    public boolean eliminarOpinion(String motivo) {
        return this.idGestor > 0 && motivo != null && !motivo.trim().isEmpty();
    }

    // Sobrecarga 2: con referencia de objeto Opinion + motivo (no genera ambigüedad con null)
    public boolean eliminarOpinion(Opinion opinion, String motivo) {
        return this.idGestor > 0 && opinion != null && opinion.getIdOpinion() > 0
                && motivo != null && !motivo.trim().isEmpty();
    }


    // --- EQUALS Y HASHCODE ---
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof GestorContenido g)) {
            return false;
        }
        return this.idGestor == g.idGestor && super.equals(g);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idGestor);
    }
}
