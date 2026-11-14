package ec.edu.uce.gestor.Consola;

import ec.edu.uce.gestor.Util.Validaciones;
import ec.edu.uce.gestor.dominio.Contenido;
import ec.edu.uce.gestor.dominio.Libro;
import ec.edu.uce.gestor.dominio.Pelicula;
import ec.edu.uce.gestor.dominio.Serie;
import ec.edu.uce.gestor.dominio.Videojuego;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MenuContenido {

    private static int contadorIdContenido = 1;

    private static Libro libroRegistrado = null;
    private static Pelicula peliculaRegistrada = null;
    private static Serie serieRegistrada = null;
    private static Videojuego videojuegoRegistrado = null;

    // Métodos auxiliares públicos para que MenuValoracion y MenuOpinion verifiquen contenidos existentes
    public static boolean hayContenidoRegistrado() {
        return libroRegistrado != null || peliculaRegistrada != null
                || serieRegistrada != null || videojuegoRegistrado != null;
    }

    public static void mostrarResumenContenidos() {
        System.out.println("\n--- CONTENIDOS DISPONIBLES EN CATÁLOGO ---");
        if (libroRegistrado != null) {
            System.out.println(" [ID: " + libroRegistrado.getIdContenido() + "] Libro: " + libroRegistrado.getTitulo());
        }
        if (peliculaRegistrada != null) {
            System.out.println(" [ID: " + peliculaRegistrada.getIdContenido() + "] Película: " + peliculaRegistrada.getTitulo());
        }
        if (serieRegistrada != null) {
            System.out.println(" [ID: " + serieRegistrada.getIdContenido() + "] Serie: " + serieRegistrada.getTitulo());
        }
        if (videojuegoRegistrado != null) {
            System.out.println(" [ID: " + videojuegoRegistrado.getIdContenido() + "] Videojuego: " + videojuegoRegistrado.getTitulo());
        }
    }

    public static String obtenerTituloPorId(int id) {
        if (libroRegistrado != null && libroRegistrado.getIdContenido() == id) return "Libro: " + libroRegistrado.getTitulo();
        if (peliculaRegistrada != null && peliculaRegistrada.getIdContenido() == id) return "Película: " + peliculaRegistrada.getTitulo();
        if (serieRegistrada != null && serieRegistrada.getIdContenido() == id) return "Serie: " + serieRegistrada.getTitulo();
        if (videojuegoRegistrado != null && videojuegoRegistrado.getIdContenido() == id) return "Videojuego: " + videojuegoRegistrado.getTitulo();
        return null;
    }

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n===(H2). GESTIONAR CONTENIDO DE ENTRETENIMIENTO ===");
            System.out.println("1. Registrar Contenido");
            System.out.println("2. Eliminar Contenido Erróneo");
            System.out.println("3. Consultar Catálogo");
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
                    if (!MenuPrincipal.sesionIniciada) {
                        System.out.println("\n[ACCESO RESTRINGIDO]: Debe iniciar sesión para registrar contenido.");
                    } else {
                        menuRegistrarContenido(sc);
                    }
                    break;

                case 2:
                    if (!MenuPrincipal.sesionIniciada) {
                        System.out.println("\n[ACCESO RESTRINGIDO]: Debe iniciar sesión para eliminar contenido.");
                    } else {
                        eliminarContenido(sc);
                    }
                    break;

                case 3:
                    menuCatalogo(sc);
                    break;

                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;

                default:
                    System.out.println("[ERROR]: Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static void menuRegistrarContenido(Scanner sc) {

        int opcion = -1;

        do {
            System.out.println("\n=== REGISTRAR CONTENIDO (H2.1) ===");
            System.out.println("1. Registrar Libro");
            System.out.println("2. Registrar Película");
            System.out.println("3. Registrar Serie");
            System.out.println("4. Registrar Videojuego");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            String entrada = sc.nextLine();
            if (!Validaciones.validarNumeros(entrada)) {
                System.out.println("[ERROR]: Ingrese solo números.");
                continue;
            }
            opcion = Integer.parseInt(entrada);

            switch (opcion) {
                case 1: registrarLibro(sc); break;
                case 2: registrarPelicula(sc); break;
                case 3: registrarSerie(sc); break;
                case 4: registrarVideojuego(sc); break;
                case 0: break;
                default: System.out.println("[ERROR]: Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static void cargarDatosComunes(Scanner sc, Contenido contenido) {
        boolean valido;

        // ID automático, fecha de publicación ingresada manualmente por teclado
        contenido.setIdContenido(contadorIdContenido++);

        do {
            System.out.print("Ingrese título: ");
            valido = contenido.setTitulo(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: El título no puede estar vacío.");
        } while (!valido);

        do {
            System.out.print("Ingrese autor / director / desarrollador (solo letras): ");
            valido = contenido.setAutor(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Autor inválido (use solo letras y espacios).");
        } while (!valido);

        do {
            System.out.print("Ingrese género (solo letras): ");
            valido = contenido.setGenero(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: Género inválido (use solo letras y espacios).");
        } while (!valido);

        do {
            System.out.print("Ingrese fecha de publicación (formato dd/MM/yyyy): ");
            String fechaTxt = sc.nextLine();
            valido = Validaciones.validarFecha(fechaTxt) && contenido.setFechaPublicacion(convertirFecha(fechaTxt));
            if (!valido) System.out.println("[ERROR]: Fecha inválida. Use el formato dd/MM/yyyy.");
        } while (!valido);
    }

    private static void registrarLibro(Scanner sc) {
        Libro libro = new Libro();
        System.out.println("\n--- NUEVO LIBRO (H2.1.2) ---");
        cargarDatosComunes(sc, libro);
        boolean valido;

        do {
            System.out.print("Ingrese número de páginas (números > 0): ");
            String pagTxt = sc.nextLine();
            valido = Validaciones.validarNumeros(pagTxt) && libro.setNumeroPaginas(Integer.parseInt(pagTxt));
            if (!valido) System.out.println("[ERROR]: Número de páginas inválido.");
        } while (!valido);

        do {
            System.out.print("Ingrese descripción: ");
            valido = libro.setDescripcion(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: La descripción no puede estar vacía.");
        } while (!valido);

        libroRegistrado = libro;
        System.out.println("\n[REGISTRO EXITOSO]: " + libroRegistrado.toString());
    }

    private static void registrarPelicula(Scanner sc) {
        Pelicula pelicula = new Pelicula();
        System.out.println("\n--- NUEVA PELÍCULA (H2.1.1) ---");
        cargarDatosComunes(sc, pelicula);
        boolean valido;

        do {
            System.out.print("Ingrese duración en minutos (números > 0): ");
            String durTxt = sc.nextLine();
            valido = Validaciones.validarNumeros(durTxt) && pelicula.setDuracion(Integer.parseInt(durTxt));
            if (!valido) System.out.println("[ERROR]: Duración inválida.");
        } while (!valido);

        do {
            System.out.print("Ingrese clasificación (ej. PG-13, +18): ");
            valido = pelicula.setClasificacion(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: La clasificación no puede estar vacía.");
        } while (!valido);

        peliculaRegistrada = pelicula;
        System.out.println("\n[REGISTRO EXITOSO]: " + peliculaRegistrada.toString());
    }

    private static void registrarSerie(Scanner sc) {
        Serie serie = new Serie();
        System.out.println("\n--- NUEVA SERIE (H2.1.3) ---");
        cargarDatosComunes(sc, serie);
        boolean valido;

        do {
            System.out.print("Ingrese número de temporadas (números > 0): ");
            String tempTxt = sc.nextLine();
            valido = Validaciones.validarNumeros(tempTxt) && serie.setNumeroTemporadas(Integer.parseInt(tempTxt));
            if (!valido) System.out.println("[ERROR]: Número de temporadas inválido.");
        } while (!valido);

        do {
            System.out.print("Ingrese número de episodios (números > 0): ");
            String epTxt = sc.nextLine();
            valido = Validaciones.validarNumeros(epTxt) && serie.setNumeroEpisodios(Integer.parseInt(epTxt));
            if (!valido) System.out.println("[ERROR]: Número de episodios inválido.");
        } while (!valido);

        serieRegistrada = serie;
        System.out.println("\n[REGISTRO EXITOSO]: " + serieRegistrada.toString());
    }

    private static void registrarVideojuego(Scanner sc) {
        Videojuego videojuego = new Videojuego();
        System.out.println("\n--- NUEVO VIDEOJUEGO (H2.1.4) ---");
        cargarDatosComunes(sc, videojuego);
        boolean valido;

        do {
            System.out.print("Ingrese plataforma (ej. PC, PS5, Xbox): ");
            valido = videojuego.setPlataforma(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: La plataforma no puede estar vacía.");
        } while (!valido);

        do {
            System.out.print("Ingrese clasificación de edad (ej. E, T, M): ");
            valido = videojuego.setClasificacionEdad(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: La clasificación de edad no puede estar vacía.");
        } while (!valido);

        videojuegoRegistrado = videojuego;
        System.out.println("\n[REGISTRO EXITOSO]: " + videojuegoRegistrado.toString());
    }

    private static void eliminarContenido(Scanner sc) {
        // Restricción inicial: si no existe nada registrado, no deja continuar
        if (!hayContenidoRegistrado()) {
            System.out.println("\n[RESTRICCIÓN]: No existen contenidos registrados para eliminar.");
            return;
        }

        // Mostramos los IDs disponibles para que el usuario no tenga que memorizarlos
        mostrarResumenContenidos();

        Contenido temp = new Contenido();
        boolean valido;

        do {
            System.out.print("Ingrese el ID del contenido a eliminar: ");
            String idTxt = sc.nextLine();
            valido = Validaciones.validarNumeros(idTxt) && temp.setIdContenido(Integer.parseInt(idTxt));
            if (!valido) System.out.println("[ERROR]: ID inválido. Ingrese solo números mayores a 0.");
        } while (!valido);

        int idBuscar = temp.getIdContenido();
        if (libroRegistrado != null && libroRegistrado.getIdContenido() == idBuscar) {
            libroRegistrado = null;
            System.out.println("[SISTEMA]: Libro eliminado correctamente.");
        } else if (peliculaRegistrada != null && peliculaRegistrada.getIdContenido() == idBuscar) {
            peliculaRegistrada = null;
            System.out.println("[SISTEMA]: Película eliminada correctamente.");
        } else if (serieRegistrada != null && serieRegistrada.getIdContenido() == idBuscar) {
            serieRegistrada = null;
            System.out.println("[SISTEMA]: Serie eliminada correctamente.");
        } else if (videojuegoRegistrado != null && videojuegoRegistrado.getIdContenido() == idBuscar) {
            videojuegoRegistrado = null;
            System.out.println("[SISTEMA]: Videojuego eliminado correctamente.");
        } else {
            System.out.println("[ERROR]: El ID ingresado no coincide con ninguno de los contenidos mostrados.");
        }
    }

    private static void menuCatalogo(Scanner sc) {

        int opcion = -1;

        do {
            System.out.println("\n=== CONSULTAR CATÁLOGO (H2.3) ===");
            System.out.println("1. Visualizar Series");
            System.out.println("2. Visualizar Libros");
            System.out.println("3. Visualizar Películas");
            System.out.println("4. Visualizar Videojuegos");
            System.out.println("5. Buscar Contenido");
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
                    System.out.println(serieRegistrada != null ? serieRegistrada.toString() : "[INFO]: No hay series registradas aún.");
                    break;
                case 2:
                    System.out.println(libroRegistrado != null ? libroRegistrado.toString() : "[INFO]: No hay libros registrados aún.");
                    break;
                case 3:
                    System.out.println(peliculaRegistrada != null ? peliculaRegistrada.toString() : "[INFO]: No hay películas registradas aún.");
                    break;
                case 4:
                    System.out.println(videojuegoRegistrado != null ? videojuegoRegistrado.toString() : "[INFO]: No hay videojuegos registrados aún.");
                    break;
                case 5:
                    menuBuscarContenido(sc);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("[ERROR]: Opción inválida.");
            }

        } while (opcion != 0);
    }

    private static void menuBuscarContenido(Scanner sc) {

        int opcion = -1;
        Contenido busqueda = new Contenido();

        do {
            System.out.println("\n=== BUSCAR CONTENIDO (H2.3.5) ===");
            System.out.println("1. Buscar por Título");
            System.out.println("2. Buscar por Género");
            System.out.println("3. Buscar por Autor");
            System.out.println("0. Regresar");
            System.out.print("Seleccione una opción: ");

            String entrada = sc.nextLine();
            if (!Validaciones.validarNumeros(entrada)) {
                System.out.println("[ERROR]: Ingrese solo números.");
                continue;
            }
            opcion = Integer.parseInt(entrada);

            boolean valido;
            switch (opcion) {
                case 1:
                    do {
                        System.out.print("Ingrese título a buscar: ");
                        valido = busqueda.setTitulo(sc.nextLine());
                        if (!valido) System.out.println("[ERROR]: El título no puede estar vacío.");
                    } while (!valido);
                    System.out.println("[VALIDACIÓN EXITOSA]: Criterio de búsqueda -> " + busqueda.getTitulo());
                    break;

                case 2:
                    do {
                        System.out.print("Ingrese género a buscar (solo letras): ");
                        valido = busqueda.setGenero(sc.nextLine());
                        if (!valido) System.out.println("[ERROR]: Género inválido (solo letras).");
                    } while (!valido);
                    System.out.println("[VALIDACIÓN EXITOSA]: Criterio de búsqueda -> " + busqueda.getGenero());
                    break;

                case 3:
                    do {
                        System.out.print("Ingrese autor a buscar (solo letras): ");
                        valido = busqueda.setAutor(sc.nextLine());
                        if (!valido) System.out.println("[ERROR]: Autor inválido (solo letras).");
                    } while (!valido);
                    System.out.println("[VALIDACIÓN EXITOSA]: Criterio de búsqueda -> " + busqueda.getAutor());
                    break;

                case 0:
                    break;

                default:
                    System.out.println("[ERROR]: Opción inválida.");
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