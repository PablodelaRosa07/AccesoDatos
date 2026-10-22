package EjercicioLectura.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import EjercicioLectura.Exceptions.RepositorioException;
import EjercicioLectura.Models.Persona;

public class RepositorioPersonasTxt implements IRepositorioPersonas{
	
	
	private String separador;
	private String fichero;
	private List<Persona> personas;
	

	public RepositorioPersonasTxt(String separador, String fichero, List<Persona> personas) {
		super();
		this.separador = separador;
		this.fichero = fichero;
		this.personas = new ArrayList<Persona>();
	}
	
	
	public RepositorioPersonasTxt(String fichero) {
		super();
		this.fichero = fichero;
	}

	public String getSeparador() {
		return separador;
	}

	public void setSeparador(String separador) {
		this.separador = separador;
	}

	public String getFichero() {
		return fichero;
	}

	public void setFichero(String fichero) {
		this.fichero = fichero;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}


	@Override
	public List<Persona> findAll() throws RepositorioException, FileNotFoundException {
		if (personas == null || personas.isEmpty()) {
			personas = new ArrayList<Persona>();
		
		}
		leerFichero(fichero);
		return personas;
			
	}
	
	private List<Persona> leerFichero(String fichero) throws FileNotFoundException{
		Scanner sc = null;
		try {
			FileReader fl = new FileReader(fichero);
            sc = new Scanner(fichero);
            
            while (sc.hasNext()) {
                String nombre = sc.next();
                Persona p = new Persona(nombre);
                personas.add(p);
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
		return personas;
		
	}








	
}
