package ec.edu.uce.gestor.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validaciones del sistema usando expresiones regulares (Pattern / Matcher).
 * Los Pattern se compilan una sola vez como constantes estáticas.
 */
public class Validaciones {

    // ---------- Patrones precompilados ----------
    // Entero positivo de hasta 9 dígitos (evita NumberFormatException al usar Integer.parseInt)
    private static final Pattern NUMEROS = Pattern.compile("^\\d{1,9}$");
    private static final Pattern LETRAS = Pattern.compile("^[A-Za-zñÑáéíóúÁÉÍÓÚ ]+$");
    private static final Pattern CORREO = Pattern.compile("^[A-Za-z0-9._-]+@[A-Za-z0-9-]{3,}(\\.[A-Za-z]{2,})+$");
    private static final Pattern CONTRASENA = Pattern.compile("^\\S{6,}$");
    private static final Pattern FECHA = Pattern.compile("^(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/\\d{4}$");
    private static final Pattern CALIFICACION = Pattern.compile("^[1-5]$");

    private static boolean coincide(Pattern pattern, String texto) {
        if (texto == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(texto);
        return matcher.matches();
    }

    // ---------- Validaciones ----------
    public static boolean validarNumeros(String texto) {
        return coincide(NUMEROS, texto);
    }

    public static boolean validarLetras(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return false;
        }
        return coincide(LETRAS, texto);
    }

    public static boolean validarCorreo(String texto) {
        return coincide(CORREO, texto);
    }

    public static boolean validarContrasena(String texto) {
        return coincide(CONTRASENA, texto);
    }

    public static boolean validarFecha(String texto) {
        return coincide(FECHA, texto);
    }

    public static boolean validarCalificacion(String texto) {
        return coincide(CALIFICACION, texto);
    }
}
