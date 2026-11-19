package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Contenido;
import ec.edu.uce.gestor.dominio.Libro;
import ec.edu.uce.gestor.dominio.Pelicula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class LibroTest {
    // Objeto de prueba
    private Libro libro;
    private Date fechaContenido;

    // Preparar el objeto de prueba antes de cada test
    @BeforeEach
    public void setUp() {
        fechaContenido = new Date();
        libro = new Libro(1, "Cien años de soledad", "Gabriel Garcia Marquez", "Novela", fechaContenido,300, "Novela clásica de ciencia ficción");
    }

    // Constructores

    @Test
    public void libroDefecto() {
        Libro libro = new Libro();

        // Valores por defecto del padre
        assertEquals(0, libro.getIdContenido());
        assertEquals("sin titulo", libro.getTitulo());
        assertEquals("sin autor", libro.getAutor());
        assertEquals("sin genero",libro.getGenero());
        assertEquals(null,libro.getFechaPublicacion());

        // En un constructor vacío, los int inician en 0 y los String en null por defecto
        assertEquals(0, libro.getNumeroPaginas());
        assertEquals("Sin descripcion", libro.getDescripcion());
    }

    @Test
    public void libroValido() {

        assertEquals(1, libro.getIdContenido());
        assertEquals("Cien años de soledad", libro.getTitulo());
        assertEquals("Gabriel Garcia Marquez", libro.getAutor());
        assertEquals("Novela", libro.getGenero());
        assertEquals(fechaContenido, libro.getFechaPublicacion());

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



    // --- Nuevos tests: Polimorfismo, instanceof, Sobrecarga, equals y hashCode ---

    @Test
    public void testPolimorfismoYInstanceof() {
        Contenido contPolimorfico = libro;

        assertEquals(600, contPolimorfico.calcularTiempoConsumo());
        assertTrue(contPolimorfico instanceof Libro);

        if (contPolimorfico instanceof Libro l) {
            assertEquals(300, l.getNumeroPaginas());
        }
    }

    @Test
    public void testSobrecargaCalcularTiempoConsumo() {
        assertEquals(600, libro.calcularTiempoConsumo());
        assertEquals(900, libro.calcularTiempoConsumo(3));
        assertEquals(0, libro.calcularTiempoConsumo(0));
        assertEquals(0, libro.calcularTiempoConsumo(-2));
    }

    @Test
    public void testEqualsYHashCode() {
        Libro mismoLibro = new Libro(1, "Otro Titulo", "Otro Autor", "Drama", new Date(), 300, "Otra desc");
        Libro distintoLibro = new Libro(2, "Cien años de soledad", "Gabriel Garcia Marquez", "Novela", fechaContenido, 450, "Novela clásica");

        assertEquals(libro, libro);
        assertEquals(libro, mismoLibro);
        assertEquals(libro.hashCode(), mismoLibro.hashCode());
        assertNotEquals(libro, distintoLibro);
        assertFalse(libro.equals(null));
    }
}
