package ec.edu.uce.gestor.Consola;

import ec.edu.uce.gestor.Util.Validaciones;
import ec.edu.uce.gestor.dominio.Cuenta;
import ec.edu.uce.gestor.dominio.Usuario;
import java.util.Date;
import java.util.Scanner;

public class MenuCuenta {

    // Contadores automáticos para IDs mientras no se usan arreglos
    private static int contadorIdUsuario = 1;
    private static int contadorIdCuenta = 1;

    // Referencia temporal en RAM del usuario registrado para poder iniciar sesión y exponer sus atributos
    private static Usuario usuarioRegistrado = null;
    private static Cuenta cuentaRegistrada = null;

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n===(H1). GESTIONAR CUENTA ===");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Registrar Usuario");
            System.out.println("3. Consultar Datos de Cuenta Generada");
            if (MenuPrincipal.sesionIniciada) {
                System.out.println("4. Cerrar Sesión");
            }
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            String entrada = sc.nextLine();
            if (!Validaciones.validarNumeros(entrada)) {
                System.out.println("[ERROR]: Ingrese solo números.");
                continue;
            }
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1:
                    iniciarSesion(sc);
                    break;

                case 2:
                    registrarUsuario(sc);
                    break;

                case 3:
                    if (usuarioRegistrado != null && cuentaRegistrada != null) {
                        System.out.println("\n--- DATOS ALMACENADOS EN LOS OBJETOS ---");
                        System.out.println(usuarioRegistrado.toString());
                        System.out.println(cuentaRegistrada.toString());
                    } else {
                        System.out.println("\n[INFO]: Aún no se ha registrado ningún usuario en esta ejecución.");
                    }
                    break;

                case 4:
                    if (MenuPrincipal.sesionIniciada) {
                        MenuPrincipal.sesionIniciada = false;
                        System.out.println("\n[SISTEMA]: Sesión cerrada correctamente.");
                    } else {
                        System.out.println("[ERROR]: Opción no válida.");
                    }
                    break;

                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("[ERROR]: Opción no válida.");
            }

        } while (opcion != 0);
    }

    private static void registrarUsuario(Scanner sc) {
        Usuario nuevoUsuario = new Usuario();
        Cuenta nuevaCuenta = new Cuenta();
        boolean valido;

        System.out.println("\n--- REGISTRO DE USUARIO (H1.2) ---");

        // 1. Datos ingresados por teclado y validados por los setters
        do {
            System.out.print("Ingrese nombre completo (solo letras): ");
            valido = nuevoUsuario.setNombreCompleto(sc.nextLine());
            if (!valido) {
                System.out.println("[ERROR]: Nombre inválido. Use únicamente letras y espacios.");
            }
        } while (!valido);

        do {
            System.out.print("Ingrese correo electrónico (ej. juanko@uce.edu.ec): ");
            valido = nuevaCuenta.setCorreoElectronico(sc.nextLine());
            if (!valido) {
                System.out.println("[ERROR]: Correo inválido. Debe contener '@' y un dominio válido.");
            }
        } while (!valido);

        do {
            System.out.print("Ingrese contraseña (mínimo 6 caracteres sin espacios): ");
            valido = nuevaCuenta.setContrasena(sc.nextLine());
            if (!valido) {
                System.out.println("[ERROR]: Contraseña inválida. Mínimo 6 caracteres y sin espacios.");
            }
        } while (!valido);

        // 2. Atributos generados automáticamente y pasados por los setters para validar
        nuevoUsuario.setIdUsuario(contadorIdUsuario++);
        nuevaCuenta.setIdCuenta(contadorIdCuenta++);
        nuevaCuenta.setFechaRegistro(new Date());

        usuarioRegistrado = nuevoUsuario;
        cuentaRegistrada = nuevaCuenta;

        System.out.println("\n[REGISTRO EXITOSO]: Datos validados y atributos automáticos generados:");
        System.out.println(" -> " + usuarioRegistrado.toString());
        System.out.println(" -> " + cuentaRegistrada.toString());
    }

    private static void iniciarSesion(Scanner sc) {
        if (cuentaRegistrada == null) {
            System.out.println("\n[AVISO]: No existe un usuario registrado todavía. Primero regístrese en la opción 2.");
            return;
        }

        Cuenta cuentaTemp = new Cuenta();
        boolean valido;

        System.out.println("\n--- INICIAR SESIÓN (H1.1) ---");

        do {
            System.out.print("Correo electrónico: ");
            valido = cuentaTemp.setCorreoElectronico(sc.nextLine());
            if (!valido) {
                System.out.println("[ERROR]: Formato de correo inválido.");
            }
        } while (!valido);

        do {
            System.out.print("Contraseña: ");
            valido = cuentaTemp.setContrasena(sc.nextLine());
            if (!valido) {
                System.out.println("[ERROR]: Formato de contraseña inválido.");
            }
        } while (!valido);

        if (cuentaTemp.getCorreoElectronico().equals(cuentaRegistrada.getCorreoElectronico())
                && cuentaTemp.getContrasena().equals(cuentaRegistrada.getContrasena())) {
            MenuPrincipal.sesionIniciada = true;
            System.out.println("\nIngreso exitoso. Bienvenido/a, " + usuarioRegistrado.getNombreCompleto() + ".");
            System.out.println("[SISTEMA]: Todos los módulos han sido desbloqueados.");
        } else {
            System.out.println("\n[ERROR]: Correo o contraseña incorrectos. Verifique los datos con los que se registró.");
        }
    }
}