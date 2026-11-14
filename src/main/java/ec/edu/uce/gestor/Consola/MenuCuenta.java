package ec.edu.uce.gestor.Consola;

import ec.edu.uce.gestor.Util.Validaciones;
import ec.edu.uce.gestor.dominio.Cuenta;
import ec.edu.uce.gestor.dominio.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuCuenta {

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {

            System.out.println("\n===(H1).GESTIONAR CUENTA ===");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrar Usuario");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            String entrada = sc.nextLine();
            if (!Validaciones.validarNumeros(entrada)) {
                System.out.println("Opción inválida. Ingrese solo números.");
                continue;
            }
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1:
                    // Se crea un objeto temporal solo para validar el ingreso
                    Cuenta cuentaLogin = new Cuenta();
                    boolean validoLogin;

                    do {
                        System.out.print("Correo electrónico: ");
                        validoLogin = cuentaLogin.setCorreoElectronico(sc.nextLine());
                        if (!validoLogin) System.out.println("Correo inválido (debe tener @ y dominio válido).");
                    } while (!validoLogin);

                    do {
                        System.out.print("Contraseña (mínimo 6 caracteres sin espacios): ");
                        validoLogin = cuentaLogin.setContrasena(sc.nextLine());
                        if (!validoLogin) System.out.println("Contraseña inválida.");
                    } while (!validoLogin);

                    System.out.println("Datos validados en memoria -> Correo: " + cuentaLogin.getCorreoElectronico());
                    break;

                case 2:
                    // Objetos temporales en RAM que reciben los datos validados
                    Usuario usuario = new Usuario();
                    Cuenta cuenta = new Cuenta();
                    boolean valido;

                    do {
                        System.out.print("ID Usuario (números > 0): ");
                        String idTxt = sc.nextLine();
                        valido = Validaciones.validarNumeros(idTxt) && usuario.setIdUsuario(Integer.parseInt(idTxt));
                        if (!valido) System.out.println("ID inválido.");
                    } while (!valido);

                    do {
                        System.out.print("Nombre completo (solo letras): ");
                        valido = usuario.setNombreCompleto(sc.nextLine());
                        if (!valido) System.out.println("Nombre inválido.");
                    } while (!valido);

                    do {
                        System.out.print("ID Cuenta (números > 0): ");
                        String idCuentaTxt = sc.nextLine();
                        valido = Validaciones.validarNumeros(idCuentaTxt) && cuenta.setIdCuenta(Integer.parseInt(idCuentaTxt));
                        if (!valido) System.out.println("ID de cuenta inválido.");
                    } while (!valido);

                    do {
                        System.out.print("Correo electrónico: ");
                        valido = cuenta.setCorreoElectronico(sc.nextLine());
                        if (!valido) System.out.println("Correo inválido.");
                    } while (!valido);

                    do {
                        System.out.print("Contraseña (mínimo 6 caracteres sin espacios): ");
                        valido = cuenta.setContrasena(sc.nextLine());
                        if (!valido) System.out.println("Contraseña inválida.");
                    } while (!valido);

                    do {
                        System.out.print("Fecha de registro (dd/MM/yyyy): ");
                        String fechaTxt = sc.nextLine();
                        valido = Validaciones.validarFecha(fechaTxt) && cuenta.setFechaRegistro(convertirFecha(fechaTxt));
                        if (!valido) System.out.println("Fecha inválida.");
                    } while (!valido);

                    // Aquí compruebas que los objetos guardaron los datos validados
                    System.out.println("Validación completada:");
                    System.out.println(usuario.toString());
                    System.out.println(cuenta.toString());
                    break;

                case 0:
                    System.out.println("Regresando...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static Date convertirFecha(String fechaTxt) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            return sdf.parse(fechaTxt);
        } catch (ParseException e) {
            return null;
        }
    }
}