package Ejercicio1.Repository;

import java.util.ArrayList;
import java.util.List;


import Ejercicio1.Models.Pelicula;
import Ejercicio1.Service.XMLDomPeliculas;



public class PeliculasRepo {

    private final XMLDomPeliculas xmlDomPeliculas;

    public PeliculasRepo() {
        this.xmlDomPeliculas = new XMLDomPeliculas();
    }

    public PeliculasRepo(XMLDomPeliculas xmlDomPeliculas) {
        this.xmlDomPeliculas = xmlDomPeliculas;
    }

    public List<Pelicula> obtenerPeliculasDesdeFichero(String nombreFichero) {
        List<Pelicula> resultado = new ArrayList<>();
        if (nombreFichero != null) {
        	resultado = xmlDomPeliculas.cargarPeliculas(nombreFichero);
        }
        return resultado;
    }
}
