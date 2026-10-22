package EjercicioLectura;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Principal {
	private static final Logger logger = LogManager.getLogger(Principal.class);


	public static void main(String[] args) {
        String nombreFichero = "Fichero1.txt";
        
        Principal principal = new Principal();	
        Persona p1 = new Persona("Pepe");
        
        List<Persona> personas = new ArrayList<>();
        personas.add(p1);
        
        
        
        System.out.println(personas);
    }
	
	 private static void crearFicheroEjemplo(String rutaFichero, Persona persona) {
	        try (FileWriter escritor = new FileWriter(rutaFichero)) {
	            escritor.write("Pepe 1.0 3.5 7.7\n");
	            persona.agregarNota(1);;
	            persona.agregarNota(3.5);
	            persona.agregarNota(7.7);
	            escritor.write("Marcos 2\n");
	            persona.agregarNota(2);
	            escritor.write("Candela 10.0 11.1\n");
	            persona.agregarNota(10);
	            persona.agregarNota(11.1);
	        } catch (IOException e) {
	            System.err.println("Error al crear el fichero: " + e.getMessage());
	        }
	    }

	  private static List<Persona> cargarDatosDesdeFichero(String rutaFichero) {
	        List<Persona> personas = new ArrayList<>();
	        
	        try (BufferedReader br = new BufferedReader(new FileReader(rutaFichero))) {
	            String linea;
	            
	            while ((linea = br.readLine()) != null) {
	                linea = linea.trim();
	                if (linea.isEmpty()) continue;

	                String[] partes = linea.split(" ");
	                
	                Persona persona = new Persona(partes[0], null);
	                
	                for (int i = 1; i < partes.length; i++) {
	                    persona.agregarNota(Double.parseDouble(partes[i]));
	                }
	                
	                personas.add(persona);
	            }
	        } catch (IOException e) {
	            System.err.println("Error al leer el archivo: " + e.getMessage());
	        } catch (NumberFormatException e) {
	            System.err.println("Error de formato numérico: " + e.getMessage());
	        }
	        
	        return personas;
	    }

	    
	    
	    
	    
	
	
}
		

	
		
