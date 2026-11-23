package ec.edu.uce.gestor;
import ec.edu.uce.gestor.dominio.Contenido;
import ec.edu.uce.gestor.dominio.Cuenta;
import ec.edu.uce.gestor.dominio.SolicitudConsumo;
import ec.edu.uce.gestor.dominio.Usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("Ana", 1);
    }

    // ---------- DATOS BÁSICOS Y RELACIÓN UNO A UNO ----------
    @Test
    void constructorAsignaDatosYCrea​Cuenta() {
        assertEquals(1, usuario.getIdUsuario());
        assertEquals("Ana", usuario.getNombreCompleto());
        assertNotNull(usuario.getCuenta()); // composición: la cuenta se crea dentro
    }

    @Test
    void setIdInvalidoDevuelveFalse() {
        assertFalse(usuario.setIdUsuario(0));
        assertFalse(usuario.setIdUsuario(-5));
        assertEquals(1, usuario.getIdUsuario()); // no cambió
    }

    @Test
    void setCuentaNullDevuelveFalse() {
        assertFalse(usuario.setCuenta(null));
        assertNotNull(usuario.getCuenta());
    }

    @Test
    void setCuentaValidaReemplazaLaCuenta() {
        Cuenta nueva = new Cuenta();
        assertTrue(usuario.setCuenta(nueva));
        assertSame(nueva, usuario.getCuenta());
    }

    // ---------- CRUD SOLICITUDES ----------
    @Test
    void agregarSolicitudValidaVinculaSolicitante() {
        SolicitudConsumo s = new SolicitudConsumo(1, "Pendiente", new Date(), "Libro");
        assertTrue(usuario.agregarSolicitud(s));
        assertEquals(1, usuario.getCantidadSolicitudes());
        assertSame(usuario, s.getSolicitante());
    }

    @Test
    void agregarSolicitudNullDevuelveFalse() {
        assertFalse(usuario.agregarSolicitud(null));
        assertEquals(0, usuario.getCantidadSolicitudes());
    }

    @Test
    void noPermiteSolicitudConMismoId() {
        usuario.agregarSolicitud(new SolicitudConsumo(1, "Pendiente", new Date(), "Libro"));
        boolean resultado = usuario.agregarSolicitud(new SolicitudConsumo(1, "Pendiente", new Date(), "Pelicula"));
        assertFalse(resultado);
        assertEquals(1, usuario.getCantidadSolicitudes());
    }

    @Test
    void noPermiteDosPendientesDelMismoTipoSinContenidos() {
        usuario.agregarSolicitud(new SolicitudConsumo(1, "Pendiente", new Date(), "Libro"));
        boolean resultado = usuario.agregarSolicitud(new SolicitudConsumo(2, "Pendiente", new Date(), "libro"));
        assertFalse(resultado);
    }

    @Test
    void permiteSolicitudesDeTiposDistintos() {
        assertTrue(usuario.agregarSolicitud(new SolicitudConsumo(1, "Pendiente", new Date(), "Libro")));
        assertTrue(usuario.agregarSolicitud(new SolicitudConsumo(2, "Pendiente", new Date(), "Pelicula")));
        assertEquals(2, usuario.getCantidadSolicitudes());
    }

    @Test
    void permiteMismoTipoSiLaAnteriorYaNoEstaPendiente() {
        usuario.agregarSolicitud(new SolicitudConsumo(1, "Aprobada", new Date(), "Libro"));
        assertTrue(usuario.agregarSolicitud(new SolicitudConsumo(2, "Pendiente", new Date(), "Libro")));
    }

    @Test
    void noPermiteSolicitudConContenidoYaSolicitado() {
        Contenido c = new Contenido(10, "Quijote", "Cervantes", "Novela", new Date());

        SolicitudConsumo s1 = new SolicitudConsumo(1, "Pendiente", new Date(), "Libro");
        s1.agregarContenido(c);
        usuario.agregarSolicitud(s1);

        SolicitudConsumo s2 = new SolicitudConsumo(2, "Pendiente", new Date(), "Libro");
        s2.agregarContenido(c);
        assertFalse(usuario.agregarSolicitud(s2));
    }

    @Test
    void buscarSolicitudExistenteYNoExistente() {
        SolicitudConsumo s = new SolicitudConsumo(1, "Pendiente", new Date(), "Libro");
        usuario.agregarSolicitud(s);
        assertSame(s, usuario.buscarSolicitud(1));
        assertNull(usuario.buscarSolicitud(99));
    }

    @Test
    void editarSolicitudCambiaElEstado() {
        usuario.agregarSolicitud(new SolicitudConsumo(1, "Pendiente", new Date(), "Libro"));
        assertTrue(usuario.editarSolicitud(1, "Aprobada"));
        assertEquals("Aprobada", usuario.buscarSolicitud(1).getEstado());
    }

    @Test
    void editarSolicitudConEstadoInvalidoOInexistenteDevuelveFalse() {
        usuario.agregarSolicitud(new SolicitudConsumo(1, "Pendiente", new Date(), "Libro"));
        assertFalse(usuario.editarSolicitud(1, "Estado123")); // tiene dígitos
        assertFalse(usuario.editarSolicitud(99, "Aprobada")); // no existe
        assertEquals("Pendiente", usuario.buscarSolicitud(1).getEstado());
    }

    @Test
    void eliminarSolicitudDesplazaElArreglo() {
        usuario.agregarSolicitud(new SolicitudConsumo(1, "Pendiente", new Date(), "Libro"));
        usuario.agregarSolicitud(new SolicitudConsumo(2, "Pendiente", new Date(), "Pelicula"));
        usuario.agregarSolicitud(new SolicitudConsumo(3, "Pendiente", new Date(), "Serie"));

        assertTrue(usuario.eliminarSolicitud(2));
        assertEquals(2, usuario.getCantidadSolicitudes());
        assertNull(usuario.buscarSolicitud(2));
        assertNotNull(usuario.buscarSolicitud(3)); // se desplazó bien
        assertFalse(usuario.eliminarSolicitud(2)); // ya no existe
    }

    @Test
    void listarSolicitudesDevuelveCopiaDelTamanoCorrecto() {
        usuario.agregarSolicitud(new SolicitudConsumo(1, "Pendiente", new Date(), "Libro"));
        SolicitudConsumo[] lista = usuario.listarSolicitudes();
        assertEquals(1, lista.length);

        lista[0] = null; // modificar la copia no afecta al original
        assertNotNull(usuario.buscarSolicitud(1));
    }

    // ---------- CRUD CONTENIDOS ----------
    @Test
    void agregarContenidoValido() {
        Contenido c = new Contenido(1, "Quijote", "Cervantes", "Novela", new Date());
        assertTrue(usuario.agregarContenido(c));
        assertEquals(1, usuario.getCantidadContenidos());
    }

    @Test
    void agregarContenidoNullDevuelveFalse() {
        assertFalse(usuario.agregarContenido(null));
    }

    @Test
    void noPermiteContenidoConMismoId() {
        usuario.agregarContenido(new Contenido(1, "Quijote", "Cervantes", "Novela", new Date()));
        assertFalse(usuario.agregarContenido(new Contenido(1, "Otro", "Autor", "Drama", new Date())));
    }

    @Test
    void noPermiteContenidoConMismoTituloYAutor() {
        usuario.agregarContenido(new Contenido(1, "Quijote", "Cervantes", "Novela", new Date()));
        assertFalse(usuario.agregarContenido(new Contenido(2, " quijote ", "CERVANTES", "Novela", new Date())));
        assertEquals(1, usuario.getCantidadContenidos());
    }

    @Test
    void permiteMismoTituloConDistintoAutor() {
        usuario.agregarContenido(new Contenido(1, "Dune", "Herbert", "Ficcion", new Date()));
        assertTrue(usuario.agregarContenido(new Contenido(2, "Dune", "Lynch", "Ficcion", new Date())));
    }

    @Test
    void buscarContenidoExistenteYNoExistente() {
        Contenido c = new Contenido(1, "Quijote", "Cervantes", "Novela", new Date());
        usuario.agregarContenido(c);
        assertSame(c, usuario.buscarContenido(1));
        assertNull(usuario.buscarContenido(99));
    }

    @Test
    void editarContenidoCambiaElTitulo() {
        usuario.agregarContenido(new Contenido(1, "Quijote", "Cervantes", "Novela", new Date()));
        assertTrue(usuario.editarContenido(1, "Nuevo"));
        assertEquals("Nuevo", usuario.buscarContenido(1).getTitulo());
        assertFalse(usuario.editarContenido(99, "Otro")); // no existe
    }

    @Test
    void eliminarContenido() {
        usuario.agregarContenido(new Contenido(1, "Quijote", "Cervantes", "Novela", new Date()));
        usuario.agregarContenido(new Contenido(2, "Dune", "Herbert", "Ficcion", new Date()));

        assertTrue(usuario.eliminarContenido(1));
        assertEquals(1, usuario.getCantidadContenidos());
        assertNull(usuario.buscarContenido(1));
        assertNotNull(usuario.buscarContenido(2));
        assertFalse(usuario.eliminarContenido(1));
    }

    @Test
    void listarContenidosDevuelveTamanoCorrecto() {
        usuario.agregarContenido(new Contenido(1, "Quijote", "Cervantes", "Novela", new Date()));
        assertEquals(1, usuario.listarContenidos().length);
    }

    // ---------- POLIMORFISMO / SOBRECARGA ----------
    @Test
    void obtenerRolYMostrarPerfil() {
        assertEquals("Usuario Registrado", usuario.obtenerRol());
        assertEquals("Id: 1, Nombre: Ana", usuario.mostrarPerfil());
        assertEquals("Id: 1, Nombre: Ana, Rol: Usuario Registrado", usuario.mostrarPerfil(true));
    }

    @Test
    void esMismoUsuario() {
        assertTrue(usuario.esMismoUsuario(new Usuario("Otro", 1)));
        assertFalse(usuario.esMismoUsuario(new Usuario("Ana", 2)));
        assertFalse(usuario.esMismoUsuario(null));
    }

    // ---------- EQUALS Y HASHCODE ----------
    @Test
    void equalsYHashCode() {
        Usuario igual = new Usuario("Ana", 1);
        Usuario distinto = new Usuario("Ana", 2);

        assertEquals(usuario, igual);
        assertEquals(usuario.hashCode(), igual.hashCode());
        assertNotEquals(usuario, distinto);
        assertNotEquals(usuario, null);
        assertNotEquals(usuario, "texto");
    }
}