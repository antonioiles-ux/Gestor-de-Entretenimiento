package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Pelicula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PeliculaTest {
    //Objeto de prueba
    private Pelicula pelicula;
    private Date fechaContenido;

    //Preparar el objeto de prueba
    @BeforeEach
    public void setUp(){
        fechaContenido = new Date();
        pelicula = new Pelicula(1, "StarWars", "George Lucas", "Ciencia Ficcion", fechaContenido, 120,"+18");

    }

    @Test
    public void peliculaPorDefecto() {
        Pelicula pelicula = new Pelicula();

        // Valores por defecto del padre
        assertEquals(0, pelicula.getIdContenido());
        assertEquals("sin titulo", pelicula.getTitulo());

        // Valores por defecto del hijo
        assertEquals(0, pelicula.getDuracion());
        assertEquals("sin clasificacion", pelicula.getClasificacion());
    }

    @Test
    public void peliculaValida(){

        assertEquals(1, pelicula.getIdContenido());
        assertEquals("StarWars", pelicula.getTitulo());

        assertEquals(120, pelicula.getDuracion());
        assertEquals("+18",pelicula.getClasificacion());
    }

    @Test
    public void testCrearPelicula() {
        assertNotNull(pelicula);
    }

    @Test
    public void testGetDuracion() {
        assertEquals(120, pelicula.getDuracion());
    }

    @Test
    public void testGetClasificacion() {
        assertEquals("+18", pelicula.getClasificacion());
    }

    @Test
    public void testSetDuracion() {
        assertTrue(pelicula.setDuracion(130));
        assertEquals(130, pelicula.getDuracion());

        // Comprobación de rechazo (0 o negativo)
        assertFalse(pelicula.setDuracion(0));
        assertFalse(pelicula.setDuracion(-120));
    }

    @Test
    public void testSetClasificacion() {
        assertTrue(pelicula.setClasificacion("+15"));
        assertEquals("+15", pelicula.getClasificacion());

        // Comprobación de rechazo (vacio)
        assertFalse(pelicula.setClasificacion(""));
    }
}