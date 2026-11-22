package ec.edu.uce.gestor.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validaciones del sistema usando expresiones regulares (Pattern / Matcher).
 * Los Pattern se compilan una sola vez como constantes estáticas.
 */
public class Validaciones {

    // ---------- Patrones precompilados ----------
    private static final Pattern NUMEROS = Pattern.compile("^\\d{1,9}$");
    private static final Pattern LETRAS = Pattern.compile("^[A-Za-zñÑáéíóúÁÉÍÓÚüÜ ]+$");
    private static final Pattern CORREO = Pattern.compile("^[A-Za-z0-9._-]+@[A-Za-z0-9-]{3,}(\\.[A-Za-z]{2,})+$");
    private static final Pattern CONTRASENA = Pattern.compile("^\\S{6,}$");
    private static final Pattern FECHA = Pattern.compile("^(0[1-9]|[12]\\d|3[01])/(0[1-9]|1[0-2])/\\d{4}$");
    private static final Pattern CALIFICACION = Pattern.compile("^[1-5]$");
    private static final Pattern TEXTO_CON_LETRAS = Pattern.compile("^(?=.*[A-Za-zñÑáéíóúÁÉÍÓÚüÜ]).+$");
    private static final Pattern CLASIFICACION_EDAD = Pattern.compile("^(?=.*[A-Za-zñÑáéíóúÁÉÍÓÚ+]).+$");

    private static boolean coincide(Pattern pattern, String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return false;
        }
        Matcher matcher = pattern.matcher(texto.trim());
        return matcher.matches();
    }

    // ---------- Validaciones ----------
    public static boolean validarNumeros(String texto) {
        return coincide(NUMEROS, texto);
    }

    public static boolean validarLetras(String texto) {
        return coincide(LETRAS, texto);
    }

    public static boolean validarTextoConLetras(String texto) {
        return coincide(TEXTO_CON_LETRAS, texto);
    }

    public static boolean validarClasificacionEdad(String texto) {
        return coincide(CLASIFICACION_EDAD, texto);
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
