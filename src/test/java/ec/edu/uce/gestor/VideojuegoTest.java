package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Videojuego;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VideojuegoTest {
    // Objeto de prueba
    private Videojuego videojuego;

    // Preparar el objeto de prueba antes de cada test
    @BeforeEach
    public void setUp() {
        videojuego = new Videojuego("PC", "PEGI 12");
    }

    // Constructores

    @Test
    public void videojuegoDefecto() {
        Videojuego videojuegoVacio = new Videojuego();
        assertEquals("Sin plataforma", videojuegoVacio.getPlataforma());
        assertEquals("Sin Clasificacion", videojuegoVacio.getClasificacionEdad());
    }

    @Test
    public void videojuegoValido() {
        assertEquals("PC", videojuego.getPlataforma());
        assertEquals("PEGI 12", videojuego.getClasificacionEdad());
    }

    @Test
    public void testCrearVideojuego() {
        assertNotNull(videojuego);
    }

    // Getters

    @Test
    public void testGetPlataforma() {
        assertEquals("PC", videojuego.getPlataforma());
    }

    @Test
    public void testGetClasificacionEdad() {
        assertEquals("PEGI 12", videojuego.getClasificacionEdad());
    }

    // Setters Válidos

    @Test
    public void testSetPlataforma() {
        assertTrue(videojuego.setPlataforma("PlayStation 5"));
        assertEquals("PlayStation 5", videojuego.getPlataforma());
    }

    @Test
    public void testSetClasificacionEdad() {
        assertTrue(videojuego.setClasificacionEdad("PEGI 18"));
        assertEquals("PEGI 18", videojuego.getClasificacionEdad());
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
