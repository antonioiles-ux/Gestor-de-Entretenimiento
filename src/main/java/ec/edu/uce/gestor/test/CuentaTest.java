package ec.edu.uce.gestor.test;

import ec.edu.uce.gestor.dominio.Cuenta;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class CuentaTest {

    // objeto de prueba
    private Cuenta cuenta;

    // prepara el object de prueba antes de cada test
    @BeforeEach
    public void setUp() {
        cuenta = new Cuenta(2, "juanpablo@gmail.com", "clave123", "2025-01-15");
        Date fechaRegistro = new Date();
    }

    // Constructores

    @Test
    void cuentaDatosValidos() {
        Cuenta c = new Cuenta(1, "ana33@gmail.com", "ana12345", "2025-02-20");

        assertEquals(1, c.getIdCuenta());
        assertEquals("ana33@gmail.com", c.getCorreoElectronico());
        assertEquals("ana12345", c.getContrasena());
        assertEquals("2025-02-20", c.getFechaRegistro());
    }

    @Test
    void cuentaDefecto() {
        Cuenta c = new Cuenta();

        assertEquals(0, c.getIdCuenta());
        assertEquals("sin correo", c.getCorreoElectronico());
        assertEquals("sin contrasena", c.getContrasena());
        assertEquals("sin fecha", c.getFechaRegistro());
    }

    @Test
    public void testCrearCuenta() {
        assertNotNull(cuenta);
    }

    // Getters

    @Test
    public void testGetIdCuenta() {
        assertEquals(2, cuenta.getIdCuenta());
    }

    @Test
    public void testGetCorreoElectronico() {
        assertEquals("juanpablo@gmail.com", cuenta.getCorreoElectronico());
    }

    @Test
    public void testGetContrasena() {
        assertEquals("clave123", cuenta.getContrasena());
    }

    @Test
    public void testGetFechaRegistro() {
        assertEquals("2025-01-15", cuenta.getFechaRegistro());
    }

    // Id cuenta

    @Test
    public void idCuentaValido() {
        assertTrue(cuenta.setIdCuenta(10));
        assertEquals(10, cuenta.getIdCuenta());
    }

    @Test
    public void idCuentaInvalido() {
        int idAntes = cuenta.getIdCuenta();

        assertFalse(cuenta.setIdCuenta(0));
        assertFalse(cuenta.setIdCuenta(-5));
        assertEquals(idAntes, cuenta.getIdCuenta());
    }

    // Correo Electrónico

    @Test
    public void correoValido() {
        assertTrue(cuenta.setCorreoElectronico("carlosp@gmail.com"));
        assertEquals("carlosp@gmail.com", cuenta.getCorreoElectronico());
    }

    @Test
    public void correoInvalido() {
        String correoAntes = cuenta.getCorreoElectronico();

        assertFalse(cuenta.setCorreoElectronico(""));
        assertFalse(cuenta.setCorreoElectronico(null));
        assertFalse(cuenta.setCorreoElectronico("sinarroba.com"));
        assertEquals(correoAntes, cuenta.getCorreoElectronico());
    }

    // Contraseña

    @Test
    public void contrasenaValida() {
        assertTrue(cuenta.setContrasena("nueva456"));
        assertEquals("nueva456", cuenta.getContrasena());
    }

    @Test
    public void contrasenaInvalida() {
        String contrasenaAntes = cuenta.getContrasena();

        assertFalse(cuenta.setContrasena(""));
        assertFalse(cuenta.setContrasena(null));
        assertFalse(cuenta.setContrasena("123"));
        assertEquals(contrasenaAntes, cuenta.getContrasena());
    }

    // Fecha registro

    @Test
    public void fechaRegistroValida() {
        assertTrue(cuenta.setFechaRegistro("2025-03-10"));
        assertEquals("2025-03-10", cuenta.getFechaRegistro());
    }

    @Test
    public void fechaRegistroInvalida() {
        String fechaAntes = cuenta.getFechaRegistro();

        assertFalse(cuenta.setFechaRegistro(""));
        assertFalse(cuenta.setFechaRegistro(null));
        assertEquals(fechaAntes, cuenta.getFechaRegistro());
    }
}