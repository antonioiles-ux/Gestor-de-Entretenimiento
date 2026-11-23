package ec.edu.uce.gestor.dominio;
import ec.edu.uce.gestor.Util.Validaciones;

import java.util.Objects;
import java.util.Date;

public class Contenido {
    private int idContenido;
    private String titulo;
    private String autor;
    private String genero;
    private Date fechaPublicacion;

    // RELACIÓN: COMPOSICIÓN (Si se elimina el Contenido, desaparecen sus opiniones y valoraciones)
    private Opinion[] opiniones;
    private int cantidadOpiniones;

    private Valoracion[] valoraciones;
    private int cantidadValoraciones;

    public Contenido(){
        this(0, "sin titulo", "sin autor", "sin genero", null);
    }

    public Contenido(int idContenido, String titulo, String autor, String genero, Date fechaPublicacion) {
        this.idContenido = idContenido;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.fechaPublicacion = fechaPublicacion;
        this.opiniones = new Opinion[50];
        this.cantidadOpiniones = 0;
        this.valoraciones = new Valoracion[50];
        this.cantidadValoraciones = 0;
    }

    //GET
    public int getIdContenido(){
        return idContenido;
    }
    public String getTitulo(){
        return titulo;
    }
    public String getAutor(){
        return autor;
    }
    public String getGenero(){
        return genero;
    }
    public Date getFechaPublicacion(){
        return fechaPublicacion;
    }

    //SET VALIDACIONES
    public boolean setIdContenido(int idContenido){
        if (idContenido <= 0){
            return false;
        }
        this.idContenido = idContenido;
        return true;
    }

    public boolean setTitulo(String titulo){
        if (!Validaciones.validarLetras(titulo)) {
            return false;
        }
        this.titulo = titulo.trim();
        return true;
    }

    public boolean setAutor(String autor){
        if (!Validaciones.validarLetras(autor)) {
            return false;
        }
        this.autor = autor.trim();
        return true;
    }

    public boolean setGenero(String genero){
        if (!Validaciones.validarLetras(genero)) {
            return false;
        }
        this.genero = genero.trim();
        return true;
    }


    public boolean setFechaPublicacion(Date fechaPublicacion){
        if (fechaPublicacion == null) {
            return false;
        }
        this.fechaPublicacion = fechaPublicacion;
        return true;
    }

    @Override
    public String toString(){
        return "ID CONTENIDO:" +this.getIdContenido()+
                "  Titulo:" +this.getTitulo()+"  Autor:" +this.getAutor()+
                "  Genero:" +this.getGenero()+"  Fecha Publicacion:" +
                this.getFechaPublicacion();
    }

    // --- POLIMORFISMO (Metodo base que sobreescribir) ---
    public int calcularTiempoConsumo() {
        return 0;
    }

    // --- SOBRECARGA DE MÉTODOS ---
    public String obtenerFicha() {
        return "[" + this.idContenido + "] " + this.titulo + " (" + this.genero + ")";
    }

    public String obtenerFicha(boolean incluirAutor) {
        if (incluirAutor) {
            return obtenerFicha() + " - Autor: " + this.autor;
        }
        return obtenerFicha();
    }

    // Sobrecarga con referencia de objeto
    public boolean tieneMismoGenero(Contenido otro) {
        if (otro == null || this.genero == null) {
            return false;
        }
        return this.genero.equalsIgnoreCase(otro.genero);
    }

    //OPINION
    //Duplicados Opinion
    private boolean esOpinionDuplicada(Opinion opinion) {

        for (int i = 0; i < cantidadOpiniones; i++) {

            if (opiniones[i].getIdOpinion() == opinion.getIdOpinion()) {
                return true;
            }

            if (opiniones[i].getComentario().equalsIgnoreCase(opinion.getComentario())) {
                return true;
            }
        }

        return false;
    }

    // =========================================================================
    // COMPOSICIÓN 1: GESTIÓN DE OPINIONES DEL CONTENIDO (Opinion[])
    // =========================================================================

    // CREAR
    public boolean agregarOpinion(Opinion opinion) {
        if (opinion == null || cantidadOpiniones == opiniones.length
                || esOpinionDuplicada(opinion)) {
            return false;
        }
        opinion.setContenido(this); // Vincula la referencia al contenido dueño
        opiniones[cantidadOpiniones] = opinion;
        cantidadOpiniones++;
        return true;
    }

    // BUSCAR
    public Opinion buscarOpinion(int idOpinion) {
        for (int i = 0; i < cantidadOpiniones; i++) {
            if (opiniones[i].getIdOpinion() == idOpinion) {
                return opiniones[i];
            }
        }
        return null;
    }

    // EDITAR
    public boolean editarOpinion(int idOpinion, String nuevoComentario, String nuevaFechaEdicion) {
        Opinion op = buscarOpinion(idOpinion);
        if (op == null) {
            return false;
        }
        return op.actualizarComentario(nuevoComentario, nuevaFechaEdicion);
    }

    // ELIMINAR (con desplazamiento a la izquierda)
    public boolean eliminarOpinion(int idOpinion) {
        for (int i = 0; i < cantidadOpiniones; i++) {
            if (opiniones[i].getIdOpinion() == idOpinion) {
                for (int j = i; j < cantidadOpiniones - 1; j++) {
                    opiniones[j] = opiniones[j + 1];
                }
                opiniones[cantidadOpiniones - 1] = null;
                cantidadOpiniones--;
                return true;
            }
        }
        return false;
    }

    // LISTAR (copia exacta para proteger el arreglo interno)
    public Opinion[] listarOpiniones() {
        Opinion[] copia = new Opinion[cantidadOpiniones];
        for (int i = 0; i < cantidadOpiniones; i++) {
            copia[i] = opiniones[i];
        }
        return copia;
    }

    public int getCantidadOpiniones() {
        return cantidadOpiniones;
    }

    //VALORACION
    //Duplicados Valoracion
    private boolean esValoracionDuplicada(Valoracion valoracion) {

        for (int i = 0; i < cantidadValoraciones; i++) {

            if (valoraciones[i].getIdValoracion() == valoracion.getIdValoracion()) {
                return true;
            }

            if (valoraciones[i].getAutor().equals(valoracion.getAutor())) {
                return true;
            }
        }

        return false;
    }

    // =========================================================================
    // COMPOSICIÓN 2: GESTIÓN DE VALORACIONES DEL CONTENIDO (Valoracion[])
    // =========================================================================

    // CREAR
    public boolean agregarValoracion(Valoracion valoracion) {
        if (valoracion == null || cantidadValoraciones == valoraciones.length
                || esValoracionDuplicada(valoracion)) {
            return false;
        }
        valoracion.setContenido(this); // Vincula la referencia al contenido dueño
        valoraciones[cantidadValoraciones] = valoracion;
        cantidadValoraciones++;
        return true;
    }


    // BUSCAR
    public Valoracion buscarValoracion(int idValoracion) {
        for (int i = 0; i < cantidadValoraciones; i++) {
            if (valoraciones[i].getIdValoracion() == idValoracion) {
                return valoraciones[i];
            }
        }
        return null;
    }

    // EDITAR
    public boolean editarValoracion(int idValoracion, int nuevaCalificacion, String nuevaFecha) {
        Valoracion val = buscarValoracion(idValoracion);
        if (val == null) {
            return false;
        }
        return val.actualizarValoracion(nuevaCalificacion, nuevaFecha);
    }

    // ELIMINAR (con desplazamiento a la izquierda)
    public boolean eliminarValoracion(int idValoracion) {
        for (int i = 0; i < cantidadValoraciones; i++) {
            if (valoraciones[i].getIdValoracion() == idValoracion) {
                for (int j = i; j < cantidadValoraciones - 1; j++) {
                    valoraciones[j] = valoraciones[j + 1];
                }
                valoraciones[cantidadValoraciones - 1] = null;
                cantidadValoraciones--;
                return true;
            }
        }
        return false;
    }

    // LISTAR (copia exacta)
    public Valoracion[] listarValoraciones() {
        Valoracion[] copia = new Valoracion[cantidadValoraciones];
        for (int i = 0; i < cantidadValoraciones; i++) {
            copia[i] = valoraciones[i];
        }
        return copia;
    }

    public int getCantidadValoraciones() {
        return cantidadValoraciones;
    }

    public double calcularPromedioValoraciones() {
        if (cantidadValoraciones == 0) {
            return 0.0;
        }
        int suma = 0;
        for (int i = 0; i < cantidadValoraciones; i++) {
            suma += valoraciones[i].getCalificacion();
        }
        return (double) suma / cantidadValoraciones;
    }

    // --- EQUALS Y HASHCODE ---
    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Contenido c)) {
            return false;
        }
        return this.idContenido == c.idContenido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idContenido);
    }
}