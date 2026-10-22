package Ejercicio1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcesadorFichero {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		ProcesadorFichero procesadorFichero = new ProcesadorFichero();
		List<String> listaPalabras = new ArrayList<>();
        String[] palabras = { "servicio", "hilo", "proceso", "multihilo", "concurrencia" };

		
	    String nombreFicheroEntrada = "src/main/resources/Fichero.txt";
	    String palabra = null;
	    
	    procesadorFichero.contarPalabras(nombreFicheroEntrada, palabra, listaPalabras);
	    for (int i = 0; i < palabras.length; i++) {
	    	palabra = args[i];
		    System.out.println("Número de '"+listaPalabras.get(i)+"': " +listaPalabras.size());
		}
	    
	}
	
	
	public List<String> contarPalabras(String nombreFichero, String palabra, List<String> listaPalabras) throws FileNotFoundException{
		Scanner sc = null;
		try {
            FileReader fichero = new FileReader(nombreFichero);
            sc = new Scanner(fichero);
            
            while (sc.hasNext()) {
                String palabras = sc.next();
                listaPalabras.add(palabra);
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        
        return listaPalabras;
	}


}
