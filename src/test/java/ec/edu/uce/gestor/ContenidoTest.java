package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Contenido;
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
}
