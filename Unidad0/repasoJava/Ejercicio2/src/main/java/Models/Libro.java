package Models;

import java.util.Objects;

public class Libro {
    private String id;
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private Genero genero;
    private int ejemplaresDisponibles;

    public Libro(String id, String titulo, String autor, int anioPublicacion, Genero genero, int ejemplaresDisponibles) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
        this.ejemplaresDisponibles = ejemplaresDisponibles;
    }


    public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getAutor() {
		return autor;
	}



	public void setAutor(String autor) {
		this.autor = autor;
	}



	public int getAnioPublicacion() {
		return anioPublicacion;
	}



	public void setAnioPublicacion(int anioPublicacion) {
		this.anioPublicacion = anioPublicacion;
	}



	public Genero getGenero() {
		return genero;
	}



	public void setGenero(Genero genero) {
		this.genero = genero;
	}



	public int getEjemplaresDisponibles() {
		return ejemplaresDisponibles;
	}



	public void setEjemplaresDisponibles(int ejemplaresDisponibles) {
		this.ejemplaresDisponibles = ejemplaresDisponibles;
	}



	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(id, libro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s (%d) | Género: %s | Disponibles: %d",
                id, titulo, autor, anioPublicacion, genero, ejemplaresDisponibles);
    }
}