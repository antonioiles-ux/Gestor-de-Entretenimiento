package ec.edu.uce.gestor.dominio;

public class Serie extends Contenido{
    private int numeroTemporadas;
    private int numeroEpisodios;

    public Serie() {
    }

    public Serie(int numeroTemporadas, int numeroEpisodios){
        this.numeroTemporadas = numeroTemporadas;
        this.numeroEpisodios = numeroEpisodios;
    }

    public int getNumeroTemporadas(){
        return numeroTemporadas;
    }
    public void setNumeroTemporadas(int numeroTemporadas){
        this.numeroTemporadas = numeroTemporadas;
    }

    public int getNumeroEpisodios(){
        return numeroEpisodios;
    }
    public void setNumeroEpisodios(int numeroEpisodios){
        this.numeroEpisodios = numeroEpisodios;
    }
}
