package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Cuenta;
import ec.edu.uce.gestor.dominio.GestorContenido;
import ec.edu.uce.gestor.dominio.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class GestorContenidoTest {

    private GestorContenido gestor;

    @BeforeEach
    public void setUp() {
        gestor = new GestorContenido(5);
    }

    // ==========================
    // CONSTRUCTORES
    // ==========================

    @Test
    void gestorDatosValidos() {

        GestorContenido gestor =
                new GestorContenido(3);

        assertEquals(3, gestor.getIdGestor());
    }

    @Test
    void gestorDefecto() {

        GestorContenido gestor =
                new GestorContenido();

        assertEquals(0, gestor.getIdGestor());
    }

    @Test
    public void testCrearGestor() {
        assertNotNull(gestor);
    }

    // ==========================
    // GETTERS
    // ==========================

    @Test
    public void testGetIdGestor() {
        assertEquals(5, gestor.getIdGestor());
    }

    // ==========================
    // SETTERS
    // ==========================

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

    // ==========================
    // MÉTODOS DEL UML
    // ==========================

    @Test
    public void testSuspenderCuenta() {

        assertTrue(gestor.suspenderCuenta());
    }

    @Test
    public void testReactivarCuenta() {

        assertTrue(gestor.reactivarCuenta());
    }

    @Test
    public void testEliminarCuenta() {

        assertTrue(gestor.eliminarCuenta());
    }

    @Test
    public void testEliminarOpinion() {

        assertTrue(gestor.eliminarOpinion());
    }

    // ==========================
    // TOSTRING
    // ==========================

    @Test
    public void testToString() {

        assertEquals(
                "Gestor Contenido, Id gestor: 5",
                gestor.toString()
        );
    }

    // ==========================
    // EQUALS Y HASHCODE
    // ==========================

    @Test
    public void testEqualsYHashCode() {

        GestorContenido mismoGestor =
                new GestorContenido(5);

        GestorContenido distintoGestor =
                new GestorContenido(9);

        assertEquals(gestor, gestor);

        assertEquals(
                gestor,
                mismoGestor
        );

        assertEquals(
                gestor.hashCode(),
                mismoGestor.hashCode()
        );

        assertNotEquals(
                gestor,
                distintoGestor
        );

        assertFalse(
                gestor.equals(null)
        );
    }

    //CRUD de gestorContenido
    @Test
    public void testAgregarUsuario() {

        Usuario usuario = new Usuario("Carlos Perez", 1);

        assertTrue(gestor.agregarUsuario(usuario));
        assertEquals(1, gestor.getCantidadUsuarios());
    }

    @Test
    public void testAgregarUsuarioDuplicado() {

        Usuario usuario1 = new Usuario("Carlos Perez", 1);
        Usuario usuario2 = new Usuario("Maria Lopez", 1);

        assertTrue(gestor.agregarUsuario(usuario1));

        assertFalse(gestor.agregarUsuario(usuario2));
    }

    @Test
    public void testBuscarUsuario() {

        Usuario usuario = new Usuario("Carlos Perez", 1);

        gestor.agregarUsuario(usuario);

        Usuario encontrado = gestor.buscarUsuario(1);

        assertNotNull(encontrado);
        assertEquals("Carlos Perez",
                encontrado.getNombreCompleto());
    }

    @Test
    public void testBuscarUsuarioInexistente() {

        assertNull(gestor.buscarUsuario(99));
    }

    @Test
    public void testActualizarNombreUsuario() {

        Usuario usuario = new Usuario("Carlos Perez", 1);

        gestor.agregarUsuario(usuario);

        assertTrue(
                gestor.actualizarNombreUsuario(
                        1,
                        "Pedro Gomez"
                )
        );

        assertEquals(
                "Pedro Gomez",
                gestor.buscarUsuario(1)
                        .getNombreCompleto()
        );
    }

    @Test
    public void testActualizarNombreUsuarioInexistente() {

        assertFalse(
                gestor.actualizarNombreUsuario(
                        99,
                        "Pedro Gomez"
                )
        );
    }

    @Test
    public void testEliminarUsuario() {

        Usuario usuario = new Usuario("Carlos Perez", 1);

        gestor.agregarUsuario(usuario);

        assertTrue(gestor.eliminarUsuario(1));

        assertNull(gestor.buscarUsuario(1));

        assertEquals(0,
                gestor.getCantidadUsuarios());
    }

    @Test
    public void testEliminarUsuarioInexistente() {

        assertFalse(
                gestor.eliminarUsuario(99)
        );
    }

    @Test
    public void testListarUsuarios() {

        Usuario usuario1 =
                new Usuario("Carlos Perez", 1);

        Usuario usuario2 =
                new Usuario("Ana Lopez", 2);

        gestor.agregarUsuario(usuario1);
        gestor.agregarUsuario(usuario2);

        Usuario[] usuarios =
                gestor.listarUsuarios();

        assertEquals(2, usuarios.length);

        assertEquals("Carlos Perez",
                usuarios[0].getNombreCompleto());

        assertEquals("Ana Lopez",
                usuarios[1].getNombreCompleto());
    }

    //Duplicaciones
    @Test
    public void testAgregarUsuarioCorreoDuplicado() {

        Cuenta cuenta1 = new Cuenta(
                1,
                "correo@uce.edu.ec",
                "12345",
                new Date()
        );

        Cuenta cuenta2 = new Cuenta(
                2,
                "correo@uce.edu.ec",
                "54321",
                new Date()
        );

        Usuario usuario1 =
                new Usuario("Carlos", 1, cuenta1);

        Usuario usuario2 =
                new Usuario("Pedro", 2, cuenta2);

        assertTrue(
                gestor.agregarUsuario(usuario1)
        );

        assertFalse(
                gestor.agregarUsuario(usuario2)
        );
    }
}