package Ejercicio2.Controller;

import Ejercicio2.Models.Autor;
import Ejercicio2.Models.Libro;
import Ejercicio2.Service.LibroService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class LibroController {
    private static final Logger logger = LogManager.getLogger(LibroController.class);
    private LibroService servicio;

    public LibroController(LibroService servicio) {
        this.servicio = servicio;
    }

    public void gestionarCargaYListado(String rutaLibros, String rutaAutores, String paisFiltro, String rutaSalidaCsv) {
        logger.info("Iniciando carga y validación de libros desde ambos ficheros JSON");
        servicio.procesarYAlmacenarLibros(rutaLibros, rutaAutores);

        logger.info("Exportando libros del país '{}' a CSV", paisFiltro);
        boolean exitoCsv = servicio.exportarLibrosPorPaisCSV(paisFiltro, rutaSalidaCsv);
        if (exitoCsv) {
            logger.info("Fichero CSV generado correctamente en: {}", rutaSalidaCsv);
        } else {
            logger.error("No se pudo generar el fichero CSV.");
        }

        System.out.println("\nListado final de libros almacenados en la BD simulada");
        List<Libro> librosValidos = servicio.listarLibrosAlmacenados();
        
        for (Libro l : librosValidos) {
            String autorNombre;
            Autor autorLibro = l.getAutor();
            
            if (autorLibro != null) {
                autorNombre = autorLibro.getNombre();
            } else {
                autorNombre = "Sin autor asignado";
            }

            logger.info("{} | Editorial: {} | Autor: {}", l.getTitulo(), l.getEditorial(), autorNombre);
        }
    }
}