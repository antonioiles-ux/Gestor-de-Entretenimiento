package ec.edu.uce.gestor.test;

import ec.edu.uce.gestor.dominio.Opinion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OpinionTest {
    //Objeto de prueba
    private Opinion opinion;

    //Preparar el objeto de prueba
    @BeforeEach
    public void setUp(){
        opinion = new Opinion(2, "Contenido actualizado y mejorado.", "08/11/2026", "09/11/2026");

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
        Opinion opinion = new Opinion(1, "Excelente libro, muy recomendado", "07/11/2026", "07/11/2026");

        assertEquals(1, opinion.getIdOpinion());
        assertEquals("Excelente libro, muy recomendado", opinion.getComentario());
        assertEquals("07/11/2026", opinion.getFechaRegistro());
        assertEquals("07/11/2026",opinion.getFechaEdicion());
    }

    @Test
    public void testCrearOpinion(){
        assertNotNull(opinion);

    }

}
