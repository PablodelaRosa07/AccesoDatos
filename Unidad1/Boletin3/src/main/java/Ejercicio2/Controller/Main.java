package Ejercicio2.Controller;

import Ejercicio2.Repository.LibroRepository;
import Ejercicio2.Service.LibroService;

public class Main {
    public static void main(String[] args) {
        // Rutas de tus dos ficheros JSON
        String rutaJsonLibros = args[0];
        String rutaJsonAutores = args[1];
        
        String paisBusqueda = "Estados Unidos";
        String rutaCsv = "libros_eeuu.csv";

        LibroRepository repositorio = new LibroRepository();
        LibroService servicio = new LibroService(repositorio);
        LibroController controlador = new LibroController(servicio);

        controlador.gestionarCargaYListado(rutaJsonLibros, rutaJsonAutores, paisBusqueda, rutaCsv);
    }
}
