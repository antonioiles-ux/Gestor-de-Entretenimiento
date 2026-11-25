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

    // =========================================================================
    // 1. CONSTRUCTORES, COMPOSICIÓN (1 A 1) Y VALIDACIONES DE SETTERS
    // =========================================================================

    @Test
    void constructorPorDefectoInicializaCuentaYArreglo() {
        Usuario vacio = new Usuario();
        assertEquals("sin nombre", vacio.getNombreCompleto());
        assertEquals(0, vacio.getIdUsuario());
        assertEquals(0, vacio.getCantidadSolicitudes());
        assertNotNull(vacio.getCuenta());
    }

    @Test
    void constructorAsignaDatosYCreaCuenta() {
        assertEquals(1, usuario.getIdUsuario());
        assertEquals("Ana", usuario.getNombreCompleto());
        assertNotNull(usuario.getCuenta());
    }

    @Test
    void constructorConCuentaPersonalizada() {
        Cuenta cuenta = new Cuenta(5, "ana@uce.edu.ec", "clave123", new Date());
        Usuario u = new Usuario("Ana", 1, cuenta);
        assertSame(cuenta, u.getCuenta());
    }

    @Test
    void setNombreCompletoValidoEInvalido() {
        assertTrue(usuario.setNombreCompleto("  Juan Perez  "));
        assertEquals("Juan Perez", usuario.getNombreCompleto());

        assertFalse(usuario.setNombreCompleto(""));
        assertFalse(usuario.setNombreCompleto("   "));
        assertFalse(usuario.setNombreCompleto("Ana123"));
        assertFalse(usuario.setNombreCompleto(null));
        assertEquals("Juan Perez", usuario.getNombreCompleto());
    }

    @Test
    void setIdInvalidoDevuelveFalse() {
        assertFalse(usuario.setIdUsuario(0));
        assertFalse(usuario.setIdUsuario(-5));
        assertEquals(1, usuario.getIdUsuario());
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

    // =========================================================================
    // 2. CRUD SOLICITUDES: CREAR Y REGLAS DE DUPLICADOS / CAPACIDAD
    // =========================================================================

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
    void noPermiteSuperarCapacidadDelArreglo() {
        Usuario usuarioLimitado = new Usuario(2);
        assertTrue(usuarioLimitado.agregarSolicitud(new SolicitudConsumo(1, "Pendiente", new Date(), "Libro")));
        assertTrue(usuarioLimitado.agregarSolicitud(new SolicitudConsumo(2, "Pendiente", new Date(), "Pelicula")));
        assertFalse(usuarioLimitado.agregarSolicitud(new SolicitudConsumo(3, "Pendiente", new Date(), "Serie")));
        assertEquals(2, usuarioLimitado.getCantidadSolicitudes());
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

    // =========================================================================
    // 3. CRUD SOLICITUDES: LISTAR Y BUSCAR (SOBRECARGAS)
    // =========================================================================

    @Test
    void listarSolicitudesDevuelveCopiaExactaSinHuecos() {
        usuario.agregarSolicitud(new SolicitudConsumo(1, "Pendiente", new Date(), "Libro"));
        SolicitudConsumo[] lista = usuario.listarSolicitudes();
        assertEquals(1, lista.length);

        lista[0] = null;
        assertNotNull(usuario.buscarSolicitud("Libro"));
    }

    @Test
    void buscarSolicitudPorTipoContenido() {
        SolicitudConsumo s = new SolicitudConsumo(1, "Pendiente", new Date(), "Pelicula");
        usuario.agregarSolicitud(s);

        assertSame(s, usuario.buscarSolicitud("pelicula"));
        assertSame(s, usuario.buscarSolicitud("  PELICULA  "));
        assertNull(usuario.buscarSolicitud("Serie"));
        assertNull(usuario.buscarSolicitud("   "));
        assertNull(usuario.buscarSolicitud((String) null));
    }

    @Test
    void buscarSolicitudPorObjeto() {
        SolicitudConsumo s = new SolicitudConsumo(1, "Pendiente", new Date(), "Libro");
        usuario.agregarSolicitud(s);

        SolicitudConsumo referenciaIgual = new SolicitudConsumo(1, "Aprobada", new Date(), "Libro");
        SolicitudConsumo distinta = new SolicitudConsumo(9, "Pendiente", new Date(), "Serie");

        assertSame(s, usuario.buscarSolicitud(referenciaIgual));
        assertNull(usuario.buscarSolicitud(distinta));
        assertNull(usuario.buscarSolicitud((SolicitudConsumo) null));
    }

    @Test
    void buscarSolicitudPorId() {
        SolicitudConsumo s = new SolicitudConsumo(1, "Pendiente", new Date(), "Libro");
        usuario.agregarSolicitud(s);

        assertSame(s, usuario.buscarSolicitud(1));
        assertNull(usuario.buscarSolicitud(99));
    }

    // =========================================================================
    // 4. CRUD SOLICITUDES: ACTUALIZAR Y ELIMINAR (SOBRECARGAS)
    // =========================================================================

    @Test
    void actualizarEstadoSolicitudPorTipoContenido() {
        usuario.agregarSolicitud(new SolicitudConsumo(1, "Pendiente", new Date(), "Libro"));

        assertTrue(usuario.actualizarEstadoSolicitud("libro", "Consumido"));
        assertEquals("Consumido", usuario.buscarSolicitud("Libro").getEstado());

        assertFalse(usuario.actualizarEstadoSolicitud("Libro", "   "));
        assertFalse(usuario.actualizarEstadoSolicitud("Libro", "Estado123"));
        assertFalse(usuario.actualizarEstadoSolicitud("Inexistente", "Aprobada"));
    }

    @Test
    void actualizarEstadoSolicitudPorObjeto() {
        SolicitudConsumo s = new SolicitudConsumo(1, "Pendiente", new Date(), "Pelicula");
        usuario.agregarSolicitud(s);

        assertTrue(usuario.actualizarEstadoSolicitud(s, "Aprobada"));
        assertEquals("Aprobada", s.getEstado());
        assertFalse(usuario.actualizarEstadoSolicitud((SolicitudConsumo) null, "Consumido"));
    }

    @Test
    void editarSolicitudPorIdCambiaElEstado() {
        usuario.agregarSolicitud(new SolicitudConsumo(1, "Pendiente", new Date(), "Libro"));
        assertTrue(usuario.editarSolicitud(1, "Aprobada"));
        assertEquals("Aprobada", usuario.buscarSolicitud(1).getEstado());

        assertFalse(usuario.editarSolicitud(1, "Estado123"));
        assertFalse(usuario.editarSolicitud(99, "Aprobada"));
        assertEquals("Aprobada", usuario.buscarSolicitud(1).getEstado());
    }

    @Test
    void eliminarSolicitudPorTipoContenidoDesplazaSinHuecos() {
        usuario.agregarSolicitud(new SolicitudConsumo(1, "Pendiente", new Date(), "Libro"));
        usuario.agregarSolicitud(new SolicitudConsumo(2, "Pendiente", new Date(), "Pelicula"));
        usuario.agregarSolicitud(new SolicitudConsumo(3, "Pendiente", new Date(), "Serie"));

        assertTrue(usuario.eliminarSolicitud("pelicula"));
        assertEquals(2, usuario.getCantidadSolicitudes());
        assertNull(usuario.buscarSolicitud("Pelicula"));
        assertNotNull(usuario.buscarSolicitud("Serie"));
        assertFalse(usuario.eliminarSolicitud("Pelicula"));
        assertFalse(usuario.eliminarSolicitud((String) null));
    }

    @Test
    void eliminarSolicitudPorObjeto() {
        SolicitudConsumo s1 = new SolicitudConsumo(1, "Pendiente", new Date(), "Libro");
        SolicitudConsumo s2 = new SolicitudConsumo(2, "Pendiente", new Date(), "Serie");
        usuario.agregarSolicitud(s1);
        usuario.agregarSolicitud(s2);

        assertTrue(usuario.eliminarSolicitud(s1));
        assertEquals(1, usuario.getCantidadSolicitudes());
        assertNull(usuario.buscarSolicitud(1));
        assertNotNull(usuario.buscarSolicitud(2));
        assertFalse(usuario.eliminarSolicitud((SolicitudConsumo) null));
    }

    @Test
    void eliminarSolicitudPorIdDesplazaElArreglo() {
        usuario.agregarSolicitud(new SolicitudConsumo(1, "Pendiente", new Date(), "Libro"));
        usuario.agregarSolicitud(new SolicitudConsumo(2, "Pendiente", new Date(), "Pelicula"));
        usuario.agregarSolicitud(new SolicitudConsumo(3, "Pendiente", new Date(), "Serie"));

        assertTrue(usuario.eliminarSolicitud(2));
        assertEquals(2, usuario.getCantidadSolicitudes());
        assertNull(usuario.buscarSolicitud(2));
        assertNotNull(usuario.buscarSolicitud(3));
        assertFalse(usuario.eliminarSolicitud(2));
    }

    // =========================================================================
    // 5. MÉTODOS DE NEGOCIO, SOBRECARGA Y @Override
    // =========================================================================

    @Test
    void obtenerRolYMostrarPerfil() {
        assertEquals("Usuario Registrado", usuario.obtenerRol());
        assertEquals("Id: 1, Nombre: Ana", usuario.mostrarPerfil());
        assertEquals("Id: 1, Nombre: Ana, Rol: Usuario Registrado", usuario.mostrarPerfil(true));
        assertEquals("Id: 1, Nombre: Ana", usuario.mostrarPerfil(false));
    }

    @Test
    void esMismoUsuario() {
        assertTrue(usuario.esMismoUsuario(new Usuario("Otro", 1)));
        assertFalse(usuario.esMismoUsuario(new Usuario("Ana", 2)));
        assertFalse(usuario.esMismoUsuario(null));
    }

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
