
import java.io.File;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class UtilidadesDirectorio {

	private static final Logger logger = LogManager.getLogger(UtilidadesDirectorio.class);
	
	
	
	public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Introduce la ruta del directorio que deseas borrar: ");
        String ruta = scanner.nextLine();
        
        File directorioAborrar = new File(ruta);
        
        borrarDirectorio(directorioAborrar);
        
        scanner.close();
    }
	
	
	
    private static int contarElementos(File directorio) {
        int contador = 0;
        File[] archivos = directorio.listFiles();
        
        if (archivos != null) {
            for (File archivo : archivos) {
                contador++; 
                if (archivo.isDirectory()) {
                    contador += contarElementos(archivo);
                }
            }
        }
        return contador;
    }


    public static boolean borrarDirectorio(File dir) throws Exception {
        if (!dir.exists() || !dir.isDirectory()) {
            throw new Exception("La ruta no es válida");
        }

        int totalElementos = contarElementos(dir);
        logger.info("Se van a eliminar un total de " + totalElementos + " elementos (entre ficheros y carpetas).");

        System.out.print("¿Estás seguro de que deseas continuar con el borrado? (s/n): ");
        Scanner scanner = new Scanner(System.in);
        String respuesta = scanner.nextLine().trim().toLowerCase();

        if (!respuesta.equals("s") && !respuesta.equals("si") && !respuesta.equals("sí")) {
            throw new Exception("Operación cancelada pr el usuario");
        }

        boolean exito = ejecutarBorradoRecursivo(dir);
        if (exito) {
            logger.info("El directorio y su contenido se han borrado correctamente.");
        } else {
            logger.info("Hubo un error al intentar borrar algunos elementos.");
        }
        
        return exito;
    }


    public static boolean ejecutarBorradoRecursivo(File dir) {
        File[] archivos = dir.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    ejecutarBorradoRecursivo(archivo);
                } else {
                    archivo.delete();
                }
            }
        }
        return dir.delete();
    }
}