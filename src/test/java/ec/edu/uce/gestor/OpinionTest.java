package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Opinion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OpinionTest {
    private Opinion opinion;
    private String fechaRegistro;
    private String fechaEdicion;

    @BeforeEach
    public void setUp() {
        fechaRegistro = "08/11/2026";
        fechaEdicion = "09/11/2026";
        opinion = new Opinion(1, "Contenido actualizado y mejorado.", fechaRegistro, fechaEdicion);
    }

    @Test
    public void opinionDefecto() {
        Opinion vacia = new Opinion();

        assertEquals(0, vacia.getIdOpinion());
        assertEquals("sin comentario", vacia.getComentario());
        assertEquals("sin fecha de registro", vacia.getFechaRegistroOp());
        assertEquals("sin fecha de edicion", vacia.getFechaEdicion());
    }

    @Test
    public void opinionValida() {
        assertEquals(1, opinion.getIdOpinion());
        assertEquals("Contenido actualizado y mejorado.", opinion.getComentario());
        assertEquals(fechaRegistro, opinion.getFechaRegistroOp());
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
        assertEquals(fechaRegistro, opinion.getFechaRegistroOp());
    }

    @Test
    public void testGetFechaEdicion() {
        assertEquals(fechaEdicion, opinion.getFechaEdicion());
    }

    // Setters válidos
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
    public void testSetFechaRegistroValida() {
        assertTrue(opinion.setFechaRegistro("13/11/2026"));
        assertEquals("13/11/2026", opinion.getFechaRegistroOp());
    }

    @Test
    public void testSetFechaEdicionValida() {
        assertTrue(opinion.setFechaEdicion("14/11/2026"));
        assertEquals("14/11/2026", opinion.getFechaEdicion());
    }

    // Setters inválidos (Validaciones)
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
    public void fechaRegistroInvalida() {
        String fechaAntes = opinion.getFechaRegistroOp();

        assertFalse(opinion.setFechaRegistro("2026-11-13"));
        assertEquals(fechaAntes, opinion.getFechaRegistroOp());
    }

    @Test
    public void fechaEdicionInvalida() {
        String fechaAntes = opinion.getFechaEdicion();

        assertFalse(opinion.setFechaEdicion("fecha-mala"));
        assertEquals(fechaAntes, opinion.getFechaEdicion());
    }

    @Test
    public void testToString() {
        String esperado = "ID OPINION:1  Comentario:Contenido actualizado y mejorado."
                + "  Fecha de Registro:08/11/2026  Fecha de Edicion:09/11/2026";

        assertEquals(esperado, opinion.toString());
    }

    @Test
    public void testToStringPorDefecto() {
        Opinion vacia = new Opinion();
        String esperado = "ID OPINION:0  Comentario:sin comentario"
                + "  Fecha de Registro:sin fecha de registro  Fecha de Edicion:sin fecha de edicion";

        assertEquals(esperado, vacia.toString());
    }
}