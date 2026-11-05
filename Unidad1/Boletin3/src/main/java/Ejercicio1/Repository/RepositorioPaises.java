package Ejercicio1.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Ejercicio1.Models.Pais;

public class RepositorioPaises {
	public static void main(String[] args) throws IOException {
		RepositorioPaises repo = new RepositorioPaises();
		
		String ficheroRuta = args[0];
		List<Pais> listaPaises = new ArrayList<Pais>();
		
		repo.leerFichero(ficheroRuta, listaPaises);
	}
	
	
	public List<Pais> leerFichero(String ficheroRuta, List<Pais> listaPaises) throws IOException{
		FileReader fichero = new FileReader(ficheroRuta);
		Scanner sc = null;
		List<String> listaLineas = new ArrayList<String>();
		
		try {
			sc = new Scanner(fichero);
			
			while (sc.hasNext()) {
				String linea = sc.nextLine();
				listaLineas.add(linea);			
			}
		}
		
		finally {
			for (int i = 1; i < listaLineas.size(); i++) {
				String[] tablaSeparada = listaLineas.get(i).split(",");
				Integer integer = Integer.parseInt(tablaSeparada[2]);
				List<String> listaIdiomas = new ArrayList<String>();

				if (tablaSeparada[i] != "Nombre") {
					if (!tablaSeparada[3].equals(tablaSeparada[-2])) {
						int contador = 3;
						while (!tablaSeparada[contador].equals(tablaSeparada[-2])) {
							listaIdiomas.add(tablaSeparada[contador]);
							contador++;
						}
					}
					else {
						Pais pais = new Pais(tablaSeparada[0], tablaSeparada[1], integer, listaIdiomas, tablaSeparada[4]);
						listaPaises.add(pais);
					}
				}
				
				
			}
		}
		return listaPaises;
	}
}
