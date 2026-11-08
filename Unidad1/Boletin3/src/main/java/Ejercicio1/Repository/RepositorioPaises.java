package Ejercicio1.Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Ejercicio1.Models.Pais;

public class RepositorioPaises {
	private static final Logger logger = LogManager.getLogger(RepositorioPaises.class);

	public List<Pais> leerFichero(String ficheroRuta, List<Pais> listaPaises) throws IOException {
		List<String> listaLineas = new ArrayList<>();
		
		try (BufferedReader fichero = new BufferedReader(new FileReader(ficheroRuta));
			 Scanner sc = new Scanner(fichero)) {
			
			while (sc.hasNextLine()) {
				String linea = sc.nextLine();
				listaLineas.add(linea);			
			}
		}
		
		for (int i = 1; i < listaLineas.size(); i++) {
			String[] tablaSeparada = listaLineas.get(i).split(",");
			
			if (tablaSeparada.length >= 5) {
				String nombre = tablaSeparada[0].trim();
				String continente = tablaSeparada[1].trim();
				double poblacion = Double.parseDouble(tablaSeparada[2].trim());
				
				// Soporte para múltiples idiomas separados por espacio en el CSV
				List<String> listaIdiomas = new ArrayList<>(Arrays.asList(tablaSeparada[3].trim().split(" ")));
				String moneda = tablaSeparada[4].trim();
	
				Pais pais = new Pais(nombre, continente, poblacion, listaIdiomas, moneda);
				listaPaises.add(pais);		
			}
		}
		return listaPaises;
	}

	/**
	 * Reescribe el fichero CSV completo con el estado actual de la lista de países.
	 */
	public void guardarFichero(String ficheroRuta, List<Pais> listaPaises) throws IOException {
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroRuta))) {
	        // Escribir la cabecera del CSV
	        bw.write("Nombre,Continente,Población,Idioma,Moneda");
	        bw.newLine();

	        // Escribir cada país
	        for (Pais pais : listaPaises) {
	            // Unir los idiomas de la lista separados por espacio para el CSV
	            String idiomasStr = String.join(" ", pais.getIdiomas());
	            
	            // Concatenación directa de strings sin String.format
	            String linea = pais.getNombre() + "," +
	                           pais.getContinente() + "," +
	                           pais.getPoblacion() + "," +
	                           idiomasStr + "," +
	                           pais.getMoneda();
	                           
	            bw.write(linea);
	            bw.newLine();
	        }
	        logger.info("Fichero CSV actualizado correctamente en: " + ficheroRuta);
	    } catch (IOException e) {
	        logger.error("Error al guardar el fichero CSV: " + e.getMessage());
	    }
	}
	
	public void exportarPaisesPorContinente(String continenteBuscado, List<Pais> listaPaises) throws IOException {
		List<Pais> paisesFiltrados = new ArrayList<>();
		
		for (Pais pais : listaPaises) {
			if (pais.getContinente().equalsIgnoreCase(continenteBuscado)) {
				paisesFiltrados.add(pais);
			}
		}

		String nombreFichero = continenteBuscado + ".json";

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreFichero))) {
			StringBuilder jsonBuilder = new StringBuilder();
			jsonBuilder.append("[");
			
			for (int i = 0; i < paisesFiltrados.size(); i++) {
				Pais p = paisesFiltrados.get(i);
				
				jsonBuilder.append("{\"nombre\":\"")
				           .append(p.getNombre())
				           .append("\",\"continente\":\"")
				           .append(p.getContinente())
				           .append("\",\"poblacion\":")
				           .append(p.getPoblacion())
				           .append(",\"idiomas\":[");
				
				List<String> idiomas = p.getIdiomas();
				for (int j = 0; j < idiomas.size(); j++) {
					jsonBuilder.append("\"").append(idiomas.get(j)).append("\"");
					if (j < idiomas.size() - 1) {
						jsonBuilder.append(",");
					}
				}
				
				jsonBuilder.append("],\"moneda\":\"")
				           .append(p.getMoneda())
				           .append("\"}");
				
				if (i < paisesFiltrados.size() - 1) {
					jsonBuilder.append(",");
				}
			}
			jsonBuilder.append("]");
			
			bw.write(jsonBuilder.toString());
			logger.info("Fichero JSON generado con éxito: " + nombreFichero);
		} catch (IOException e) {
			logger.error("Error al generar el fichero JSON: " + e.getMessage());
			throw e;
		}
	}
}