package Ejercicio1.Repository;

import java.util.ArrayList;
import java.util.List;


import Ejercicio1.Models.Pelicula;
import Ejercicio1.Service.XMLDomPeliculas;



public class PeliculaRepositorio {

    private List<Pelicula> listaPeliculas;
    private final XMLDomPeliculas xmlDomPeliculas;				

    public PeliculaRepositorio() {
        this.listaPeliculas = new ArrayList<Pelicula>();
        this.xmlDomPeliculas = new XMLDomPeliculas();
    }

    public PeliculaRepositorio(String nombreFichero) {
        this.xmlDomPeliculas = new XMLDomPeliculas();
        this.listaPeliculas = new ArrayList<Pelicula>();
        cargarDesdeFichero(nombreFichero);  // Si lo hago aquí, cuando creo un Objeto "PeliculaRepositorio", carga el fichero directamente (hace la función) y no tengo que llamarlo después.
    }

    public List<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

    public void setListaPeliculas(List<Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }

    
    
    public void agregarPelicula(Pelicula pelicula) {   // Funcion que no siempre se repite, por eso está en el repo y no en el Utiles.
        if (pelicula != null) {
            this.listaPeliculas.add(pelicula);
        }
    }
    
    public void cargarDesdeFichero(String nombreFichero) {   // Se inicia al mismo tiempo que el objeto "PeliculaRepositorio"
        if (nombreFichero != null) {
            this.listaPeliculas = xmlDomPeliculas.cargarPeliculas(nombreFichero);    // Actualiza this.listaPeliculas asignándole la lista de objetos Pelicula que devuelve cargarPeliculas(...)
        }
    }
}
