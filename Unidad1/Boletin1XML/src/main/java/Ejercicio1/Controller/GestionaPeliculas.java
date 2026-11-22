package Ejercicio1.Controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Ejercicio1.Models.Pelicula;
import Ejercicio1.Service.PeliculaService;

public class GestionaPeliculas {

    private static final Logger logger = LogManager.getLogger(GestionaPeliculas.class);
    private final PeliculaService peliculaService;

    public GestionaPeliculas() {
        this.peliculaService = new PeliculaService();
    }

    public GestionaPeliculas(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    public List<Pelicula> listarPeliculas(String nombreFichero) {
        List<Pelicula> peliculas = peliculaService.obtenerPeliculas(nombreFichero);
        return peliculas;
    }

    public static void main(String[] args) {
        GestionaPeliculas gestiona = new GestionaPeliculas();
        List<Pelicula> peliculas = gestiona.listarPeliculas("peliculas.xml");

        if (peliculas.isEmpty()) {
            logger.info("No se encontraron películas en el fichero.");
        } else {
            for (int i = 0; i < peliculas.size(); i++) {
                Pelicula p = peliculas.get(i);
                logger.info("Título: " + p.getTitulo()
                        + " | Año: " + p.getFecha()
                        + " | Director: " + p.getDirector()
                        + " | Actores: " + p.getListaActores());
            }
        }
    }
}