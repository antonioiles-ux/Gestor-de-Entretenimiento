package ec.edu.uce.gestor.test;

import ec.edu.uce.gestor.dominio.Contenido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ContenidoTest {
    //Objeto de prueba
    private Contenido contenido;

    //Preparar el objeto de prueba
    @BeforeEach
    public void setUp(){
        Date fecha = new Date();
        contenido = new Contenido(2, "StarWars", "George Lucas", "Ciencia Ficcion", fecha);

    }

    @Test
    public void contenidoDefecto() {
        Contenido contenido = new Contenido();

        assertEquals(0, contenido.getIdContenido());
        assertEquals("sin titulo", contenido.getTitulo());
        assertEquals("sin autor", contenido.getAutor());
        assertEquals("sin genero", contenido.getGenero());
    }

    @Test
    public void contenidoValido() {
        Date fecha = new Date();

        Contenido contenido = new Contenido(1, "El Principito", "Antoine de Saint-Exupéry", "Fábula", fecha);

        assertEquals(1, contenido.getIdContenido());
        assertEquals("El Principito", contenido.getTitulo());
        assertEquals("Antoine de Saint-Exupéry", contenido.getAutor());
        assertEquals("Fábula", contenido.getGenero());
        assertEquals(fecha, contenido.getFechaPublicacion());
    }

    @Test
    public void testCrearContenido(){
        assertNotNull(contenido);

    }

    @Test
    public void testGetId(){
        assertEquals(2,contenido.getIdContenido());
    }

    @Test
    public void testGetTitulo(){
        assertEquals("StarWars",contenido.getTitulo());
    }

    @Test
    public void testGetAutor(){
        assertEquals("George Lucas", contenido.getAutor());
    }

    @Test
    public void testGetGenero(){
        assertEquals("Ciencia Ficcion", contenido.getGenero());
    }

    @Test
    public void testGetFecha(){
        Date fecha = new Date();
        assertEquals(fecha, contenido.getFechaPublicacion());
    }

    @Test
    public void testSetIdContenido() {
        Contenido contenido = new Contenido();
        contenido.setIdContenido(10);

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