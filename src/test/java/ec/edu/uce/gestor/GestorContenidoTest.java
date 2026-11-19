package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.GestorContenido;
import ec.edu.uce.gestor.dominio.Usuario;
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
        assertEquals("Id: 2, Nombre: Juan Perez, Id gestor: 5", gestor.toString());
    }


// --- Nuevos tests: Polimorfismo, instanceof, Sobrecarga, equals y hashCode ---

    @Test
    public void testPolimorfismoYInstanceof() {
        Usuario usuarioPolimorfico = new GestorContenido("Juan Perez", 2, 5);

        assertEquals("Administrador / Gestor de Contenidos", usuarioPolimorfico.obtenerRol());
        assertTrue(usuarioPolimorfico instanceof GestorContenido);

        if (usuarioPolimorfico instanceof GestorContenido g) {
            assertEquals(5, g.getIdGestor());
        }
    }

    @Test
    public void testSobrecargaEliminarOpinion() {
        assertTrue(gestor.eliminarOpinion());
        assertTrue(gestor.eliminarOpinion("Comentario ofensivo"));
        assertFalse(gestor.eliminarOpinion(""));
        assertFalse(gestor.eliminarOpinion(null));
    }

    @Test
    public void testEqualsYHashCode() {
        GestorContenido mismoGestor = new GestorContenido("Juan Perez", 2, 5);
        GestorContenido distintoGestor = new GestorContenido("Juan Perez", 2, 9);

        assertEquals(gestor, gestor);
        assertEquals(gestor, mismoGestor);
        assertEquals(gestor.hashCode(), mismoGestor.hashCode());
        assertNotEquals(gestor, distintoGestor);
        assertFalse(gestor.equals(null));
    }
}

