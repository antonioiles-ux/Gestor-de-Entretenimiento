package ec.edu.uce.gestor.Consola;

import ec.edu.uce.gestor.Util.Validaciones;
import ec.edu.uce.gestor.dominio.Cuenta;
import ec.edu.uce.gestor.dominio.GestorContenido;
import ec.edu.uce.gestor.dominio.Usuario;
import java.util.Date;
import java.util.Scanner;

public class MenuCuenta {

    private static int contadorIdUsuario = 1;
    private static int contadorIdCuenta = 1;

    private static Usuario usuarioRegistrado = null;
    private static Cuenta cuentaRegistrada = null;
    private static boolean cuentaSuspendida = false;

    // Credenciales institucionales predefinidas del Administrador (GestorContenido)
    private static GestorContenido adminSistema = new GestorContenido("Administrador UCE", 99, 1);
    private static Cuenta cuentaAdmin = new Cuenta(99, "admin@uce.edu.ec", "admin123", new Date());

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n===(H1). GESTIONAR CUENTA ===");
            System.out.println("1. Iniciar Sesión (Usuario)");
            System.out.println("2. Registrar Usuario");
            System.out.println("3. Iniciar Sesión como Administrador");
            System.out.println("4. Consultar Datos de Cuenta Generada");
            if (MenuPrincipal.esAdmin) {
                System.out.println("5. [ADMIN] Administrar Cuenta de Usuario (Suspender / Reactivar / Eliminar)");
            }
            if (MenuPrincipal.sesionIniciada) {
                System.out.println("6. Cerrar Sesión");
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
                    iniciarSesionUsuario(sc);
                    break;

                case 2:
                    registrarUsuario(sc);
                    break;

                case 3:
                    iniciarSesionAdmin(sc);
                    break;

                case 4:
                    if (usuarioRegistrado != null && cuentaRegistrada != null) {
                        System.out.println("\n--- DATOS ALMACENADOS EN LOS OBJETOS ---");
                        System.out.println("Estado de cuenta: " + (cuentaSuspendida ? "SUSPENDIDA" : "ACTIVA"));
                        System.out.println(usuarioRegistrado.toString());
                        System.out.println(cuentaRegistrada.toString());
                    } else {
                        System.out.println("\n[INFO]: Aún no se ha registrado ningún usuario en esta ejecución.");
                    }
                    break;

                case 5:
                    if (MenuPrincipal.esAdmin) {
                        panelAdministrador(sc);
                    } else {
                        System.out.println("[ERROR]: Opción no válida.");
                    }
                    break;

                case 6:
                    if (MenuPrincipal.sesionIniciada) {
                        MenuPrincipal.sesionIniciada = false;
                        MenuPrincipal.esAdmin = false;
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

        do {
            System.out.print("Ingrese nombre completo (solo letras): ");
            valido = nuevoUsuario.setNombreCompleto(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Nombre inválido. Use únicamente letras y espacios.");
        } while (!valido);

        do {
            System.out.print("Ingrese correo electrónico (ej. juanko@uce.edu.ec): ");
            valido = nuevaCuenta.setCorreoElectronico(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Correo inválido. Debe contener '@' y un dominio válido.");
        } while (!valido);

        do {
            System.out.print("Ingrese contraseña (mínimo 6 caracteres sin espacios): ");
            valido = nuevaCuenta.setContrasena(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Contraseña inválida. Mínimo 6 caracteres y sin espacios.");
        } while (!valido);

        nuevoUsuario.setIdUsuario(contadorIdUsuario++);
        nuevaCuenta.setIdCuenta(contadorIdCuenta++);
        nuevaCuenta.setFechaRegistro(new Date());

        usuarioRegistrado = nuevoUsuario;
        cuentaRegistrada = nuevaCuenta;
        cuentaSuspendida = false;

        System.out.println("\n[REGISTRO EXITOSO]: Datos validados y atributos automáticos generados:");
        System.out.println(" -> " + usuarioRegistrado.toString());
        System.out.println(" -> " + cuentaRegistrada.toString());
    }

    private static void iniciarSesionUsuario(Scanner sc) {
        if (cuentaRegistrada == null) {
            System.out.println("\n[AVISO]: No existe un usuario registrado todavía. Primero regístrese en la opción 2.");
            return;
        }

        Cuenta cuentaTemp = new Cuenta();
        boolean valido;

        System.out.println("\n--- INICIAR SESIÓN USUARIO (H1.1) ---");

        do {
            System.out.print("Correo electrónico: ");
            valido = cuentaTemp.setCorreoElectronico(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Formato de correo inválido.");
        } while (!valido);

        do {
            System.out.print("Contraseña: ");
            valido = cuentaTemp.setContrasena(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Formato de contraseña inválido.");
        } while (!valido);

        if (cuentaTemp.getCorreoElectronico().equals(cuentaRegistrada.getCorreoElectronico())
                && cuentaTemp.getContrasena().equals(cuentaRegistrada.getContrasena())) {

            if (cuentaSuspendida) {
                System.out.println("\n[ACCESO DENEGADO]: Esta cuenta se encuentra SUSPENDIDA por el Administrador.");
                return;
            }

            MenuPrincipal.sesionIniciada = true;
            MenuPrincipal.esAdmin = false;
            System.out.println("\nIngreso exitoso. Bienvenido/a, " + usuarioRegistrado.getNombreCompleto() + ".");
        } else {
            System.out.println("\n[ERROR]: Correo o contraseña incorrectos.");
        }
    }

    private static void iniciarSesionAdmin(Scanner sc) {
        Cuenta cuentaTemp = new Cuenta();
        boolean valido;

        System.out.println("\n--- INICIAR SESIÓN ADMINISTRADOR ---");
        System.out.println("(Credenciales demo: admin@uce.edu.ec / admin123)");

        do {
            System.out.print("Correo Administrador: ");
            valido = cuentaTemp.setCorreoElectronico(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Formato de correo inválido.");
        } while (!valido);

        do {
            System.out.print("Contraseña Administrador: ");
            valido = cuentaTemp.setContrasena(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Formato de contraseña inválido.");
        } while (!valido);

        if (cuentaTemp.getCorreoElectronico().equals(cuentaAdmin.getCorreoElectronico())
                && cuentaTemp.getContrasena().equals(cuentaAdmin.getContrasena())) {
            MenuPrincipal.sesionIniciada = true;
            MenuPrincipal.esAdmin = true;
            System.out.println("\nIngreso exitoso como Administrador (" + adminSistema.toString() + ").");
        } else {
            System.out.println("\n[ERROR]: Credenciales de administrador incorrectas.");
        }
    }

    private static void panelAdministrador(Scanner sc) {
        if (cuentaRegistrada == null || usuarioRegistrado == null) {
            System.out.println("\n[RESTRICCIÓN]: No existe ninguna cuenta de usuario registrada para administrar.");
            return;
        }

        System.out.println("\n--- CUENTA DE USUARIO REGISTRADA ---");
        System.out.println(" [ID Cuenta: " + cuentaRegistrada.getIdCuenta() + "] Usuario: "
                + usuarioRegistrado.getNombreCompleto() + " | Correo: " + cuentaRegistrada.getCorreoElectronico()
                + " | Estado: " + (cuentaSuspendida ? "SUSPENDIDA" : "ACTIVA"));

        Cuenta temp = new Cuenta();
        boolean valido;

        do {
            System.out.print("Ingrese el ID de la cuenta a gestionar: ");
            String idTxt = sc.nextLine();
            valido = Validaciones.validarNumeros(idTxt) && temp.setIdCuenta(Integer.parseInt(idTxt));
            if (!valido) System.out.println("[ERROR]: ID inválido.");
        } while (!valido);

        if (temp.getIdCuenta() != cuentaRegistrada.getIdCuenta()) {
            System.out.println("[ERROR]: El ID no coincide con la cuenta mostrada.");
            return;
        }

        System.out.println("1. Suspender Cuenta");
        System.out.println("2. Reactivar Cuenta");
        System.out.println("3. Eliminar Cuenta");
        System.out.print("Seleccione acción administrativa: ");
        String accion = sc.nextLine();

        switch (accion) {
            case "1":
                adminSistema.suspenderCuenta();
                cuentaSuspendida = true;
                System.out.println("[SISTEMA]: La cuenta de " + usuarioRegistrado.getNombreCompleto() + " ahora está SUSPENDIDA.");
                break;
            case "2":
                adminSistema.reactivarCuenta();
                cuentaSuspendida = false;
                System.out.println("[SISTEMA]: La cuenta de " + usuarioRegistrado.getNombreCompleto() + " ahora está ACTIVA.");
                break;
            case "3":
                adminSistema.eliminarCuenta();
                usuarioRegistrado = null;
                cuentaRegistrada = null;
                cuentaSuspendida = false;
                System.out.println("[SISTEMA]: Cuenta eliminada permanentemente.");
                break;
            default:
                System.out.println("[ERROR]: Acción no válida.");
        }
    }
}