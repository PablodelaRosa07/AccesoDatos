package Ejercicio1.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Pelicula {

    private String titulo;
    private String duracion;
    private String genero;
    private String sinopsis;
    private String fecha;
    private String director;
    private List<String> listaActores;

    public Pelicula() {
        this.listaActores = new ArrayList<String>();
    }

    public Pelicula(String titulo, String fecha, String director, List<String> listaActores) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.director = director;
        if (listaActores != null) {
            this.listaActores = listaActores;
        } else {
            this.listaActores = new ArrayList<String>();
        }
    }

    public Pelicula(String titulo, String duracion, String genero, String sinopsis, String fecha, String director, List<String> listaActores) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.sinopsis = sinopsis;
        this.fecha = fecha;
        this.director = director;
        if (listaActores != null) {
            this.listaActores = listaActores;
        } else {
            this.listaActores = new ArrayList<String>();
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getListaActores() {
        return listaActores;
    }

    public void setListaActores(List<String> listaActores) {
        this.listaActores = listaActores;
    }

    @Override
    public int hashCode() {
        return Objects.hash(director, duracion, fecha, genero, listaActores, sinopsis, titulo);
    }

    @Override
    public boolean equals(Object obj) {
        boolean iguales = false;
        if (this == obj) {
            iguales = true;
        } else if (obj != null && getClass() == obj.getClass()) {
            Pelicula other = (Pelicula) obj;
            iguales = Objects.equals(director, other.director)
                    && Objects.equals(duracion, other.duracion)
                    && Objects.equals(fecha, other.fecha)
                    && Objects.equals(genero, other.genero)
                    && Objects.equals(listaActores, other.listaActores)
                    && Objects.equals(sinopsis, other.sinopsis)
                    && Objects.equals(titulo, other.titulo);
        }
        return iguales;
    }

    @Override
    public String toString() {
        return "Pelicula [titulo=" + titulo + ", duracion=" + duracion + ", genero=" + genero + ", sinopsis=" + sinopsis
                + ", fecha=" + fecha + ", director=" + director + ", listaActores=" + listaActores + "]";
    }
}
