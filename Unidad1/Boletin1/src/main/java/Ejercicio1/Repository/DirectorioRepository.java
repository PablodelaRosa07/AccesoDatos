package Repository;

import java.io.File;

import Exceptions.RutaNoValidaException;
import Models.EstadisticasDirectorio;

public class DirectorioRepository {

    public File[] obtenerElementosDirectorio(String ruta) throws RutaNoValidaException {
        File directorio = new File(ruta);

        if (!directorio.exists()) {
            throw new RutaNoValidaException("La ruta especificada no existe.");
        }

        if (!directorio.isDirectory()) {
            throw new RutaNoValidaException("La ruta especificada existe, pero no es un directorio.");
        }

        File[] elementos = directorio.listFiles();

        if (elementos == null) {
            throw new RutaNoValidaException("No se tienen permisos de lectura para acceder al directorio.");
        }

        return elementos;
    }

    public EstadisticasDirectorio procesarYMostrarElementos(File[] elementos) {
        EstadisticasDirectorio stats = new EstadisticasDirectorio();

        System.out.println("Contenido del directorio:");

        for (File elemento : elementos) {
            if (elemento.isFile()) {
                System.out.println("[F] " + elemento.getName());
                stats.incrementarFicheros();
            } else if (elemento.isDirectory()) {
                System.out.println("[D] " + elemento.getName());
                stats.incrementarDirectorios();
            }
        }


        return stats;
    }
}