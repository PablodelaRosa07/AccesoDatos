import java.io.File;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	
	private static final Logger logger = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		Main main = new Main();
		
		
		
		String ruta = main.introducirRuta();
		main.listarDirectorio(ruta);
		
	}
	
	
	public String introducirRuta() {
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
		return archivo;
	}
	
	
	public void listarDirectorio(String directorio) {
		File archivo = new File(directorio);
		File[]contenido = archivo.listFiles();
		
		for (File fichero : contenido) {
			if (fichero.isDirectory()) {
				Main directorioCarpeta = new Main();
				String directorioNuevo = fichero.getPath();
				directorioCarpeta.listarDirectorio(directorioNuevo);
			}
			else if (fichero.isFile()) {
				logger.info("Archivo: " +fichero);
			}
		}
	}
}
