package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.dominio.GestorContenido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GestorContenidoTest {

    // objeto de prueba
    private GestorContenido gestor;

    // prepara el objeto de prueba antes de cada test
    @BeforeEach
    public void setUp() {
        gestor = new GestorContenido("Juan Perez", 2, 5);
    }

    // Constructores

    @Test
    void gestorDatosValidos() {
        GestorContenido gestor = new GestorContenido("Ana Lopez", 1, 3);

        assertEquals("Ana Lopez", gestor.getNombreCompleto());
        assertEquals(1, gestor.getIdUsuario());
        assertEquals(3, gestor.getIdGestor());
    }

    @Test
    void gestorDefecto() {
        GestorContenido gestor = new GestorContenido();

        assertEquals("sin nombre", gestor.getNombreCompleto());
        assertEquals(0, gestor.getIdUsuario());
        assertEquals(0, gestor.getIdGestor());
    }

    @Test
    public void testCrearGestor() {
        assertNotNull(gestor);
    }

    // Getters

    @Test
    public void testGetNombreCompleto() {
        assertEquals("Juan Perez", gestor.getNombreCompleto());
    }

    @Test
    public void testGetIdUsuario() {
        assertEquals(2, gestor.getIdUsuario());
    }

    @Test
    public void testGetIdGestor() {
        assertEquals(5, gestor.getIdGestor());
    }

    // Nombre completo (heredado)

    @Test
    public void nombreCompletoValido() {
        assertTrue(gestor.setNombreCompleto("Pepe Gomez"));
        assertEquals("Pepe Gomez", gestor.getNombreCompleto());
    }

    @Test
    public void nombreCompletoInvalido() {
        String nombreAntes = gestor.getNombreCompleto();

        assertFalse(gestor.setNombreCompleto(""));
        assertFalse(gestor.setNombreCompleto(null));
        assertEquals(nombreAntes, gestor.getNombreCompleto());
    }

    // Id usuario (heredado)

    @Test
    public void idUsuarioValido() {
        assertTrue(gestor.setIdUsuario(10));
        assertEquals(10, gestor.getIdUsuario());
    }

    @Test
    public void idUsuarioInvalido() {
        int idAntes = gestor.getIdUsuario();

        assertFalse(gestor.setIdUsuario(0));
        assertFalse(gestor.setIdUsuario(-5));
        assertEquals(idAntes, gestor.getIdUsuario());
    }

    // Id gestor

    @Test
    public void idGestorValido() {
        assertTrue(gestor.setIdGestor(10));
        assertEquals(10, gestor.getIdGestor());
    }

    @Test
    public void idGestorInvalido() {
        int idAntes = gestor.getIdGestor();

        assertFalse(gestor.setIdGestor(0));
        assertFalse(gestor.setIdGestor(-5));
        assertEquals(idAntes, gestor.getIdGestor());
    }

    // toString

    @Test
    public void testToString() {
        assertEquals("Id gestor: 5, Id: 2, Nombre: Juan Perez", gestor.toString());
    }
}