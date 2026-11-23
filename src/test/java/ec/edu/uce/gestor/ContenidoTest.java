package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Contenido;
import ec.edu.uce.gestor.dominio.Opinion;
import ec.edu.uce.gestor.dominio.Valoracion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ContenidoTest {
    //Objeto de prueba
    private Contenido contenido;
    private Date fechaContenido;

    //Preparar el objeto de prueba
    @BeforeEach
    public void setUp(){
        fechaContenido = new Date();
        contenido = new Contenido(1, "NEED FOR SPEED", "Electronic Arts", "Videojuego de Carreras", fechaContenido);
    }

    @Test
    public void contenidoDefecto() {
        Contenido contenido = new Contenido();

        assertEquals(0, contenido.getIdContenido());
        assertEquals("sin titulo", contenido.getTitulo());
        assertEquals("sin autor", contenido.getAutor());
        assertEquals("sin genero", contenido.getGenero());
        assertEquals(null, contenido.getFechaPublicacion());
    }

    @Test
    public void contenidoValido() {

        assertEquals(1, contenido.getIdContenido());
        assertEquals("NEED FOR SPEED", contenido.getTitulo());
        assertEquals("Electronic Arts", contenido.getAutor());
        assertEquals("Videojuego de Carreras", contenido.getGenero());
        assertEquals(fechaContenido, contenido.getFechaPublicacion());

    }

    @Test
    public void testCrearContenido(){
        assertNotNull(contenido);

    }

    @Test
    public void testGetId(){
        assertEquals(1,contenido.getIdContenido());
    }

    @Test
    public void testGetTitulo(){
        assertEquals("NEED FOR SPEED",contenido.getTitulo());
    }

    @Test
    public void testGetAutor(){
        assertEquals("Electronic Arts", contenido.getAutor());
    }

    @Test
    public void testGetGenero(){
        assertEquals("Videojuego de Carreras", contenido.getGenero());
    }

    @Test
    public void testGetFecha(){
        assertNotNull(contenido.getFechaPublicacion());
        assertEquals(fechaContenido, contenido.getFechaPublicacion());
    }

    @Test
    public void testSetIdContenido() {

        assertTrue(contenido.setIdContenido(10));
        assertEquals(10, contenido.getIdContenido());
    }

    @Test
    public void testSetTitulo() {

        assertTrue(contenido.setTitulo("Harry Poter"));
        assertEquals("Harry Poter", contenido.getTitulo());

    }

    @Test
    public void tituloInvalido(){
        String tituloAntes = contenido.getTitulo();

        assertFalse(contenido.setTitulo(""));
        assertEquals(tituloAntes, contenido.getTitulo());
    }

    @Test
    public void autorInvalido(){
        String autorAntes = contenido.getAutor();

        assertFalse(contenido.setAutor(""));
        assertEquals(autorAntes, contenido.getAutor());
    }

    @Test
    public void generoNoValido(){
        String generoAntes = contenido.getGenero();

        assertFalse(contenido.setGenero(""));
        assertEquals(generoAntes,contenido.getGenero());
    }

    @Test
    public void fechaNoValida() {
        Date fechaAntes = contenido.getFechaPublicacion();

        assertFalse(contenido.setFechaPublicacion(null));
        assertEquals(fechaAntes, contenido.getFechaPublicacion());
    }

    @Test
    public void idContenidoInvalido() {
        int idContenidoAntes = contenido.getIdContenido();

        assertFalse(contenido.setIdContenido(0));
        assertEquals(idContenidoAntes, contenido.getIdContenido());
    }

    @Test
    public void idNegativo(){
        int idContenidoAntes = contenido.getIdContenido();

        assertFalse(contenido.setIdContenido(-1));
        assertEquals(idContenidoAntes, contenido.getIdContenido());
    }


    // --- Nuevos tests: Sobrecarga, Polimorfismo, equals y hashCode ---

    @Test
    public void testSobrecargaObtenerFicha() {
        assertEquals("[1] NEED FOR SPEED (Videojuego de Carreras)", contenido.obtenerFicha());
        assertEquals("[1] NEED FOR SPEED (Videojuego de Carreras) - Autor: Electronic Arts",
                contenido.obtenerFicha(true));
        assertEquals("[1] NEED FOR SPEED (Videojuego de Carreras)", contenido.obtenerFicha(false));
    }

    @Test
    public void testCalcularTiempoConsumoBase() {
        assertEquals(0, contenido.calcularTiempoConsumo());
    }

    @Test
    public void testEqualsYHashCode() {
        Contenido mismoId = new Contenido(1, "Otro Titulo", "Otro Autor", "Accion", new Date());
        Contenido distintoId = new Contenido(99, "NEED FOR SPEED", "Electronic Arts", "Videojuego de Carreras", fechaContenido);

        assertEquals(contenido, contenido);
        assertEquals(contenido, mismoId);
        assertEquals(contenido.hashCode(), mismoId.hashCode());
        assertNotEquals(contenido, distintoId);
        assertFalse(contenido.equals(null));
        assertFalse(contenido.equals("Cadena cualquiera"));
    }
    @Test
    public void testTieneMismoGenero() {
        Contenido mismoGenero = new Contenido(2, "FIFA", "EA Sports", "Videojuego de Carreras", new Date());
        Contenido distintoGenero = new Contenido(3, "Harry Potter", "J.K. Rowling", "Fantasia", new Date());

        assertTrue(contenido.tieneMismoGenero(mismoGenero));
        assertFalse(contenido.tieneMismoGenero(distintoGenero));
        assertFalse(contenido.tieneMismoGenero(null));
    }

    @Test
    public void testPromedioSinValoraciones() {
        assertEquals(0.0,
                contenido.calcularPromedioValoraciones(),
                0.001);
    }

    @Test
    public void testToString() {
        String cadena = contenido.toString();

        assertTrue(cadena.contains("ID CONTENIDO:1"));
        assertTrue(cadena.contains("Titulo:NEED FOR SPEED"));
        assertTrue(cadena.contains("Autor:Electronic Arts"));
    }

    @Test
    void agregarOpinion() {
        Opinion op = new Opinion(1, "Excelente juego", "10/10/2026", "10/10/2026");

        assertTrue(contenido.agregarOpinion(op));
        assertEquals(1, contenido.getCantidadOpiniones());
    }

    @Test
    void opinionDuplicada() {

        Opinion op1 = new Opinion(1, "Excelente juego", "10/10/2026", "10/10/2026");
        Opinion op2 = new Opinion(1, "Otro comentario", "11/10/2026", "11/10/2026");

        contenido.agregarOpinion(op1);

        assertFalse(contenido.agregarOpinion(op2));
    }

    @Test
    void buscarOpinion() {
        Opinion op = new Opinion(1, "Excelente juego", "10/10/2026", "10/10/2026");
        contenido.agregarOpinion(op);

        assertEquals(op, contenido.buscarOpinion(1));
    }

    @Test
    void editarOpinion() {

        Opinion op = new Opinion(1, "Comentario", "10/10/2026", "10/10/2026");
        contenido.agregarOpinion(op);

        assertTrue(contenido.editarOpinion(1, "Nuevo comentario", "11/10/2026"));
        assertEquals("Nuevo comentario", contenido.buscarOpinion(1).getComentario());
    }

    @Test
    void eliminarOpinion() {

        Opinion op = new Opinion(1, "Comentario", "10/10/2026", "10/10/2026");
        contenido.agregarOpinion(op);
        assertTrue(contenido.eliminarOpinion(1));
        assertNull(contenido.buscarOpinion(1));
    }

    @Test
    void agregarValoracion() {

        Valoracion v = new Valoracion(1, 5, "10/10/2026");
        assertTrue(contenido.agregarValoracion(v));
        assertEquals(1, contenido.getCantidadValoraciones());
    }

    @Test
    void valoracionDuplicadaPorId() {

        Valoracion v1 = new Valoracion(1, 5, "10/10/2026");
        Valoracion v2 = new Valoracion(1, 4, "11/10/2026");
        contenido.agregarValoracion(v1);

        assertFalse(contenido.agregarValoracion(v2));
    }

    @Test
    void buscarValoracion() {

        Valoracion v = new Valoracion(1, 5, "10/10/2026");
        contenido.agregarValoracion(v);
        assertEquals(v, contenido.buscarValoracion(1));
    }

    @Test
    void editarValoracion() {

        Valoracion v = new Valoracion(1, 3, "10/10/2026");
        contenido.agregarValoracion(v);
        assertTrue(contenido.editarValoracion(1, 5, "11/10/2026"));
        assertEquals(5, contenido.buscarValoracion(1).getCalificacion());
    }

    @Test
    void eliminarValoracion() {

        Valoracion v = new Valoracion(1, 5, "10/10/2026");

        contenido.agregarValoracion(v);

        assertTrue(contenido.eliminarValoracion(1));

        assertNull(contenido.buscarValoracion(1));
    }

    @Test
    void listarOpiniones() {

        Opinion op = new Opinion(1, "Excelente juego", "10/10/2026", "10/10/2026");

        contenido.agregarOpinion(op);
        Opinion[] opiniones = contenido.listarOpiniones();

        assertEquals(1, opiniones.length);
        assertEquals(op, opiniones[0]);
    }

    @Test
    void listarValoraciones() {

        Valoracion v = new Valoracion(1, 5, "10/10/2026");
        contenido.agregarValoracion(v);
        Valoracion[] valoraciones = contenido.listarValoraciones();
        assertEquals(1, valoraciones.length);
        assertEquals(v, valoraciones[0]);
    }


}
