package ec.edu.uce.gestor.dominio;

import ec.edu.uce.gestor.dominio.dominio.Libro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {
    // Objeto de prueba
    private Libro libro;

    // Preparar el objeto de prueba antes de cada test
    @BeforeEach
    public void setUp() {
        libro = new Libro(300, "Novela clásica de ciencia ficción");
    }

    // Constructores

    @Test
    public void libroDefecto() {
        Libro libroVacio = new Libro();

        // En un constructor vacío, los int inician en 0 y los String en null por defecto
        assertEquals(0, libroVacio.getNumeroPaginas());
        assertNull(libroVacio.getDescripcion());
    }

    @Test
    public void libroValido() {
        assertEquals(300, libro.getNumeroPaginas());
        assertEquals("Novela clásica de ciencia ficción", libro.getDescripcion());
    }

    @Test
    public void testCrearLibro() {
        assertNotNull(libro);
    }

    // Getters

    @Test
    public void testGetNumeroPaginas() {
        assertEquals(300, libro.getNumeroPaginas());
    }

    @Test
    public void testGetDescripcion() {
        assertEquals("Novela clásica de ciencia ficción", libro.getDescripcion());
    }

    // Setters Válidos

    @Test
    public void testSetNumeroPaginas() {
        assertTrue(libro.setNumeroPaginas(500));
        assertEquals(500, libro.getNumeroPaginas());
    }

    @Test
    public void testSetDescripcion() {
        assertTrue(libro.setDescripcion("Nueva edición de aniversario"));
        assertEquals("Nueva edición de aniversario", libro.getDescripcion());
    }

    // Setters Inválidos

    @Test
    public void numeroPaginasInvalido() {
        int paginasAntes = libro.getNumeroPaginas();

        // Probando límite y valores negativos
        assertFalse(libro.setNumeroPaginas(0));
        assertFalse(libro.setNumeroPaginas(-50));
        assertEquals(paginasAntes, libro.getNumeroPaginas());
    }

    @Test
    public void descripcionInvalida() {
        String descripcionAntes = libro.getDescripcion();

        // Probando cadena vacía, espacios en blanco y null
        assertFalse(libro.setDescripcion(""));
        assertFalse(libro.setDescripcion("    "));
        assertFalse(libro.setDescripcion(null));
        assertEquals(descripcionAntes, libro.getDescripcion());
    }
}
