package Controller;

import Exceptions.RutaNoValidaException;
import Models.EstadisticasDirectorio;
import Repository.DirectorioRepository;

import java.io.File;
import java.util.Scanner;

public class GestionaDirectorio {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DirectorioRepository repository = new DirectorioRepository();

        System.out.print("Introduce la ruta de un directorio: ");
        String rutaIngresada = scanner.nextLine();

        try {
            File[] elementos = repository.obtenerElementosDirectorio(rutaIngresada);
            EstadisticasDirectorio stats = repository.procesarYMostrarElementos(elementos);
            System.out.println(stats);
        } catch (RutaNoValidaException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}