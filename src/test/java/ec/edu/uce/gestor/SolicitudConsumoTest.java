package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Contenido;
import ec.edu.uce.gestor.dominio.Libro;
import ec.edu.uce.gestor.dominio.SolicitudConsumo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SolicitudConsumoTest {

    private SolicitudConsumo solicitud;
    private Date fechaPrueba;

    @BeforeEach
    public void setUp() {
        // Usamos una fecha fija en milisegundos
        fechaPrueba = new Date(1700000000000L);
        solicitud = new SolicitudConsumo(1, "Aprobada", fechaPrueba, "Libro");
    }

    @Test
    void solicitudDatosValidos() {
        Date fecha = new Date(1700000000000L);
        SolicitudConsumo sol = new SolicitudConsumo(2, "Rechazada", fecha, "Pelicula");

        assertEquals(2, sol.getIdSolicitud());
        assertEquals("Rechazada", sol.getEstado());
        assertEquals(fecha, sol.getFechaSolicitud());
        assertEquals("Pelicula", sol.getTipoContenido());
    }

    @Test
    void solicitudDefecto() {
        SolicitudConsumo sol = new SolicitudConsumo();

        assertEquals(0, sol.getIdSolicitud());
        assertEquals("Pendiente", sol.getEstado());
        assertNotNull(sol.getFechaSolicitud());
        assertEquals("Sin asignar", sol.getTipoContenido());
    }

    @Test
    public void testCrearSolicitud() {
        assertNotNull(solicitud);
    }

    // --- Tests de Getters ---

    @Test
    public void testGetIdSolicitud() {
        assertEquals(1, solicitud.getIdSolicitud());
    }

    @Test
    public void testGetEstado() {
        assertEquals("Aprobada", solicitud.getEstado());
    }

    @Test
    public void testGetFechaSolicitud() {
        assertEquals(fechaPrueba, solicitud.getFechaSolicitud());
    }

    @Test
    public void testGetTipoContenido() {
        assertEquals("Libro", solicitud.getTipoContenido());
    }

    // --- Tests de Setters (Casos Válidos) ---

    @Test
    public void testSetIdSolicitudValido() {
        assertTrue(solicitud.setIdSolicitud(10));
        assertEquals(10, solicitud.getIdSolicitud());
    }

    @Test
    public void testSetEstadoValido() {
        assertTrue(solicitud.setEstado("EnRevision")); // Suponiendo que el validador acepta letras sin espacios
        assertEquals("EnRevision", solicitud.getEstado());
    }

    @Test
    public void testSetFechaSolicitud() {
        Date nuevaFecha = new Date();
        assertTrue(solicitud.setFechaSolicitud(nuevaFecha));
        assertEquals(nuevaFecha, solicitud.getFechaSolicitud());
    }

    @Test
    public void testSetTipoContenidoValido() {
        assertTrue(solicitud.setTipoContenido("Serie"));
        assertEquals("Serie", solicitud.getTipoContenido());
    }

    // --- Tests de Setters (Casos Inválidos) ---

    @Test
    public void testSetIdSolicitudInvalido() {
        // Suponiendo que el validador de números no acepta valores negativos o rechaza ciertos formatos
        assertFalse(solicitud.setIdSolicitud(-5));
        assertEquals(1, solicitud.getIdSolicitud()); // Mantiene el original
    }

    @Test
    public void testSetEstadoInvalido() {
        // Un estado con números debe fallar la validación
        assertFalse(solicitud.setEstado("Estado123"));
        assertEquals("Aprobada", solicitud.getEstado()); // Mantiene el original
    }

    @Test
    public void testSetTipoContenidoInvalido() {
        // Un tipo de contenido con caracteres especiales debe fallar
        assertFalse(solicitud.setTipoContenido("Libro@"));
        assertEquals("Libro", solicitud.getTipoContenido()); // Mantiene el original
    }
    // --- Nuevos tests: Sobrecarga, equals y hashCode ---
    @Test
    public void testSobrecargaActualizarEstado() {
        assertTrue(solicitud.actualizarEstado("Consumido"));
        assertEquals("Consumido", solicitud.getEstado());

        Date nuevaFecha = new Date(1750000000000L);
        assertTrue(solicitud.actualizarEstado("Finalizada", nuevaFecha));
        assertEquals("Finalizada", solicitud.getEstado());
        assertEquals(nuevaFecha, solicitud.getFechaSolicitud());

        assertFalse(solicitud.actualizarEstado("Estado123"));
    }

    @Test
    public void testEqualsYHashCode() {
        SolicitudConsumo mismaSolicitud = new SolicitudConsumo(1, "Pendiente", new Date(), "Libro");
        SolicitudConsumo distintaSolicitud = new SolicitudConsumo(4, "Aprobada", fechaPrueba, "Serie");

        assertEquals(solicitud, solicitud);
        assertEquals(solicitud, mismaSolicitud);
        assertEquals(solicitud.hashCode(), mismaSolicitud.hashCode());
        assertNotEquals(solicitud, distintaSolicitud);
        assertFalse(solicitud.equals(null));
    }

    @Test
    void testBuscarContenidoPorTituloYAutor() {

        Libro libro = new Libro(1, "Clean Code", "Robert Martin", "Programacion", new Date(), 450, "Buenas practicas");
        solicitud.agregarContenido(libro);

        Contenido encontrado = solicitud.buscarContenido("Clean Code", "Robert Martin");
        assertNotNull(encontrado);
        assertEquals(libro, encontrado);
    }

    @Test
    void testEliminarContenido() {

        Libro libro = new Libro(1, "Clean Code", "Robert Martin", "Programacion", new Date(), 450, "Buenas practicas");
        solicitud.agregarContenido(libro);

        assertTrue(solicitud.eliminarContenido(libro.getTitulo(), libro.getAutor()));

        assertNull(solicitud.buscarContenido("Clean Code", "Robert Martin"));
    }

    @Test
    void testReemplazarContenido() {

        Libro libro1 = new Libro(1, "Clean Code", "Robert Martin", "Programacion", new Date(), 450, "Buenas practicas");
        Libro libro2 = new Libro(2, "Effective Java", "Joshua Bloch", "Programacion",new Date(), 500, "Java avanzado");

        solicitud.agregarContenido(libro1);

        assertTrue(solicitud.reemplazarContenido(libro1.getTitulo(), libro1.getAutor(), libro2));
        assertNull(solicitud.buscarContenido("Clean Code", "Robert Martin"));
        assertNotNull(solicitud.buscarContenido("Effective Java", "Joshua Bloch"));
    }

    @Test
    void testListarContenidos() {

        Libro libro = new Libro(1, "Clean Code", "Robert Martin", "Programacion", new Date(), 450, "Buenas practicas");
        solicitud.agregarContenido(libro);
        Contenido[] contenidos = solicitud.listarContenidos();
        assertEquals(1, contenidos.length);
        assertEquals(libro, contenidos[0]);
    }

    @Test
    void testNoAgregarContenidoDuplicado() {

        Libro libro1 = new Libro(1, "Clean Code", "Robert Martin", "Programacion", new Date(), 450, "Buenas practicas");
        Libro libro2 = new Libro(2, "Clean Code", "Robert Martin", "Programacion", new Date(), 500, "Otra descripcion");
        assertTrue(solicitud.agregarContenido(libro1));
        assertFalse(solicitud.agregarContenido(libro2));
    }

}

