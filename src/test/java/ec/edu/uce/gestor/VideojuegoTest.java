package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Videojuego;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class VideojuegoTest {
    private Videojuego videojuego;
    private Date fechaPublicacion;

    private Date crearFecha(int dia, int mes, int anio) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(anio, mes - 1, dia);
        return cal.getTime();
    }

    @BeforeEach
    public void setUp() {
        fechaPublicacion = crearFecha(20, 4, 2018);
        videojuego = new Videojuego(1, "God of War", "Santa Monica", "Accion", fechaPublicacion, "PSP y PS", "+18");
    }

    @Test
    public void videojuegoDefecto() {
        Videojuego vacio = new Videojuego();

        assertEquals(0, vacio.getIdContenido());
        assertEquals("sin titulo", vacio.getTitulo());
        assertEquals("sin autor", vacio.getAutor());
        assertEquals("sin genero", vacio.getGenero());
        assertNull(vacio.getFechaPublicacion());
        assertEquals("Sin plataforma", vacio.getPlataforma());
        assertEquals("Sin Clasificacion", vacio.getClasificacionEdad());
    }

    @Test
    public void videojuegoValido() {
        assertEquals(1, videojuego.getIdContenido());
        assertEquals("God of War", videojuego.getTitulo());
        assertEquals("Santa Monica", videojuego.getAutor());
        assertEquals("Accion", videojuego.getGenero());
        assertEquals(fechaPublicacion, videojuego.getFechaPublicacion());
        assertEquals("PSP y PS", videojuego.getPlataforma());
        assertEquals("+18", videojuego.getClasificacionEdad());
    }

    @Test
    public void testCrearVideojuego() {
        assertNotNull(videojuego);
    }

    // Getters
    @Test
    public void testGetPlataforma() {
        assertEquals("PSP y PS", videojuego.getPlataforma());
    }

    @Test
    public void testGetClasificacionEdad() {
        assertEquals("+18", videojuego.getClasificacionEdad());
    }

    // Setters válidos
    @Test
    public void testSetPlataforma() {
        assertTrue(videojuego.setPlataforma("PC y PS5"));
        assertEquals("PC y PS5", videojuego.getPlataforma());
    }

    @Test
    public void testSetClasificacionEdad() {
        assertTrue(videojuego.setClasificacionEdad("T"));
        assertEquals("T", videojuego.getClasificacionEdad());
    }

    // Setters inválidos (Validaciones)
    @Test
    public void plataformaInvalida() {
        String plataformaAntes = videojuego.getPlataforma();

        assertFalse(videojuego.setPlataforma(""));
        assertEquals(plataformaAntes, videojuego.getPlataforma());
    }

    @Test
    public void plataformaNulaInvalida() {
        String plataformaAntes = videojuego.getPlataforma();

        assertFalse(videojuego.setPlataforma(null));
        assertEquals(plataformaAntes, videojuego.getPlataforma());
    }

    @Test
    public void clasificacionEdadInvalida() {
        String clasificacionAntes = videojuego.getClasificacionEdad();

        assertFalse(videojuego.setClasificacionEdad(""));
        assertEquals(clasificacionAntes, videojuego.getClasificacionEdad());
    }

    @Test
    public void clasificacionEdadNulaInvalida() {
        String clasificacionAntes = videojuego.getClasificacionEdad();

        assertFalse(videojuego.setClasificacionEdad(null));
        assertEquals(clasificacionAntes, videojuego.getClasificacionEdad());
    }

    // toString
    @Test
    public void testToString() {
        String esperado = "ID CONTENIDO:1  Titulo:God of War  Autor:Santa Monica  Genero:Accion  Fecha Publicacion:"
                + fechaPublicacion + "Plataforma: PSP y PS, clasificacionEdad: +18, ";

        assertEquals(esperado, videojuego.toString());
    }

    @Test
    public void testToStringPorDefecto() {
        Videojuego vacio = new Videojuego();
        String esperado = "ID CONTENIDO:0  Titulo:sin titulo  Autor:sin autor  Genero:sin genero  Fecha Publicacion:null"
                + "Plataforma: Sin plataforma, clasificacionEdad: Sin Clasificacion, ";

        assertEquals(esperado, vacio.toString());
    }
}