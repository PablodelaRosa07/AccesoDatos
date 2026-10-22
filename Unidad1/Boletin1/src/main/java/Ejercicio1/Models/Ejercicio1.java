package Models;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ejercicio1 {
	
	
	private static final Logger logger = LogManager.getLogger(Ejercicio1.class);
	Scanner sc = new Scanner(System.in);
    

    public void crearFichero() {
    	
    	System.out.println("Escribe la ruta: ");
        String directorio = sc.next();
        
        try {
            boolean buscado = directorio.contains();
            if (creado) {
                logger.info("Fichero creado con éxito.");
            } else {
                logger.info("El fichero ya existía.");
            }
        } catch (IOException e) {
            logger.error("Error al crear fichero: " + e.getMessage());
        }
    }
    
    
    public static void main(String[] args) {
		Ejercicio1 ejercicio1 = new Ejercicio1();
		ejercicio1.crearFichero();
	}

	
}
