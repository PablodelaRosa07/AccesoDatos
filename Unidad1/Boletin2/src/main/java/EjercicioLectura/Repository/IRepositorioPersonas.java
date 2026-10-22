package EjercicioLectura.Repository;

import java.io.FileNotFoundException;
import java.util.List;

import EjercicioLectura.Exceptions.RepositorioException;
import EjercicioLectura.Models.Persona;

public interface IRepositorioPersonas {
	   List<Persona> findAll() throws RepositorioException, FileNotFoundException;	   
	   
	   /*Persona findById(String nombre) throws RepositorioException;
	   boolean existsById(String nombre) throws RepositorioException;
	   int count() throws RepositorioException;
	   void save(Persona persona) throws RepositorioException;         
	   boolean deleteById(String nombre) throws RepositorioException;
	   public List<Persona> getPersonas();*/
	}
