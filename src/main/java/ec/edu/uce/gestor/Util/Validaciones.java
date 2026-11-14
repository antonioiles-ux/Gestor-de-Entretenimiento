package ec.edu.uce.gestor.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validaciones {

    public static boolean validarNumeros(String texto) {
        Pattern patron = Pattern.compile("[0-9]+");
        Matcher resultado = patron.matcher(texto);
        return resultado.matches();
    }

    public static boolean validarLetras(String texto) {
        Pattern patron = Pattern.compile("[A-Za-zñÑáéíóúÁÉÍÓÚ ]+");
        Matcher resultado = patron.matcher(texto);
        return resultado.matches();
    }

    public static boolean validarCorreo(String texto) {
        Pattern patron = Pattern.compile("[A-Za-z0-9._-]+@[A-Za-z0-9-]+\\.[A-Za-z]{2,}");
        Matcher resultado = patron.matcher(texto);
        return resultado.matches();
    }

    public static boolean validarContrasena(String texto) {
        Pattern patron = Pattern.compile("\\S{6,}");
        Matcher resultado = patron.matcher(texto);
        return resultado.matches();
    }

    public static boolean validarFecha(String texto) {
        Pattern patron = Pattern.compile("[0-9]{2}/[0-9]{2}/[0-9]{4}");
        Matcher resultado = patron.matcher(texto);
        return resultado.matches();
    }

    public static boolean validarCalificacion(String texto) {
        Pattern patron = Pattern.compile("[1-5]");
        Matcher resultado = patron.matcher(texto);
        return resultado.matches();
    }
}
