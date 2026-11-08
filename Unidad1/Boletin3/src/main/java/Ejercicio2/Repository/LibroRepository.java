package Ejercicio2.Repository;

import Ejercicio2.Models.Libro;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibroRepository {
    private List<Libro> baseDatosMemoria;

    public LibroRepository() {
        this.baseDatosMemoria = new ArrayList<>();
    }

    public List<Libro> leerFicherosJson(String rutaLibros, String rutaAutores) {
        Gson gson = new Gson();
        List<Libro> listaLibros = new ArrayList<>();
        List<Libro> listaAutoresLibros = new ArrayList<>();

        try (FileReader readerLibros = new FileReader(rutaLibros)) {
            Libro[] arrayLibros = gson.fromJson(readerLibros, Libro[].class);
            if (arrayLibros != null) {
                listaLibros = new ArrayList<>(Arrays.asList(arrayLibros));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader readerAutores = new FileReader(rutaAutores)) {
            Libro[] arrayAutoresLibros = gson.fromJson(readerAutores, Libro[].class);
            if (arrayAutoresLibros != null) {
                listaAutoresLibros = Arrays.asList(arrayAutoresLibros);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Cruzamos los datos usando el ISBN
        for (Libro libro : listaLibros) {
            for (Libro aux : listaAutoresLibros) {
                if (aux.getIsbn() != null && aux.getIsbn().equals(libro.getIsbn())) {
                    if (aux.getAutor() != null) {
                        libro.setAutor(aux.getAutor());
                    }
                    if ((libro.getEditorial() == null || libro.getEditorial().trim().isEmpty()) && aux.getEditorial() != null) {
                        libro.setEditorial(aux.getEditorial());
                    }
                }
            }
        }

        // Devolvemos la lista leída para que el servicio la procese y guarde ordenadamente
        List<Libro> resultado = listaLibros;
        return resultado; // Único return
    }

    public void guardar(Libro libro) {
        baseDatosMemoria.add(libro);
    }

    public boolean eliminarPorIsbn(String isbn) {
        boolean eliminado = false;
        Libro libroEncontrado = null;

        for (Libro libro : baseDatosMemoria) {
            if (libro.getIsbn() != null && libro.getIsbn().equals(isbn)) {
                libroEncontrado = libro;
            }
        }

        if (libroEncontrado != null) {
            baseDatosMemoria.remove(libroEncontrado);
            eliminado = true;
        }

        return eliminado; // Único return
    }

    public List<Libro> obtenerPorPais(String pais) {
        List<Libro> librosFiltrados = new ArrayList<>();

        if (pais != null) {
            for (Libro libro : baseDatosMemoria) {
                if (libro.getAutor() != null && libro.getAutor().getPais() != null) {
                    if (libro.getAutor().getPais().equalsIgnoreCase(pais)) {
                        librosFiltrados.add(libro);
                    }
                }
            }
        }

        return librosFiltrados; // Único return
    }

    public List<Libro> obtenerTodos() {
        List<Libro> resultado = baseDatosMemoria;
        return resultado; // Único return
    }
}