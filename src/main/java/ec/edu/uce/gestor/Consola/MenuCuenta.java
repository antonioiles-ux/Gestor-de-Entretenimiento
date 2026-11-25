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

    // Credenciales institucionales predefinidas del Administrador
    private static Cuenta cuentaAdmin = new Cuenta(99, "adminJose@uce.edu.ec", "admin123", new Date());

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n===(H1). GESTIONAR CUENTA ===");
            System.out.println("1. Iniciar Sesión (Usuario)");
            System.out.println("2. Registrar Usuario");
            System.out.println("3. Iniciar Sesión como Administrador");
            System.out.println("4. Consultar Cuentas Registradas");

            if (MenuPrincipal.esAdmin) {
                System.out.println("5. [ADMIN] Administrar Usuarios (Suspender / Reactivar / Editar / Eliminar)");
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
                    consultarCuentas();
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
                        MenuPrincipal.usuarioActivo = null;
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
        // COMPOSICIÓN: Usuario crea su propia Cuenta interna en su constructor
        Usuario nuevoUsuario = new Usuario();
        boolean valido;

        System.out.println("\n--- REGISTRO DE USUARIO ---");

        do {
            System.out.print("Ingrese nombre completo (solo letras): ");
            valido = nuevoUsuario.setNombreCompleto(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Nombre inválido. Use únicamente letras y espacios.");
        } while (!valido);

        do {
            System.out.print("Ingrese correo electrónico (ej. Joseiles@uce.edu.ec): ");
            String correoTxt = sc.nextLine();
            valido = Validaciones.validarCorreo(correoTxt)
                    && nuevoUsuario.getCuenta().setCorreoElectronico(correoTxt);
            if (!valido) System.out.println("[ERROR]: Correo inválido. Debe contener '@' y un dominio válido.");
        } while (!valido);

        do {
            System.out.print("Ingrese contraseña (mínimo 6 caracteres sin espacios): ");
            String passTxt = sc.nextLine();
            valido = Validaciones.validarContrasena(passTxt)
                    && nuevoUsuario.getCuenta().setContrasena(passTxt);
            if (!valido) System.out.println("[ERROR]: Contraseña inválida. Mínimo 6 caracteres y sin espacios.");
        } while (!valido);

        nuevoUsuario.setIdUsuario(contadorIdUsuario);
        nuevoUsuario.getCuenta().setIdCuenta(contadorIdCuenta);
        nuevoUsuario.getCuenta().setFechaRegistro(new Date());

        if (MenuPrincipal.gestorGlobal.agregarUsuario(nuevoUsuario)) {
            contadorIdUsuario++;
            contadorIdCuenta++;
            System.out.println("\n[REGISTRO EXITOSO]: Usuario y cuenta compuesta almacenados:");
            System.out.println(" -> " + nuevoUsuario.toString());
            System.out.println(" -> " + nuevoUsuario.getCuenta().toString());
        } else {
            System.out.println("\n[ERROR]: No se pudo registrar (correo duplicado o límite alcanzado).");
        }
    }

    private static void iniciarSesionUsuario(Scanner sc) {
        if (MenuPrincipal.gestorGlobal.getCantidadUsuarios() == 0) {
            System.out.println("\n[AVISO]: No existen usuarios registrados todavía. Primero regístrese en la opción 2.");
            return;
        }

        Cuenta cuentaTemp = new Cuenta();
        boolean valido;

        System.out.println("\n--- INICIAR SESIÓN USUARIO ---");

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

        Usuario encontrado = MenuPrincipal.gestorGlobal.buscarUsuarioPorCorreo(cuentaTemp.getCorreoElectronico());

        if (encontrado != null && encontrado.getCuenta().validarCredenciales(cuentaTemp)) {
            if (!encontrado.getCuenta().isActiva()) {
                System.out.println("\n[ACCESO DENEGADO]: Esta cuenta se encuentra SUSPENDIDA por el Administrador.");
                return;
            }
            MenuPrincipal.sesionIniciada = true;
            MenuPrincipal.esAdmin = false;
            MenuPrincipal.usuarioActivo = encontrado;
            System.out.println("\nIngreso exitoso. Bienvenido/a, " + encontrado.getNombreCompleto() + ".");
        } else {
            System.out.println("\n[ERROR]: Correo o contraseña incorrectos.");
        }
    }

    private static void iniciarSesionAdmin(Scanner sc) {
        Cuenta cuentaTemp = new Cuenta();
        boolean valido;

        System.out.println("\n--- INICIAR SESIÓN ADMINISTRADOR ---");
        System.out.println("(Credenciales demo: adminJose@uce.edu.ec / admin123)");

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

        if (cuentaAdmin.validarCredenciales(cuentaTemp)) {
            MenuPrincipal.sesionIniciada = true;
            MenuPrincipal.esAdmin = true;
            MenuPrincipal.usuarioActivo = null;
            System.out.println("\nIngreso exitoso como Administrador (" + MenuPrincipal.gestorGlobal.toString() + ").");
        } else {
            System.out.println("\n[ERROR]: Credenciales de administrador incorrectas.");
        }
    }

    private static void consultarCuentas() {
        Usuario[] lista = MenuPrincipal.gestorGlobal.listarUsuarios();
        if (lista.length == 0) {
            System.out.println("\n[INFO]: Aún no se ha registrado ningún usuario en esta ejecución.");
            return;
        }

        System.out.println("\n--- USUARIOS Y CUENTAS REGISTRADAS (" + lista.length + ") ---");
        for (Usuario u : lista) {
            Cuenta c = u.getCuenta();
            String estado = c.isActiva() ? "ACTIVA" : "SUSPENDIDA";
            System.out.println(" -> " + u.toString() + " | " + c.toString() + " | Estado: " + estado);
        }
    }

    private static void panelAdministrador(Scanner sc) {
        if (MenuPrincipal.gestorGlobal.getCantidadUsuarios() == 0) {
            System.out.println("\n[RESTRICCIÓN]: No existe ninguna cuenta de usuario registrada para administrar.");
            return;
        }

        consultarCuentas();

        int idBuscar = 0;
        boolean valido;

        do {
            System.out.print("Ingrese el ID del Usuario a gestionar: ");
            String idTxt = sc.nextLine();
            if (Validaciones.validarNumeros(idTxt) && Integer.parseInt(idTxt) > 0) {
                idBuscar = Integer.parseInt(idTxt);
                valido = true;
            } else {
                System.out.println("[ERROR]: ID inválido.");
                valido = false;
            }
        } while (!valido);

        Usuario objetivo = MenuPrincipal.gestorGlobal.buscarUsuario(idBuscar);
        if (objetivo == null) {
            System.out.println("[ERROR]: El ID no coincide con ningún usuario registrado.");
            return;
        }

        System.out.println("1. Suspender Cuenta");
        System.out.println("2. Reactivar Cuenta");
        System.out.println("3. Editar Nombre de Usuario");
        System.out.println("4. Eliminar Usuario y Cuenta");
        System.out.print("Seleccione acción administrativa: ");
        String accion = sc.nextLine();

        switch (accion) {
            case "1":
                if (MenuPrincipal.gestorGlobal.suspenderCuenta(idBuscar)) {
                    System.out.println("[SISTEMA]: La cuenta de " + objetivo.getNombreCompleto() + " ahora está SUSPENDIDA.");
                }
                break;
            case "2":
                if (MenuPrincipal.gestorGlobal.reactivarCuenta(idBuscar)) {
                    System.out.println("[SISTEMA]: La cuenta de " + objetivo.getNombreCompleto() + " ahora está ACTIVA.");
                }
                break;
            case "3":
                System.out.print("Ingrese nuevo nombre completo: ");
                if (MenuPrincipal.gestorGlobal.actualizarNombreUsuario(idBuscar, sc.nextLine())) {
                    System.out.println("[SISTEMA]: Nombre actualizado a -> " + objetivo.getNombreCompleto());
                } else {
                    System.out.println("[ERROR]: Nombre inválido.");
                }
                break;
            case "4":
                if (MenuPrincipal.gestorGlobal.eliminarUsuario(idBuscar)) {
                    System.out.println("[SISTEMA]: Usuario y cuenta eliminados permanentemente.");
                }
                break;
            default:
                System.out.println("[ERROR]: Acción no válida.");
        }
    }
}