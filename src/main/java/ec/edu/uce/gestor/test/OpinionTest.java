package ec.edu.uce.gestor.test;

import ec.edu.uce.gestor.dominio.Opinion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OpinionTest {
    //Objeto de prueba
    private Opinion opinion;

    //Preparar el objeto de prueba
    @BeforeEach
    public void setUp(){
        opinion = new Opinion(1, "Contenido actualizado y mejorado.", "08/11/2026", "09/11/2026");

    }

    @Test
    public void opinionDefecto() {
        Opinion opinion = new Opinion();

        assertEquals(0, opinion.getIdOpinion());
        assertEquals("sin comentario", opinion.getComentario());
        assertEquals("sin fecha de registro", opinion.getFechaRegistro());
        assertEquals("sin fecha de edicion", opinion.getFechaEdicion());
    }

    @Test
    public void opinionValida(){

        assertEquals(1, opinion.getIdOpinion());
        assertEquals("Contenido actualizado y mejorado.", opinion.getComentario());
        assertEquals("08/11/2026", opinion.getFechaRegistro());
        assertEquals("09/11/2026",opinion.getFechaEdicion());
    }

    @Test
    public void testCrearOpinion(){
        assertNotNull(opinion);
    }

    @Test
    public void testGetIdOp(){
        assertEquals(1, opinion.getIdOpinion());
    }

    @Test
    public void testGetComentario(){
        assertEquals("Contenido actualizado y mejorado.", opinion.getComentario());
    }

    @Test
    public void testGetFechaRegistro(){
        assertEquals("08/11/2026", opinion.getFechaRegistro());
    }

    @Test
    public void testGetFechaEdicion(){
        assertEquals("09/11/2026",opinion.getFechaEdicion());
    }

    @Test
    public void testSetIdOp(){
        assertTrue(opinion.setIdOpinion(5));
        assertEquals(5,opinion.getIdOpinion());
    }

    @Test
    public void testSetComentario(){
        assertTrue(opinion.setComentario("Excelente libro, muy recomendado."));
        assertEquals("Excelente libro, muy recomendado.",opinion.getComentario());
    }

    @Test
    public void comentarioInvalido(){
        String comentarioAntes = opinion.getComentario();

        assertFalse(opinion.setComentario(""));
        assertEquals(comentarioAntes,opinion.getComentario());
    }

    @Test
    public void fechaRegistroNoValida(){
        String fechaRegistroAntes = opinion.getFechaRegistro();

        assertFalse(opinion.setFechaRegistro(""));
        assertEquals(fechaRegistroAntes, opinion.getFechaRegistro());
    }

    @Test
    public void fechaEdicionInvalida(){
        String fechaEdicionAntes = opinion.getFechaEdicion();

        assertFalse(opinion.setFechaEdicion(""));
        assertEquals(fechaEdicionAntes, opinion.getFechaEdicion());
    }

    @Test
    public void idOpinionNoValido(){
        int idOpinionAntes = opinion.getIdOpinion();

        assertFalse(opinion.setIdOpinion(-5));
        assertEquals(idOpinionAntes, opinion.getIdOpinion());
    }

}
