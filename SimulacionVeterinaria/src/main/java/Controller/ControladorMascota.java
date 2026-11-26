package Controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exception.VeterinariaException;
import Models.Mascota;
import Service.ServicioMascota;


public class ControladorMascota {

	private static final Logger logger = LogManager.getLogger(ControladorMascota.class);

	public static void main(String[] args) throws VeterinariaException {
		ServicioMascota servicio = new ServicioMascota();
		List<Mascota> mascotasCargadas = servicio.cargarMascotasDesdeXML("veterinaria.xml");

		logger.info("Total de mascotas cargadas: " + mascotasCargadas.size());
		logger.info(mascotasCargadas);
	}
}
