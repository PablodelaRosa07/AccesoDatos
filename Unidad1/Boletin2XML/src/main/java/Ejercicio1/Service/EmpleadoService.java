package Ejercicio1.Service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Ejercicio1.Models.Empleado;
import Ejercicio1.Models.Puesto;
import Ejercicio1.Repository.EmpleadoRepositorio;

public class EmpleadoService {

	private static final Logger logger = LogManager.getLogger(EmpleadoService.class);
	private final EmpleadoRepositorio empleadoRepositorio;

	public EmpleadoService() {
		this(new EmpleadoRepositorio());
	}

	public EmpleadoService(EmpleadoRepositorio empleadoRepositorio) {
		this.empleadoRepositorio = empleadoRepositorio;
	}

	public List<Empleado> cargarEmpleadosDesdeXML(String rutaFichero) {
		empleadoRepositorio.cargarDesdeFichero(rutaFichero);
		return empleadoRepositorio.getListaEmpleados();
	}

	public boolean guardarEmpleadosEnXML(String rutaFichero) {
		return empleadoRepositorio.guardarEnFichero(rutaFichero);
	}

	public boolean registrarEmpleado(String nombreApellidos, String empresa, Puesto puesto) {
		boolean registrado = false;
		try {
			Empleado empleado = new Empleado(nombreApellidos, empresa, puesto);
			empleadoRepositorio.agregarEmpleado(empleado);
			registrado = true;
		} catch (IllegalArgumentException e) {
			logger.error("No se ha podido crear o registrar el empleado: " + e.getMessage(), e);
		}
		return registrado;
	}

	public List<Empleado> listarEmpleados() {
		return empleadoRepositorio.getListaEmpleados();
	}
}