package EjercicioPruebaJson.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import EjercicioPruebaJson.Models.Empleado;

public class Main {
	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		Gson gson = new Gson();
		String rutaFichero = args[0];
		Empleado empleado = new Empleado("empleado2", "Elena de la Rosa", 3, "Empresa2");

		leeEmpleados(rutaFichero);
		escribeEmpleado(empleado, rutaFichero);
	}
	
	public static void leeEmpleados(String rutaFichero) { 
	    try { 
	        Gson gson = new Gson(); 
	        FileReader fichero = new FileReader(rutaFichero); 
	        
	        Empleado empleado = gson.fromJson(fichero, Empleado.class); 
	        
	        System.out.println(empleado); 
	    } catch (Exception e) { 
	        System.out.println("Error al leer empleados: " + e.getMessage()); 
	    } 
	}
	
	public static void escribeEmpleado(Empleado empleado, String rutaFichero) {
		 Gson gson = new Gson();
		 String json = gson.toJson(empleado);
		 FileWriter fichero = null;
		 try {
			fichero = new FileWriter(rutaFichero);
			fichero.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fichero != null) {
				try {
					fichero.close();
				} 
				catch (IOException e) {
					System.out.println("Error al escribir empleado");
				}			
			}		
		}	   
	}


	
	
}
