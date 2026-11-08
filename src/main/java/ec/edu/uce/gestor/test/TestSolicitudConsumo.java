package ec.edu.uce.gestor.test;

import ec.edu.uce.gestor.dominio.SolicitudConsumo;
import java.util.Date;

public class TestSolicitudConsumo {

    public static void main(String[] args) {

        // Crear objeto usando constructor con parámetros
        SolicitudConsumo solicitud = new SolicitudConsumo(
                1,
                "Pendiente",
                new Date(),
                "Libro"
        );

        System.out.println("=== DATOS INICIALES ===");
        System.out.println("ID Solicitud: " + solicitud.getIdSolicitud());
        System.out.println("Estado: " + solicitud.getEstado());
        System.out.println("Fecha Solicitud: " + solicitud.getFechaSolicitud());
        System.out.println("Tipo de Contenido: " + solicitud.getTipoContenido());

        // Modificar datos usando setters
        solicitud.setIdSolicitud(2);
        solicitud.setEstado("Aprobada");
        solicitud.setFechaSolicitud(new Date());
        solicitud.setTipoContenido("Película");

        System.out.println("\n=== DATOS MODIFICADOS ===");
        System.out.println("ID Solicitud: " + solicitud.getIdSolicitud());
        System.out.println("Estado: " + solicitud.getEstado());
        System.out.println("Fecha Solicitud: " + solicitud.getFechaSolicitud());
        System.out.println("Tipo de Contenido: " + solicitud.getTipoContenido());
    }
}
