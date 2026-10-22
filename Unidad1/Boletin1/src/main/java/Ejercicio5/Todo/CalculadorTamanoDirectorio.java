package Todo;

import java.io.File;



import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CalculadorTamanoDirectorio {
	
	private static final Logger logger = LogManager.getLogger(CalculadorTamanoDirectorio.class);

	
	
	public static void main(String[] args) {
	    CalculadorTamanoDirectorio calculador = new CalculadorTamanoDirectorio();
	    
	    File ruta = calculador.introducirRuta();

	    
	    double bytesTotales = calculador.calcularTamano(ruta);
	    calculador.formatearTamano(bytesTotales);
	    }
	
	
	
	
	
	public File introducirRuta() {
		Scanner sc = new Scanner(System.in);
		logger.info("Introduce la ruta: ");
		String archivo = sc.next();
		File directorio = new File(archivo);
		
		if (directorio.exists()) {
			logger.info("Entrando en la ruta.");
		}
		else {
			logger.info("No existe la ruta.");
		}
		return directorio;
	}
	
	
	public double calcularTamano(File directorio) {
        double totalBytes = 0;

        File[] ficheros = directorio.listFiles();
        if (ficheros != null) {
            for (File fichero : ficheros) {
                if (fichero.isFile()) {
                    totalBytes += fichero.length();
                } 
                else if (fichero.isDirectory()) {
                    totalBytes += calcularTamano(fichero); 
                }
            }
        }

        return totalBytes;
    }
	
	
	
	public void formatearTamano(double bytes) {
	    double kb = bytes / 1024.0;
	    double mb = kb / 1024.0;
	    logger.info("MB: " +mb+ " | KB: " +kb+ " | B: " +bytes);

	}
}
