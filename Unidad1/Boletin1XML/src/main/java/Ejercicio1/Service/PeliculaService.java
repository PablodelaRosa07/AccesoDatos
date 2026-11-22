package Ejercicio1.Service;

import java.util.List;

import Ejercicio1.Models.Pelicula;
import Ejercicio1.Repository.PeliculaRepositorio;

public class PeliculaService {

    private final PeliculaRepositorio peliculaRepositorio;

    public PeliculaService() {
        this.peliculaRepositorio = new PeliculaRepositorio();
    }

    public PeliculaService(PeliculaRepositorio peliculaRepositorio) {
        this.peliculaRepositorio = peliculaRepositorio;
    }

    public List<Pelicula> obtenerPeliculas(String nombreFichero) {
        peliculaRepositorio.cargarDesdeFichero(nombreFichero);
        List<Pelicula> peliculas = peliculaRepositorio.getListaPeliculas();
        return peliculas;
    }

    public List<Pelicula> listarPeliculasEnMemoria() {
        return peliculaRepositorio.getListaPeliculas();
    }
}