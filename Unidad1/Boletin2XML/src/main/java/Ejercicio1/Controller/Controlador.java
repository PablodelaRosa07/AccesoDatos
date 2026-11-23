package Ejercicio1.Controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Ejercicio1.Models.Empleado;
import Ejercicio1.Models.Puesto;

public class Controlador {
	private static final Logger logger = LogManager.getLogger(Controlador.class);

	public static void main(String[] args) {
		Controlador controlador = new Controlador();
		Puesto puesto = new Puesto("Junior", 1234.56);
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		
		controlador.agregarEmpleado("Pablo", "NTT", puesto, listaEmpleados);
		controlador.agregarEmpleado("Pepe", "Ayesa", puesto, listaEmpleados);
		controlador.agregarEmpleado("Elena", "NTT", puesto, listaEmpleados);
		controlador.agregarEmpleado("Javier", "NTT", puesto, listaEmpleados);
		controlador.agregarEmpleado("Sergio", "Ayesa", puesto, listaEmpleados);
		
		logger.info(listaEmpleados);
	}
	
	
	
	
	public List<Empleado> agregarEmpleado(String nombre, String empresa, Puesto puesto, List<Empleado> listaEmpleados){
		
		boolean comprueba = false;
		try {		
			Empleado empleado = new Empleado(nombre, empresa, puesto);
			listaEmpleados.add(empleado);
			if (listaEmpleados.contains(empleado)) {
				comprueba = true;
			}
			if (!comprueba) {
				throw new Exception();
			}
		} catch (Exception e) {
			logger.error("Problemas al añadir un empleado a listaEmpleados");
		}
		return listaEmpleados;
	}
}
