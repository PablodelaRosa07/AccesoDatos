package Ejercicio1.Models;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Empleado {
	private static final Logger logger = LogManager.getLogger(Empleado.class);

	private String identificador;
	private String nombreApellidos;
	private int edad;
	private String empresa;
	private List<Puesto> puestos;

	public Empleado(String nombreApellidos, String empresa, Puesto puesto) { // El bloque try-catch debe ir en el service a la hora de crear el objeto
		super();
		validarDatosBasicos(nombreApellidos, empresa);
		if (puesto == null) {
			throw new IllegalArgumentException("El puesto no puede ser nulo");
		}
		this.nombreApellidos = nombreApellidos.trim();
		this.empresa = empresa.trim();
		this.puestos = new ArrayList<Puesto>();
		this.puestos.add(puesto);
	}

	public Empleado(String identificador, String nombreApellidos, int edad, String empresa, List<Puesto> puestos) { // El bloque try-catch debe ir en el service a la hora de crear el objeto
		super();
		validarDatosBasicos(nombreApellidos, empresa);
		if (puestos == null || puestos.isEmpty()) {
			throw new IllegalArgumentException("La lista de puestos no puede estar vacia");
		}
		this.identificador = identificador;
		this.nombreApellidos = nombreApellidos.trim();
		this.edad = edad;
		this.empresa = empresa.trim();
		this.puestos = new ArrayList<Puesto>(puestos);
	}

	private void validarDatosBasicos(String nombreApellidos, String empresa) {
		if (nombreApellidos == null || nombreApellidos.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre y apellidos no son validos");
		}
		if (empresa == null || empresa.trim().isEmpty()) {
			throw new IllegalArgumentException("La empresa no es valida");
		}
	}

	public void agregarPuesto(Puesto puesto) {
		if (puesto != null) {
			if (this.puestos == null) {
				this.puestos = new ArrayList<Puesto>();
			}
			this.puestos.add(puesto);
		}
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getNombreApellidos() {
		return nombreApellidos;
	}

	public void setNombreApellidos(String nombreApellidos) {
		this.nombreApellidos = nombreApellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public List<Puesto> getPuestos() {
		return puestos;
	}

	public void setPuestos(List<Puesto> puestos) {
		this.puestos = puestos;
	}

	public static Logger getLogger() {
		return logger;
	}

	@Override
	public String toString() {
		return "Empleado [nombreApellidos=" + nombreApellidos + ", empresa=" + empresa + ", puestos=" + puestos + "]";
	}
}