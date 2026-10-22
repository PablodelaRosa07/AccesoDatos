package EjercicioLectura.Controller;

import java.io.FileNotFoundException;

import EjercicioLectura.Exceptions.RepositorioException;
import EjercicioLectura.Repository.RepositorioPersonasTxt;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, RepositorioException {
		RepositorioPersonasTxt repo = new RepositorioPersonasTxt("src/main/resources/Fichero2.txt");
		repo.findAll();
		System.out.println(repo.getPersonas());
	}
}
