package Ejercicio2.Models;

public class Libro {
    private String isbn;
    private String titulo;
    private String editorial;
    private int numero_ediciones;
    private Autor autor; 

    public Libro() {}

    public String getIsbn() {
        String resultadoIsbn = isbn;
        return resultadoIsbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        String resultadoTitulo = titulo;
        return resultadoTitulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        String resultadoEditorial = editorial;
        return resultadoEditorial;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getNumero_ediciones() {
        int resultadoEdiciones = numero_ediciones;
        return resultadoEdiciones;
    }
    public void setNumero_ediciones(int numero_ediciones) {
        this.numero_ediciones = numero_ediciones;
    }

    public Autor getAutor() {
        Autor resultadoAutor = autor;
        return resultadoAutor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}