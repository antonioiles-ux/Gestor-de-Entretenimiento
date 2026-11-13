package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Serie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SerieTest {

    private Serie serie;
    private Date fechaPrueba;

    @BeforeEach
    public void setUp() {
        fechaPrueba = new Date();
        // Usamos el constructor completo
        serie = new Serie(1, "Breaking Bad", "Vince Gilligan", "Drama", fechaPrueba, 5, 62);
    }

    @Test
    void serieDatosValidos() {
        Date nuevaFecha = new Date();
        Serie s = new Serie(2, "Stranger Things", "Duffer Brothers", "Ciencia Ficción", nuevaFecha, 4, 34);

        // Verificamos datos del padre
        assertEquals(2, s.getIdContenido());
        assertEquals("Stranger Things", s.getTitulo());

        // Verificamos datos propios del hijo
        assertEquals(4, s.getNumeroTemporadas());
        assertEquals(34, s.getNumeroEpisodios());
    }

    @Test
    void serieDefecto() {
        Serie s = new Serie();

        // Valores por defecto del padre
        assertEquals(0, s.getIdContenido());
        assertEquals("sin titulo", s.getTitulo());

        // Valores por defecto del hijo
        assertEquals(0, s.getNumeroTemporadas());
        assertEquals(0, s.getNumeroEpisodios());
    }

    @Test
    public void testCrearSerie() {
        assertNotNull(serie);
    }

    @Test
    public void testGetNumeroTemporadas() {
        assertEquals(5, serie.getNumeroTemporadas());
    }

    @Test
    public void testGetNumeroEpisodios() {
        assertEquals(62, serie.getNumeroEpisodios());
    }

    @Test
    public void testSetNumeroTemporadas() {
        assertTrue(serie.setNumeroTemporadas(6));
        assertEquals(6, serie.getNumeroTemporadas());

        // Comprobación de rechazo (0 o negativo)
        assertFalse(serie.setNumeroTemporadas(0));
        assertFalse(serie.setNumeroTemporadas(-2));
    }

    @Test
    public void testSetNumeroEpisodios() {
        assertTrue(serie.setNumeroEpisodios(70));
        assertEquals(70, serie.getNumeroEpisodios());

        // Comprobación de rechazo (0 o negativo)
        assertFalse(serie.setNumeroEpisodios(0));
        assertFalse(serie.setNumeroEpisodios(-10));
    }
}
