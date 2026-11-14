package ec.edu.uce.gestor.dominio;

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

    // Métodos del Diagrama de Clases
    public void suspenderCuenta() {
        System.out.println("[GESTOR]: Suspendiendo cuenta de usuario...");
    }

    public void reactivarCuenta() {
        System.out.println("[GESTOR]: Reactivando cuenta de usuario...");
    }

    public void eliminarCuenta() {
        System.out.println("[GESTOR]: Eliminando cuenta de usuario del sistema...");
    }

    public void eliminarOpinion() {
        System.out.println("[GESTOR]: Moderando y eliminando opinión...");
    }

    @Override
    public String toString() {
        return "Id gestor: " + this.getIdGestor() + ", " + super.toString();
    }
}
