package Ejercicio2.Service;

import Ejercicio2.Models.Autor;
import Ejercicio2.Models.Libro;
import Ejercicio2.Repository.LibroRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LibroService {
    private static final Logger logger = LogManager.getLogger(LibroService.class);
    private LibroRepository repositorio;

    public LibroService(LibroRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void procesarYAlmacenarLibros(String rutaLibros, String rutaAutores) {
        List<Libro> librosCargados = repositorio.leerFicherosJson(rutaLibros, rutaAutores);

        for (Libro libro : librosCargados) {
            if (validarLibro(libro)) {
                repositorio.guardar(libro);
                logger.info("Libro dado de alta exitosamente: '{}' (ISBN: {})", libro.getTitulo(), libro.getIsbn());
            } else {
                String tituloStr;
                if (libro != null && libro.getTitulo() != null) {
                    tituloStr = libro.getTitulo();
                } else {
                    tituloStr = "Título desconocido";
                }
                logger.error("No se ha podido realizar el alta del libro '{}' por no tener información de la editorial o datos obligatorios.", tituloStr);
            }
        }
    }

    public boolean agregarNuevoLibro(Libro libro) {
        boolean exito = false;

        if (validarLibro(libro)) {
            repositorio.guardar(libro);
            logger.info("Nuevo libro añadido manualmente con éxito: '{}'", libro.getTitulo());
            exito = true;
        } else {
            String tituloStr;
            if (libro != null && libro.getTitulo() != null) {
                tituloStr = libro.getTitulo();
            } else {
                tituloStr = "Título desconocido";
            }
            logger.error("Fallo al añadir el nuevo libro '{}'. Faltan datos obligatorios.", tituloStr);
        }

        return exito; // Único return
    }

    public boolean eliminarLibro(String isbn) {
        boolean resultadoBaja = false;
        boolean exitoRepositorio = repositorio.eliminarPorIsbn(isbn);

        if (exitoRepositorio) {
            logger.info("Libro con ISBN '{}' dado de baja correctamente.", isbn);
            resultadoBaja = true;
        } else {
            logger.error("No se ha podido realizar la baja. El ISBN '{}' no existe en el sistema.", isbn);
        }

        return resultadoBaja; // Único return
    }

    public boolean exportarLibrosPorPaisCSV(String pais, String rutaDestino) {
        boolean exportado = false;
        List<Libro> librosFiltrados = repositorio.obtenerPorPais(pais);

        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaDestino))) {
            writer.println("isbn,titulo,nombre_autor,apellidos_autor,pais_autor");

            for (Libro libro : librosFiltrados) {
                Autor autor = libro.getAutor();
                
                String nombreAutor;
                if (autor != null && autor.getNombre() != null) {
                    nombreAutor = autor.getNombre();
                } else {
                    nombreAutor = "";
                }

                String apellidosAutor;
                if (autor != null && autor.getApellidos() != null) {
                    apellidosAutor = autor.getApellidos();
                } else {
                    apellidosAutor = "";
                }

                String paisAutor;
                if (autor != null && autor.getPais() != null) {
                    paisAutor = autor.getPais();
                } else {
                    paisAutor = "";
                }

                writer.println(libro.getIsbn() + "," + libro.getTitulo() + "," + nombreAutor + "," + apellidosAutor + "," + paisAutor);
            }

            logger.info("Exportación completada exitosamente. Se han guardado {} libros del país '{}' en '{}'.", librosFiltrados.size(), pais, rutaDestino);
            exportado = true;
        } catch (IOException e) {
            logger.error("Error al generar el fichero CSV para el país '{}': {}", pais, e.getMessage());
        }

        return exportado; // Único return
    }

    private boolean validarLibro(Libro libro) {
        boolean valido = true;

        if (libro == null) {
            valido = false;
        } else if (libro.getIsbn() == null || libro.getIsbn().trim().isEmpty()) {
            valido = false;
        } else if (libro.getTitulo() == null || libro.getTitulo().trim().isEmpty()) {
            valido = false;
        } else if (libro.getEditorial() == null || libro.getEditorial().trim().isEmpty()) {
            valido = false;
        }

        return valido; // Único return
    }

    public List<Libro> listarLibrosAlmacenados() {
        List<Libro> listaLibros = repositorio.obtenerTodos();
        return listaLibros; // Único return
    }
}