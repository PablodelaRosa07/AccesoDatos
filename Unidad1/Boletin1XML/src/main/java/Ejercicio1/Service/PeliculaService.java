package Ejercicio1.Service;

import java.util.List;

import Ejercicio1.Models.Pelicula;
import Ejercicio1.Repository.PeliculaRepositorio;

public class PeliculaService {

    private final PeliculaRepositorio peliculaRepositorio; // Al ser final, tiene que quedar inicializado sí o sí antes de que termine de construirse el objeto PeliculaService

    public PeliculaService() {						// Hay dos constructores (sobrecarga de constructores) para ofrecer dos formas distintas de crear el servicio según lo que necesite en cada momento
        this.peliculaRepositorio = new PeliculaRepositorio();
    }

    public PeliculaService(PeliculaRepositorio peliculaRepositorio) {  // Recibe el repositorio desde fuera
        this.peliculaRepositorio = peliculaRepositorio; 
    }

    public List<Pelicula> obtenerPeliculas(String nombreFichero) {
        peliculaRepositorio.cargarDesdeFichero(nombreFichero);   					// Carga fichero
        List<Pelicula> peliculas = peliculaRepositorio.getListaPeliculas(); 		// Obtiene la lista que returnea la funcion y la guarda en una variable 
        return peliculas;
    }

    public List<Pelicula> listarPeliculasEnMemoria() {   // No lee ni carga nada, obtiene la lista que se había creado antes en vez de crear una nueva con obtenerPeliculas()
        return peliculaRepositorio.getListaPeliculas();
    }
}