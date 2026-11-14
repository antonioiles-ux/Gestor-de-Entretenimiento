package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Videojuego;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class VideojuegoTest {
    // Objeto de prueba
    private Videojuego videojuego;
    private Date fechaContenido;

    // Preparar el objeto de prueba antes de cada test
    @BeforeEach
    public void setUp() {
        fechaContenido = new Date();
        videojuego = new Videojuego(1, "GOD OF WAR", "David Jaffe", "Videojuego de Accion y Aventura", fechaContenido, "PSP Y PS", "Mayores +18");
    }

    // Constructores

    @Test
    public void videojuegoDefecto() {
        Videojuego videojuegoVacio = new Videojuego();

        // Valores por defecto del padre
        assertEquals(0, videojuego.getIdContenido());
        assertEquals("sin titulo", videojuego.getTitulo());
        assertEquals("sin autor",videojuego.getAutor());
        assertEquals("sin genero",videojuego.getGenero());
        assertEquals(null,videojuego.getFechaPublicacion());
        // Valores por defecto de la hija
        assertEquals("Sin plataforma", videojuegoVacio.getPlataforma());
        assertEquals("Sin Clasificacion", videojuegoVacio.getClasificacionEdad());
    }

    @Test
    public void videojuegoValido() {

        assertEquals(1, videojuego.getIdContenido());
        assertEquals("GOD OF WAR", videojuego.getTitulo());
        assertEquals("David Jaffe", videojuego.getAutor());
        assertEquals("Videojuego de Accion y Aventura", videojuego.getGenero());
        assertEquals(fechaContenido, videojuego.getFechaPublicacion());

        assertEquals("PSP y PS", videojuego.getPlataforma());
        assertEquals("Mayores +18", videojuego.getClasificacionEdad());
    }

    @Test
    public void testCrearVideojuego() {
        assertNotNull(videojuego);
    }

    // Getters

    @Test
    public void testGetPlataforma() {
        assertEquals("PSP y PS", videojuego.getPlataforma());
    }

    @Test
    public void testGetClasificacionEdad() {
        assertEquals("Mayores +18", videojuego.getClasificacionEdad());
    }

    // Setters Válidos

    @Test
    public void testSetPlataforma() {
        assertTrue(videojuego.setPlataforma("PlayStation 5"));
        assertEquals("PlayStation 5", videojuego.getPlataforma());
    }

    @Test
    public void testSetClasificacionEdad() {
        assertTrue(videojuego.setClasificacionEdad("Toda edad"));
        assertEquals("Toda edad", videojuego.getClasificacionEdad());
    }

    // Setters Inválidos

    @Test
    public void plataformaInvalida() {
        String plataformaAntes = videojuego.getPlataforma();

        // Probando valor null según la validación del setter en Videojuego
        assertFalse(videojuego.setPlataforma(null));
        assertEquals(plataformaAntes, videojuego.getPlataforma());
    }

    @Test
    public void clasificacionEdadInvalida() {
        String clasificacionAntes = videojuego.getClasificacionEdad();

        // Probando valor null según la validación del setter en Videojuego
        assertFalse(videojuego.setClasificacionEdad(null));
        assertEquals(clasificacionAntes, videojuego.getClasificacionEdad());
    }
}
