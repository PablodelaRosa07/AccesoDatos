package Todo;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GestionDirectorioArchivos {
	private static final Logger logger = LogManager.getLogger(GestionDirectorioArchivos.class);

    public static void main(String[] args) {
        GestionDirectorioArchivos gestor = new GestionDirectorioArchivos();
        gestor.ejecutarSecuencia();
    }


    private void crearFichero(File fichero) {
        try {
            if (fichero.createNewFile()) {
                logger.info("Fichero '" + fichero.getName() + "' creado correctamente.");
            } else {
                logger.info("El fichero '" + fichero.getName() + "' ya existía.");
            }
        } catch (Exception e) {
            logger.error("Error al intentar crear " + fichero.getName() + ": " + e.getMessage());
        }
    }

    private void mostrarPermisos(File fichero) {
        if (fichero.exists()) {
            logger.info("Fichero: " + fichero.getName() + " | Lectura: " + fichero.canRead() + " | Escritura: " + fichero.canWrite() + " | Ejecución: " + fichero.canExecute());
        } else {
            logger.warn("No se pueden comprobar permisos. El fichero no existe: " + fichero.getName());
        }
    }

    private void mostrarContenido(File directorio) {
        File[] ficheros = directorio.listFiles();
        if (ficheros != null && ficheros.length > 0) {
            for (File fichero : ficheros) {
                logger.info("Fichero: " +fichero);
            }
        } else {
            logger.info("El directorio está vacío.");
        }
    }
    
    
    
    public void ejecutarSecuencia() {
        String userHome = System.getProperty("user.home");
        File miDirectorio = new File(userHome, "miDirectorio");

        if (miDirectorio.mkdir()) {
            logger.info("Directorio 'miDirectorio' creado correctamente en: " + miDirectorio.getAbsolutePath());
        }
        else if (miDirectorio.exists()) {
            logger.info("El directorio 'miDirectorio' ya existía.");
        } 
        else {
            logger.error("No se pudo crear el directorio 'miDirectorio'.");
        }

            
        File lectura = new File(miDirectorio, "lectura.txt");
        File normal = new File(miDirectorio, "normal.txt");

        
        crearFichero(lectura);
        crearFichero(normal);

        
        
        if (lectura.setReadOnly()) {
            logger.info("Fichero 'lectura.txt' marcado como solo lectura.");
        } 
        else {
            logger.error("No se pudo marcar 'lectura.txt' como solo lectura.");
        }

        
        
        logger.info("Permisos de los ficheros");
        mostrarPermisos(lectura);
        mostrarPermisos(normal);

        
        
        File renombrado = new File(miDirectorio, "renombrado.txt");
        if (normal.renameTo(renombrado)) {
            logger.info("Fichero 'normal.txt' renombrado exitosamente a 'renombrado.txt'.");
        } 
        else {
            logger.error("No se pudo renombrar 'normal.txt'.");
        }

        if (lectura.delete()) {
            logger.info("Fichero 'lectura.txt' eliminado directamente.");
        } 
        else {
            logger.info("No se pudo eliminar 'lectura.txt'. Quitando marca de solo lectura.");
            if (lectura.setWritable(true)) {
                logger.info("Permiso de escritura restaurado para 'lectura.txt'.");
                if (lectura.delete()) {
                    logger.info("Fichero 'lectura.txt' eliminado.");
                } 
                else {
                    logger.error("No se pudo eliminar 'lectura.txt'.");
                }
            } else {
                logger.error("No se pudieron cambiar los permisos de 'lectura.txt'.");
            }
        }


        logger.info("Contenido final de miDirectorio");
        mostrarContenido(miDirectorio);
        }
   
}

