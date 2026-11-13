package ec.edu.uce.gestor.dominio.test;

import ec.edu.uce.gestor.dominio.dominio.Cuenta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CuentaTest{
    // objeto de prueba
    private Cuenta cuenta;
    private Date fechaR;

    // prepara el object de prueba antes de cada test
    @BeforeEach
    public void setUp() {
        fechaR = new Date();
        cuenta = new Cuenta(1, "juanpablo@gmail.com", "clave123", fechaR);
    }

    // Constructores

    @Test
    void cuentaDatosValidos() {

        assertEquals(1, cuenta.getIdCuenta());
        assertEquals("juanpablo@gmail.com", cuenta.getCorreoElectronico());
        assertEquals("clave123", cuenta.getContrasena());
        assertEquals(fechaR, cuenta.getFechaRegistro());
    }

    @Test
    void cuentaDefecto() {
        Cuenta c = new Cuenta();

        assertEquals(0, c.getIdCuenta());
        assertEquals("sin correo", c.getCorreoElectronico());
        assertEquals("sin contrasena", c.getContrasena());
        assertEquals(null, c.getFechaRegistro());
    }

    @Test
    public void testCrearCuenta() {
        assertNotNull(cuenta);
    }

    // Getters

    @Test
    public void testGetIdCuenta() {
        assertEquals(1, cuenta.getIdCuenta());
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
        assertEquals(fechaR, cuenta.getFechaRegistro());
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
    public void fechaRegistroInvalida() {
        Date fechaAntes = cuenta.getFechaRegistro();

        assertFalse(cuenta.setFechaRegistro(null));
        assertEquals(fechaAntes, cuenta.getFechaRegistro());
    }
}