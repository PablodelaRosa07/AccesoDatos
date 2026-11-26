package Repository;

import java.util.ArrayList;
import java.util.List;

import Exception.VeterinariaException;
import Models.Mascota;
import Service.MascotaDAO;


public class RepositorioMascotas {

	private final MascotaDAO mascotaDAO;
	private List<Mascota> listaMascotas;
	
	
	public RepositorioMascotas() {
		super();
		this.mascotaDAO = new MascotaDAO();
		this.listaMascotas = new ArrayList<Mascota>();
	}


	public RepositorioMascotas(String rutaFichero) throws VeterinariaException {
		super();
		this.mascotaDAO = new MascotaDAO();
		this.listaMascotas = new ArrayList<Mascota>();
		cargarDesdeFichero(rutaFichero);   // Para que cargue el fichero nada más se cree el repositorio
	}

	
	public void cargarDesdeFichero(String rutaFichero) throws VeterinariaException {
		if (rutaFichero != null && !rutaFichero.trim().isEmpty()) {
			this.listaMascotas = this.mascotaDAO.findAll(rutaFichero);
		}
	}

	public boolean guardarEnFichero(String rutaFichero) {
		return this.mascotaDAO.altaMascotas(this.listaMascotas, rutaFichero);
	}

	public void agregarMascota(Mascota mascota) {
		if (mascota != null) {
			this.listaMascotas.add(mascota);
		}
	}

	public List<Mascota> getListaMascotas() {
		return this.listaMascotas;
	}

	public void setListaMascotas(List<Mascota> listaMascotas) {
		this.listaMascotas = listaMascotas;
	}
}
