package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Contenido;
import ec.edu.uce.gestor.dominio.Serie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class SerieTest {

    private Serie serie;
    private Date fechaContenido;

    @BeforeEach
    public void setUp() {
        fechaContenido = new Date();
        // Usamos el constructor completo
        serie = new Serie(1, "Breaking Bad", "Vince Gilligan", "Drama", fechaContenido, 5, 62);
    }

    @Test
    void serieDatosValidos() {

        // Verificamos datos del padre
        assertEquals(1, serie.getIdContenido());
        assertEquals("Breaking Bad", serie.getTitulo());
        assertEquals("Vince Gilligan",serie.getAutor());
        assertEquals("Drama",serie.getGenero());
        assertEquals(fechaContenido, serie.getFechaPublicacion());

        // Verificamos datos propios del hijo
        assertEquals(5, serie.getNumeroTemporadas());
        assertEquals(62, serie.getNumeroEpisodios());
    }

    @Test
    void serieDefecto() {
        Serie serie = new Serie();

        // Valores por defecto del padre
        assertEquals(0, serie.getIdContenido());
        assertEquals("sin titulo", serie.getTitulo());
        assertEquals("sin autor",serie.getAutor());
        assertEquals("sin genero",serie.getGenero());
        assertEquals(null,serie.getFechaPublicacion());

        // Valores por defecto del hijo
        assertEquals(0, serie.getNumeroTemporadas());
        assertEquals(0, serie.getNumeroEpisodios());
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


    // --- Nuevos tests: Polimorfismo, instanceof, Sobrecarga, equals y hashCode ---

    @Test
    public void testPolimorfismoYInstanceof() {
        Contenido contPolimorfico = serie;

        assertEquals(2790, contPolimorfico.calcularTiempoConsumo());
        assertTrue(contPolimorfico instanceof Serie);

        if (contPolimorfico instanceof Serie s) {
            assertEquals(5, s.getNumeroTemporadas());
        }
    }

    @Test
    public void testSobrecargaCalcularTiempoConsumo() {
        assertEquals(2790, serie.calcularTiempoConsumo());
        assertEquals(1860, serie.calcularTiempoConsumo(30));
        assertEquals(0, serie.calcularTiempoConsumo(0));
        assertEquals(0, serie.calcularTiempoConsumo(-5));
    }

    @Test
    public void testEqualsYHashCode() {
        Serie mismaSerie = new Serie(1, "Otra Serie", "Otro Autor", "Comedia", new Date(), 5, 62);
        Serie distintaSerie = new Serie(3, "Breaking Bad", "Vince Gilligan", "Drama", fechaContenido, 2, 20);

        assertEquals(serie, serie);
        assertEquals(serie, mismaSerie);
        assertEquals(serie.hashCode(), mismaSerie.hashCode());
        assertNotEquals(serie, distintaSerie);
        assertFalse(serie.equals(null));
    }
}
