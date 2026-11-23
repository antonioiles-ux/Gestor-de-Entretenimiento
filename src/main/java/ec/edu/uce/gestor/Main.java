package ec.edu.uce.gestor;

import ec.edu.uce.gestor.dominio.Contenido;
import ec.edu.uce.gestor.dominio.GestorContenido;
import ec.edu.uce.gestor.dominio.Libro;
import ec.edu.uce.gestor.dominio.Serie;
import ec.edu.uce.gestor.dominio.Usuario;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        // 1. Herencia, Polimorfismo y Constructores (super / this)
        Contenido c1 = new Libro(1, "Cien anios de soledad", "Gabriel Garcia Marquez", "Novela", new Date(), 300, "Clasico");
        Contenido c2 = new Serie(2, "Breaking Bad", "Vince Gilligan", "Drama", new Date(), 5, 62);
        Contenido copiaC1 = new Libro(1, "Cien anios de soledad", "Gabriel Garcia Marquez", "Novela", new Date(), 300, "Clasico");
        Contenido vacio = new Libro();

        // 2. Sobreescritura de toString()
        System.out.println(c1.toString());
        System.out.println(vacio.toString());

        // 3. equals() (instanceof + casting) y hashCode()
        System.out.println("Misma referencia (==): " + (c1 == copiaC1));
        System.out.println("equals con copia: " + c1.equals(copiaC1));
        System.out.println("equals con distinto: " + c1.equals(c2));
        System.out.println("hashCode igual: " + (c1.hashCode() == copiaC1.hashCode()));

        // 4. instanceof y casting explícito
        if (c1 instanceof Libro lib) {
            System.out.println("Páginas: " + lib.getNumeroPaginas() + " | Tiempo: " + lib.calcularTiempoConsumo() + " min");
        }

        // 5. Sobrecarga de métodos con referencia de objeto
        System.out.println("Mismo género (c1 vs copiaC1): " + c1.tieneMismoGenero(copiaC1));
        System.out.println("Mismo género (c1 vs c2): " + c1.tieneMismoGenero(c2));

        Usuario admin = new GestorContenido("Administrador UCE", 99, 1);
        System.out.println(admin.toString() + " | Rol: " + admin.obtenerRol());
    }
}