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
        cargarDesdeFichero(nombreFichero);
    }

    public void cargarDesdeFichero(String nombreFichero) {
        if (nombreFichero != null) {
            this.listaPeliculas = xmlDomPeliculas.cargarPeliculas(nombreFichero);
        }
    }

    public List<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }

    public void setListaPeliculas(List<Pelicula> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }

    public void agregarPelicula(Pelicula pelicula) {
        if (pelicula != null) {
            this.listaPeliculas.add(pelicula);
        }
    }
}
