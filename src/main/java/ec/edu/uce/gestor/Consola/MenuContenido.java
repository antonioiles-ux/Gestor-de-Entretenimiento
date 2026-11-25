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

    // Obtiene una copia defensiva del arreglo polimórfico del gestor
    private static Contenido[] obtenerContenidosActuales() {
        return MenuPrincipal.gestorGlobal.listarContenidos();
    }

    private static String obtenerEtiquetaTipo(Contenido c) {
        if (c instanceof Libro) return "Libro";
        if (c instanceof Pelicula) return "Película";
        if (c instanceof Serie) return "Serie";
        if (c instanceof Videojuego) return "Videojuego";
        return "Contenido";
    }

    // Métodos puente para MenuOpinion, MenuValoracion y MenuSolicitud
    public static boolean hayContenidoRegistrado() {
        return MenuPrincipal.gestorGlobal.getCantidadContenidos() > 0;
    }

    public static void mostrarResumenContenidos() {
        System.out.println("\n--- CONTENIDOS DISPONIBLES EN CATÁLOGO ---");
        for (Contenido c : obtenerContenidosActuales()) {
            System.out.println(" [ID: " + c.getIdContenido() + "] " + obtenerEtiquetaTipo(c)
                    + ": " + c.getTitulo() + " (Autor: " + c.getAutor() + ")");
        }
    }

    public static Contenido obtenerContenidoPorId(int id) {
        return MenuPrincipal.gestorGlobal.buscarContenido(id);
    }

    public static void mostrarMenu() {

        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        do {
            System.out.println("\n===(H2). GESTIONAR CONTENIDO DE ENTRETENIMIENTO ===");
            System.out.println("1. Registrar Contenido");
            System.out.println("2. Editar Título de Contenido");
            System.out.println("3. Eliminar Contenido Erróneo");
            System.out.println("4. Consultar Catálogo");
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
                        System.out.println("\n[ACCESO RESTRINGIDO]: Debe iniciar sesión para editar contenido.");
                    } else {
                        editarContenido(sc);
                    }
                    break;

                case 3:
                    if (!MenuPrincipal.sesionIniciada) {
                        System.out.println("\n[ACCESO RESTRINGIDO]: Debe iniciar sesión para eliminar contenido.");
                    } else {
                        eliminarContenido(sc);
                    }
                    break;

                case 4:
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

        contenido.setIdContenido(contadorIdContenido);

        do {
            System.out.print("Ingrese título: ");
            valido = contenido.setTitulo(sc.nextLine());
            if (!valido) System.out.println("[ERROR]: El título debe contener solo letras y espacios.");
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

    private static void guardarEnCatalogo(Contenido nuevo) {
        if (MenuPrincipal.gestorGlobal.agregarContenido(nuevo)) {
            contadorIdContenido++;
            if (!MenuPrincipal.esAdmin && MenuPrincipal.usuarioActivo != null) {
                MenuPrincipal.usuarioActivo.agregarContenido(nuevo);
            }
            System.out.println("\n[REGISTRO EXITOSO]: " + nuevo.toString());
        } else {
            System.out.println("\n[ERROR]: No se pudo registrar (contenido duplicado o catálogo lleno).");
        }
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

        guardarEnCatalogo(libro);
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
            if (!valido) System.out.println("[ERROR]: Clasificación inválida.");
        } while (!valido);

        guardarEnCatalogo(pelicula);
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

        guardarEnCatalogo(serie);
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
            if (!valido) System.out.println("[ERROR]: Clasificación de edad inválida.");
        } while (!valido);

        guardarEnCatalogo(videojuego);
    }

    private static void editarContenido(Scanner sc) {
        if (!hayContenidoRegistrado()) {
            System.out.println("\n[RESTRICCIÓN]: No existen contenidos registrados para editar.");
            return;
        }

        mostrarResumenContenidos();

        int idBuscar = 0;
        boolean valido;

        do {
            System.out.print("Ingrese el ID del contenido a editar: ");
            String idTxt = sc.nextLine();
            if (Validaciones.validarNumeros(idTxt) && Integer.parseInt(idTxt) > 0) {
                idBuscar = Integer.parseInt(idTxt);
                valido = true;
            } else {
                System.out.println("[ERROR]: ID inválido.");
                valido = false;
            }
        } while (!valido);

        Contenido c = MenuPrincipal.gestorGlobal.buscarContenido(idBuscar);
        if (c == null) {
            System.out.println("[ERROR]: El ID ingresado no coincide con ningún contenido.");
            return;
        }

        System.out.print("Ingrese nuevo título: ");
        if (MenuPrincipal.gestorGlobal.editarContenido(idBuscar, sc.nextLine())) {
            System.out.println("[EDICIÓN EXITOSA]: " + c.obtenerFicha(true));
        } else {
            System.out.println("[ERROR]: Título inválido (use solo letras y espacios).");
        }
    }

    private static void eliminarContenido(Scanner sc) {
        if (!hayContenidoRegistrado()) {
            System.out.println("\n[RESTRICCIÓN]: No existen contenidos registrados para eliminar.");
            return;
        }

        mostrarResumenContenidos();

        int idBuscar = 0;
        boolean valido;

        do {
            System.out.print("Ingrese el ID del contenido a eliminar: ");
            String idTxt = sc.nextLine();
            if (Validaciones.validarNumeros(idTxt) && Integer.parseInt(idTxt) > 0) {
                idBuscar = Integer.parseInt(idTxt);
                valido = true;
            } else {
                System.out.println("[ERROR]: ID inválido. Ingrese solo números mayores a 0.");
                valido = false;
            }
        } while (!valido);

        if (MenuPrincipal.gestorGlobal.eliminarContenido(idBuscar)) {
            if (!MenuPrincipal.esAdmin && MenuPrincipal.usuarioActivo != null) {
                MenuPrincipal.usuarioActivo.eliminarContenido(idBuscar);
            }
            System.out.println("[SISTEMA]: Contenido eliminado correctamente del catálogo.");
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
                case 2:
                case 3:
                case 4:
                    boolean encontrado = false;
                    for (Contenido c : obtenerContenidosActuales()) {
                        if ((opcion == 1 && c instanceof Serie)
                                || (opcion == 2 && c instanceof Libro)
                                || (opcion == 3 && c instanceof Pelicula)
                                || (opcion == 4 && c instanceof Videojuego)) {
                            mostrarDetallePolimorfico(c);
                            System.out.println("------------------------------------------------");
                            encontrado = true;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("[INFO]: No hay contenido registrado en esta categoría aún.");
                    }
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

        if (!hayContenidoRegistrado()) {
            System.out.println("\n[RESTRICCIÓN]: No existen contenidos registrados para realizar búsquedas.");
            return;
        }

        int opcion = -1;

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

            if (opcion == 0) {
                System.out.println("Regresando...");
                break;
            }
            if (opcion < 1 || opcion > 3) {
                System.out.println("Opción inválida.");
                continue;
            }

            String etiquetaPrompt = (opcion == 1) ? "título" : (opcion == 2) ? "género" : "autor";
            System.out.print("Ingrese " + etiquetaPrompt + " a buscar: ");
            String criterio = sc.nextLine();

            System.out.println("\n=== RESULTADOS DE BÚSQUEDA ===");
            boolean encontrado = false;

            for (Contenido c : obtenerContenidosActuales()) {
                boolean coincide = (opcion == 1 && c.getTitulo().toLowerCase().contains(criterio.toLowerCase()))
                        || (opcion == 2 && c.getGenero().equalsIgnoreCase(criterio))
                        || (opcion == 3 && c.getAutor().equalsIgnoreCase(criterio));

                if (coincide) {
                    System.out.println("\n[" + obtenerEtiquetaTipo(c).toUpperCase() + " ENCONTRADO]");
                    mostrarDetallePolimorfico(c);
                    encontrado = true;
                }
            }

            if (!encontrado) {
                System.out.println("[INFO]: No se encontraron contenidos con ese " + etiquetaPrompt + ".");
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

    // --- POLIMORFISMO + INSTANCEOF CON CASTING ---
    private static void mostrarDetallePolimorfico(Contenido contenido) {
        if (contenido == null) {
            return;
        }
        System.out.println(contenido.toString());
        System.out.println(" -> Tiempo estimado de consumo: " + contenido.calcularTiempoConsumo() + " minutos.");
        System.out.printf(" -> Valoración promedio: %.1f/5.0 (%d valoraciones, %d opiniones)%n",
                contenido.calcularPromedioValoraciones(),
                contenido.getCantidadValoraciones(),
                contenido.getCantidadOpiniones());

        if (contenido instanceof Libro l) {
            System.out.println(" -> Detalle específico (Libro): " + l.getNumeroPaginas() + " páginas.");
        } else if (contenido instanceof Pelicula p) {
            System.out.println(" -> Detalle específico (Película): Clasificación " + p.getClasificacion());
        } else if (contenido instanceof Serie s) {
            System.out.println(" -> Detalle específico (Serie): " + s.getNumeroTemporadas() + " temporadas.");
        } else if (contenido instanceof Videojuego v) {
            System.out.println(" -> Detalle específico (Videojuego): Plataforma " + v.getPlataforma());
        }
    }

}
