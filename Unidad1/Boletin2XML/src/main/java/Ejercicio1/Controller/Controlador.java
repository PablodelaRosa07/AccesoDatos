package Ejercicio1.Controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Ejercicio1.Models.Empleado;
import Ejercicio1.Service.EmpleadoService;

public class Controlador {

	private static final Logger logger = LogManager.getLogger(Controlador.class);

	public static void main(String[] args) {
		EmpleadoService servicio = new EmpleadoService();
		List<Empleado> empleadosCargados = servicio.cargarEmpleadosDesdeXML("src/main/resources/empleados_100.xml");

		logger.info("Total de empleados cargados: " + empleadosCargados.size());
		logger.info(empleadosCargados);
	}
}
