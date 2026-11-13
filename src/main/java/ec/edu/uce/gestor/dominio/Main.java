package ec.edu.uce.gestor.dominio;

import ec.edu.uce.gestor.dominio.dominio.Libro;
import ec.edu.uce.gestor.dominio.dominio.Serie;
import ec.edu.uce.gestor.dominio.dominio.Pelicula;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Libro l = new Libro(1, "sin descripcion");
        Serie s = new Serie(1, "Breaking Bad", "Vince Gilligan", "Serie Carteles", new Date(), 5, 62);
        Pelicula p = new Pelicula(120, "PG-13");


        System.out.println(l);
        System.out.println(s);
        System.out.println(p);
    }
}
