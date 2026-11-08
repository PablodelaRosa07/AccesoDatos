package Ejercicio1.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Ejercicio1.Models.Pais;
import Ejercicio1.Repository.RepositorioPaises;

public class PaisController {
    private static final Logger logger = LogManager.getLogger(PaisController.class);
    
    public static void main(String[] args) {
    	if (args.length == 0) {
    	    System.err.println("Por favor, introduce la ruta del fichero como argumento.");
    	}
    	
        String ficheroRuta = args[0];
        List<Pais> listaPaises = new ArrayList<>();
        
        RepositorioPaises repo = new RepositorioPaises();
        PaisController controller = new PaisController();
        
        try (Scanner sc = new Scanner(System.in)) {
            // 1. Leer del fichero
            repo.leerFichero(ficheroRuta, listaPaises);
            
            // 2. Gestionar nuevo país y guardar cambios en CSV
            controller.gestionarNuevoPais(listaPaises, sc);
            repo.guardarFichero(ficheroRuta, listaPaises);
            
            logger.info("Lista actualizada de países:");
            logger.info(listaPaises);
            
            // 3. Buscar país
            controller.devuelvePais(listaPaises, sc);
            
            // 4. Añadir idiomas y guardar cambios en CSV
            controller.añadirIdiomas(listaPaises, sc);
            repo.guardarFichero(ficheroRuta, listaPaises);
            
            logger.info("Lista actualizada de países:");
            logger.info(listaPaises);
            
            // 5. Borrar país y guardar cambios en CSV
            controller.borrarPais(listaPaises, sc);
            repo.guardarFichero(ficheroRuta, listaPaises);
            
            logger.info("Lista actualizada de países:");
            logger.info(listaPaises);
            
            
            // 2. Exportar países de un continente a JSON
            controller.exportarContinenteJson(listaPaises, repo, sc);
            
        } catch (IOException e) {
            logger.error("Error crítico al procesar el fichero: " + e.getMessage());
        }
        
    }

    public void gestionarNuevoPais(List<Pais> listaPaises, Scanner sc) {
        List<String> listaIdiomas = new ArrayList<>();
        
        System.out.println("Introduce nombre del país: ");
        String nombre = sc.nextLine().trim();
        
        System.out.println("Introduce continente del país: ");
        String continente = sc.nextLine().trim();
        
        System.out.println("Introduce población del país: ");
        double poblacion = sc.nextDouble();
        sc.nextLine(); // Limpiar el buffer de entrada
        
        System.out.println("Introduce idiomas del país separados por espacio: ");
        String idiomas = sc.nextLine();
        listaIdiomas.addAll(Arrays.asList(idiomas.split(" ")));
        
        System.out.println("Introduce moneda del país: ");
        String moneda = sc.nextLine().trim();
        
        // VALIDACIÓN: No se pueden añadir países sin su nombre y su continente
        if (nombre.isEmpty() || continente.isEmpty()) {
            logger.error("Los datos no son válidos: El nombre y el continente son obligatorios.");
        } else {
            Pais nuevoPais = new Pais(nombre, continente, poblacion, listaIdiomas, moneda);
            listaPaises.add(nuevoPais);
            System.out.println("País añadido con éxito");
        }
    }
    
    public void devuelvePais(List<Pais> listaPaises, Scanner sc) {
		System.out.println("Introduce nombre del pais a buscar: ");
		String nombre = sc.nextLine().trim();
		
		for (Pais pais : listaPaises) {
			if (nombre.equalsIgnoreCase(pais.getNombre())){
				logger.info("País encontrado: " + pais.getNombre() + " | Continente: " + pais.getContinente() + " | Población: " + pais.getPoblacion() + " | Idioma/s: " + pais.getIdiomas() + " | Moneda: " + pais.getMoneda());
			}
		}
	}
    
    public List<Pais> añadirIdiomas(List<Pais> listaPaises, Scanner sc) {
    	System.out.println("Introduce nombre del pais al que añadir idioma: ");
		String nombre = sc.nextLine().trim();
    	
    	System.out.println("Introduce idioma nuevo: ");
		String idioma = sc.nextLine().trim();
		
		for (Pais pais : listaPaises) {
			if (nombre.equalsIgnoreCase(pais.getNombre())){
				pais.getIdiomas().add(idioma);
				logger.info("Idioma añadido. País actualizado: " + pais.getNombre() + " | Idiomas: " + pais.getIdiomas());
			}
		}
		return listaPaises;
    }
    
    public List<Pais> borrarPais(List<Pais> listaPaises, Scanner sc) {
    	System.out.println("Introduce nombre del pais a borrar: ");
		String nombre = sc.nextLine().trim();
		
		listaPaises.removeIf(pais -> nombre.equalsIgnoreCase(pais.getNombre()));
		System.out.println("Operación de borrado finalizada.");
		
		return listaPaises;
    }
    
    public void exportarContinenteJson(List<Pais> listaPaises, RepositorioPaises repo, Scanner sc) {
        System.out.println("Introduce el nombre del continente para exportar a JSON (ej. América, Europa): ");
        String continente = sc.nextLine().trim();
        
        try {
            repo.exportarPaisesPorContinente(continente, listaPaises);
            System.out.println("¡Exportación a JSON finalizada con éxito!");
        } catch (IOException e) {
            System.out.println("Hubo un error al exportar el fichero JSON. Revisa los logs.");
        }
    }
}