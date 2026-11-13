package ec.edu.uce.gestor;
import ec.edu.uce.gestor.Valoracion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValoracionTest {

    // objeto de prueba
    private Valoracion valoracion;

    // prepara el objeto de prueba antes de cada test
    @BeforeEach
    public void setUp() {
        valoracion = new Valoracion(2, 4, "2025-01-15");
    }

    // Constructores

    @Test
    void valoracionDatosValidos() {
        Valoracion valor = new Valoracion(1, 5, "2025-02-20");

        assertEquals(1, valor.getIdValoracion());
        assertEquals(5, valor.getCalificacion());
        assertEquals("2025-02-20", valor.getFechaValoracion());
    }

    @Test
    void valoracionDefecto() {
        Valoracion valor = new Valoracion();

        assertEquals(0, valor.getIdValoracion());
        assertEquals(1, valor.getCalificacion());
        assertEquals("sin fecha", valor.getFechaValoracion());
    }

    @Test
    public void testCrearValoracion() {
        assertNotNull(valoracion);
    }

    // Getters

    @Test
    public void testGetIdValoracion() {
        assertEquals(2, valoracion.getIdValoracion());
    }

    @Test
    public void testGetCalificacion() {
        assertEquals(4, valoracion.getCalificacion());
    }

    @Test
    public void testGetFechaValoracion() {
        assertEquals("2025-01-15", valoracion.getFechaValoracion());
    }

    // Id Valoración

    @Test
    public void idValoracionValido() {
        assertTrue(valoracion.setIdValoracion(10));
        assertEquals(10, valoracion.getIdValoracion());
    }

    @Test
    public void idValoracionInvalido() {
        int idAntes = valoracion.getIdValoracion();

        assertFalse(valoracion.setIdValoracion(0));
        assertFalse(valoracion.setIdValoracion(-5));
        assertEquals(idAntes, valoracion.getIdValoracion());
    }

    // Calificación

    @Test
    public void calificacionValida() {
        assertTrue(valoracion.setCalificacion(3));
        assertEquals(3, valoracion.getCalificacion());
    }

    @Test
    public void calificacionLimitesValidos() {
        assertTrue(valoracion.setCalificacion(1));
        assertEquals(1, valoracion.getCalificacion());

        assertTrue(valoracion.setCalificacion(5));
        assertEquals(5, valoracion.getCalificacion());
    }

    @Test
    public void calificacionInvalida() {
        int calificacionAntes = valoracion.getCalificacion();

        assertFalse(valoracion.setCalificacion(0));
        assertFalse(valoracion.setCalificacion(6));
        assertFalse(valoracion.setCalificacion(-3));
        assertEquals(calificacionAntes, valoracion.getCalificacion());
    }

    // Fecha valoración

    @Test
    public void fechaValoracionValida() {
        assertTrue(valoracion.setFechaValoracion("2025-03-10"));
        assertEquals("2025-03-10", valoracion.getFechaValoracion());
    }

    @Test
    public void fechaValoracionInvalida() {
        String fechaAntes = valoracion.getFechaValoracion();

        assertFalse(valoracion.setFechaValoracion(""));
        assertFalse(valoracion.setFechaValoracion(null));
        assertEquals(fechaAntes, valoracion.getFechaValoracion());
    }
}