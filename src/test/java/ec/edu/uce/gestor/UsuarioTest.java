package ec.edu.uce.gestor;
import ec.edu.uce.gestor.dominio.Usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    // objeto de prueba
    private Usuario usuario;

    // prepara el objeto de prueba antes de cada test
    @BeforeEach
    public void setUp() {
        usuario = new Usuario("Juan Perez", 2);
    }

    // Constructores

    @Test
    void usuarioDatosValidos() {
        Usuario user = new Usuario("Ana Lopez", 1);

        assertEquals("Ana Lopez", user.getNombreCompleto());
        assertEquals(1, user.getIdUsuario());
    }

    @Test
    void usuarioDefecto() {
        Usuario user = new Usuario();

        assertEquals("sin nombre", user.getNombreCompleto());
        assertEquals(0, user.getIdUsuario());
    }

    @Test
    public void testCrearUsuario() {
        assertNotNull(usuario);
    }

    // Getters

    @Test
    public void testGetNombreCompleto() {
        assertEquals("Juan Perez", usuario.getNombreCompleto());
    }

    @Test
    public void testGetIdUsuario() {
        assertEquals(2, usuario.getIdUsuario());
    }

    // Nombre completo

    @Test
    public void nombreCompletoValido() {
        assertTrue(usuario.setNombreCompleto("Pepe Gomez"));
        assertEquals("Pepe Gomez", usuario.getNombreCompleto());
    }

    @Test
    public void nombreCompletoInvalido() {
        String nombreAntes = usuario.getNombreCompleto();

        assertFalse(usuario.setNombreCompleto(""));
        assertFalse(usuario.setNombreCompleto(null));
        assertEquals(nombreAntes, usuario.getNombreCompleto());
    }

    // Id usuario

    @Test
    public void idUsuarioValido() {
        assertTrue(usuario.setIdUsuario(10));
        assertEquals(10, usuario.getIdUsuario());
    }

    @Test
    public void idUsuarioInvalido() {
        int idAntes = usuario.getIdUsuario();

        assertFalse(usuario.setIdUsuario(0));
        assertFalse(usuario.setIdUsuario(-5));
        assertEquals(idAntes, usuario.getIdUsuario());
    }



    // --- Nuevos tests: Polimorfismo base, Sobrecarga, equals y hashCode ---

    @Test
    public void testObtenerRolYSobrecargaMostrarPerfil() {
        assertEquals("Usuario Registrado", usuario.obtenerRol());
        assertEquals("Id: 2, Nombre: Juan Perez", usuario.mostrarPerfil());
        assertEquals("Id: 2, Nombre: Juan Perez, Rol: Usuario Registrado", usuario.mostrarPerfil(true));
        assertEquals("Id: 2, Nombre: Juan Perez", usuario.mostrarPerfil(false));
    }

    @Test
    public void testEqualsYHashCode() {
        Usuario mismoUsuario = new Usuario("Juan Perez", 2);
        Usuario distintoUsuario = new Usuario("Maria Lopez", 3);

        assertEquals(usuario, usuario);
        assertEquals(usuario, mismoUsuario);
        assertEquals(usuario.hashCode(), mismoUsuario.hashCode());
        assertNotEquals(usuario, distintoUsuario);
        assertFalse(usuario.equals(null));
    }
}
