package Service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exception.VeterinariaException;
import Models.Especie;
import Models.Mascota;
import Models.Propietario;
import Repository.RepositorioMascotas;



public class ServicioMascota {

	private static final Logger logger = LogManager.getLogger(ServicioMascota.class);
	private final RepositorioMascotas repositorioMascotas;
	
	public ServicioMascota() {
		this.repositorioMascotas = new RepositorioMascotas();
	}
	
	public ServicioMascota(RepositorioMascotas repositorioMascotas) {
		super();
		this.repositorioMascotas = new RepositorioMascotas();
	}
	
	
	
	
	
	public List<Mascota> cargarMascotasDesdeXML(String rutaFichero) throws VeterinariaException {
		repositorioMascotas.cargarDesdeFichero(rutaFichero);
		return repositorioMascotas.getListaMascotas();
	}

	public boolean guardarMascotasEnXML(String rutaFichero) {
		return repositorioMascotas.guardarEnFichero(rutaFichero);
	}

	public boolean registrarMascota(int id, String nombre, Especie especie, Propietario propietario) throws VeterinariaException {
		boolean registrado = false;
		try {
			Mascota mascota = new Mascota(id, nombre, especie, propietario);
			repositorioMascotas.agregarMascota(mascota);
			registrado = true;
		} catch (IllegalArgumentException e) {
			logger.error("No se ha podido crear o registrar la mascota: " + e.getMessage(), e);
		}
		return registrado;
	}

	public List<Mascota> listarMascota() {
		return repositorioMascotas.getListaMascotas();
	}
	
}
