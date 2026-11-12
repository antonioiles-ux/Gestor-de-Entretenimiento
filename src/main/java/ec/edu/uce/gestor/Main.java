package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Libro;
import ec.edu.uce.gestor.dominio.Serie;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Libro l = new Libro(1, "sin descripcion");

        // Se reemplaza el 2016 por new Date() para pasar la fecha actual temporalmente
        Serie s = new Serie(1, "Breaking Bad", "Vince Gilligan", "Serie Carteles", new Date(), 5, 62);

        // Al ejecutar estas líneas, Java llama automáticamente al toString() de cada objeto
        System.out.println(l);
        System.out.println(s);
    }
}
