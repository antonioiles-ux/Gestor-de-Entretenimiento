package ec.edu.uce.gestor;

import java.util.Date;

import ec.edu.uce.gestor.dominio.Cuenta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CuentaTest {

    // objeto de prueba
    private Cuenta cuenta;
    private Date fechaPrueba; // Creado para poder comparar la fecha exacta en los tests

    // prepara el objeto de prueba antes de cada test
    @BeforeEach
    public void setUp() {
        fechaPrueba = new Date(); // Guardamos la fecha exacta de creación
        cuenta = new Cuenta(2, "juanpablo@gmail.com", "clave123", fechaPrueba);
    }

    // Constructores

    @Test
    void cuentaDatosValidos() {
        Date fechaAux = new Date();
        Cuenta c = new Cuenta(1, "ana33@gmail.com", "ana12345", fechaAux);

        assertEquals(1, c.getIdCuenta());
        assertEquals("ana33@gmail.com", c.getCorreoElectronico());
        assertEquals("ana12345", c.getContrasena());
        assertEquals(fechaAux, c.getFechaRegistro()); // Comparamos Date con Date
    }

    @Test
    void cuentaDefecto() {
        Cuenta c = new Cuenta();

        assertEquals(0, c.getIdCuenta());
        assertEquals("sin correo", c.getCorreoElectronico());
        assertEquals("sin contrasena", c.getContrasena());
        assertNull(c.getFechaRegistro()); // En un constructor vacío, los objetos como Date suelen iniciar en null
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
        // Usamos la fechaPrueba que guardamos en el setUp
        assertEquals(fechaPrueba, cuenta.getFechaRegistro());
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
        Date nuevaFecha = new Date();
        assertTrue(cuenta.setFechaRegistro(nuevaFecha));
        assertEquals(nuevaFecha, cuenta.getFechaRegistro());
    }

    @Test
    public void fechaRegistroInvalida() {
        Date fechaAntes = cuenta.getFechaRegistro(); // Ahora sí guardamos el Date en una variable Date

        // Se elimina la prueba con "" porque Java no permite compilar un String en un Date
        assertFalse(cuenta.setFechaRegistro(null));
        assertEquals(fechaAntes, cuenta.getFechaRegistro());
    }
}