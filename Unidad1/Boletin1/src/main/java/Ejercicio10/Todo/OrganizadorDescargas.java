import java.io.File;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrganizadorDescargas {

    private static final Logger logger = LogManager.getLogger(OrganizadorDescargas.class);
    
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Introduce la ruta del directorio que deseas organizar: ");
        String ruta = scanner.nextLine();
        
        File directorioAOrganizar = new File(ruta);
        
        organizarDirectorio(directorioAOrganizar);
        
        scanner.close();
    }

    public static void organizarDirectorio(File directorioBase) throws Exception {
        if (!directorioBase.exists() || !directorioBase.isDirectory()) {
            throw new Exception("La ruta no es válida o no es un directorio");
        }

        File[] archivos = directorioBase.listFiles(File::isFile);

        if (archivos == null || archivos.length == 0) {
            logger.info("El directorio está vacío o no contiene ficheros para organizar.");
        }

        logger.info("Organizando " + archivos.length + " ficheros...");

        for (File archivo : archivos) {
            String nombreArchivo = archivo.getName();
            String extension = obtenerExtension(nombreArchivo);

            if (extension.isEmpty()) {
                extension = "sin_extension";
            }

            File subcarpeta = new File(directorioBase, extension);
            if (!subcarpeta.exists()) {
                boolean creada = subcarpeta.mkdir();
                if (!creada) {
                    logger.info("No se pudo crear la subcarpeta para: " + extension);
                }
            }

            File archivoDestino = obtenerArchivoUnico(subcarpeta, nombreArchivo);

            boolean movido = archivo.renameTo(archivoDestino);
            if (movido) {
                logger.info("Movido: " + nombreArchivo + " -> " + extension + "/" + archivoDestino.getName());
            } else {
                logger.info("Error al mover el fichero: " + nombreArchivo);
            }
        }

        logger.info("¡Organización completada!");
    }

    private static String obtenerExtension(String nombreArchivo) {
        int ultimoPunto = nombreArchivo.lastIndexOf('.');
        if (ultimoPunto > 0 && ultimoPunto < nombreArchivo.length() - 1) {
            return nombreArchivo.substring(ultimoPunto + 1).toLowerCase();
        }
        return "";
    }

    private static File obtenerArchivoUnico(File carpetaDestino, String nombreOriginal) {
        File destino = new File(carpetaDestino, nombreOriginal);
        
        if (!destino.exists()) {
            return destino;
        }

        int ultimoPunto = nombreOriginal.lastIndexOf('.');
        String nombreBase;
        String extension;

        if (ultimoPunto > 0) {
            nombreBase = nombreOriginal.substring(0, ultimoPunto);
            extension = nombreOriginal.substring(ultimoPunto);
        } else {
            nombreBase = nombreOriginal;
            extension = "";
        }

        int contador = 1;
        while (destino.exists()) {
            String nuevoNombre = nombreBase + "(" + contador + ")" + extension;
            destino = new File(carpetaDestino, nuevoNombre);
            contador++;
        }

        return destino;
    }
}