package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Opinion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class OpinionTest {
    private Opinion opinion;
    private Date fechaRegistro;
    private Date fechaEdicion;

    private Date crearFecha(int dia, int mes, int anio) {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(anio, mes - 1, dia);
        return cal.getTime();
    }

    @BeforeEach
    public void setUp() {
        fechaRegistro = crearFecha(8, 11, 2026);
        fechaEdicion = crearFecha(9, 11, 2026);
        opinion = new Opinion(1, "Contenido actualizado y mejorado.", fechaRegistro, fechaEdicion);
    }

    @Test
    public void opinionDefecto() {
        Opinion vacia = new Opinion();

        assertEquals(0, vacia.getIdOpinion());
        assertEquals("sin comentario", vacia.getComentario());
        assertNull(vacia.getFechaRegistro());
        assertNull(vacia.getFechaEdicion());
    }

    @Test
    public void opinionValida() {
        assertEquals(1, opinion.getIdOpinion());
        assertEquals("Contenido actualizado y mejorado.", opinion.getComentario());
        assertEquals(fechaRegistro, opinion.getFechaRegistro());
        assertEquals(fechaEdicion, opinion.getFechaEdicion());
    }

    @Test
    public void testCrearOpinion() {
        assertNotNull(opinion);
    }

    // Getters
    @Test
    public void testGetIdOp() {
        assertEquals(1, opinion.getIdOpinion());
    }

    @Test
    public void testGetComentario() {
        assertEquals("Contenido actualizado y mejorado.", opinion.getComentario());
    }

    @Test
    public void testGetFechaRegistro() {
        assertEquals(fechaRegistro, opinion.getFechaRegistro());
    }

    @Test
    public void testGetFechaEdicion() {
        assertEquals(fechaEdicion, opinion.getFechaEdicion());
    }

    // Setters
    @Test
    public void testSetIdOp() {
        assertTrue(opinion.setIdOpinion(5));
        assertEquals(5, opinion.getIdOpinion());
    }

    @Test
    public void testSetComentario() {
        assertTrue(opinion.setComentario("Excelente libro, muy recomendado."));
        assertEquals("Excelente libro, muy recomendado.", opinion.getComentario());
    }

    @Test
    public void comentarioInvalido() {
        String comentarioAntes = opinion.getComentario();

        assertFalse(opinion.setComentario(""));
        assertEquals(comentarioAntes, opinion.getComentario());
    }

    @Test
    public void comentarioNuloInvalido() {
        String comentarioAntes = opinion.getComentario();

        assertFalse(opinion.setComentario(null));
        assertEquals(comentarioAntes, opinion.getComentario());
    }

    @Test
    public void idOpinionNoValido() {
        int idOpinionAntes = opinion.getIdOpinion();

        assertFalse(opinion.setIdOpinion(-5));
        assertEquals(idOpinionAntes, opinion.getIdOpinion());
    }

    @Test
    public void fechaRegistroAutomatica() {
        Date antes = new Date();
        Opinion nueva = new Opinion(2, "Muy buena");
        Date despues = new Date();

        assertNotNull(nueva.getFechaRegistro());
        assertFalse(nueva.getFechaRegistro().before(antes));
        assertFalse(nueva.getFechaRegistro().after(despues));
    }

    @Test
    public void fechaEdicionVaciaAlCrear() {
        Opinion nueva = new Opinion(2, "Muy buena");

        assertNull(nueva.getFechaEdicion());
    }

    @Test
    public void fechaEdicionSeActualizaAlEditar() {
        Opinion nueva = new Opinion(2, "Muy buena");

        Date antes = new Date();
        assertTrue(nueva.setComentario("Cambié de opinión"));
        Date despues = new Date();

        assertNotNull(nueva.getFechaEdicion());
        assertFalse(nueva.getFechaEdicion().before(antes));
        assertFalse(nueva.getFechaEdicion().after(despues));
    }

    @Test
    public void fechaEdicionNoCambiaConComentarioInvalido() {
        Opinion nueva = new Opinion(2, "Muy buena");

        assertFalse(nueva.setComentario(""));
        assertNull(nueva.getFechaEdicion());
    }


    @Test
    public void testToString() {
        String esperado = "Id opinion:1  Comentario:Contenido actualizado y mejorado."
                + "  Fecha de Registro:08/11/2026  Fecha de Edicion:09/11/2026";

        assertEquals(esperado, opinion.toString());
    }

    @Test
    public void testToStringPorDefecto() {
        Opinion vacia = new Opinion();
        String esperado = "Id opinion:0  Comentario:sin comentario"
                + "  Fecha de Registro:sin fecha de registro  Fecha de Edicion:sin fecha de edicion";

        assertEquals(esperado, vacia.toString());
    }
}
