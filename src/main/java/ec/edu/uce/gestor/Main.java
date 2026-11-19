package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Contenido;
import ec.edu.uce.gestor.dominio.Libro;
import ec.edu.uce.gestor.dominio.Serie;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        Contenido c1 = new Libro(1, "Cien anios de soledad", "Gabriel Garcia Marquez", "Novela", new Date(), 300, "Clasico");
        Contenido c2 = new Serie(2, "Breaking Bad", "Vince Gilligan", "Drama", new Date(), 5, 62);
        Contenido copiaC1 = new Libro(1, "Cien anios de soledad", "Gabriel Garcia Marquez", "Novela", new Date(), 300, "Clasico");

        System.out.println((c1 == copiaC1) + " " + c1.equals(copiaC1) + " " + c1.equals(c2)
                + " " + (c1.hashCode() == copiaC1.hashCode()));

        if (c1 instanceof Libro lib) {
            System.out.println("Páginas: " + lib.getNumeroPaginas() + " | Tiempo: " + lib.calcularTiempoConsumo() + " min");
        }

    }
}
