package Ejercicio1.Repository;

import java.util.ArrayList;
import java.util.List;

import Ejercicio1.Models.Empleado;
import Ejercicio1.Service.UtilesXMLDom;

public class EmpleadoRepositorio {

	private final UtilesXMLDom utilesXMLDom;
	private List<Empleado> listaEmpleados;

	public EmpleadoRepositorio() {
		this.utilesXMLDom = new UtilesXMLDom();
		this.listaEmpleados = new ArrayList<Empleado>();
	}

	public EmpleadoRepositorio(String rutaFichero) {
		this.utilesXMLDom = new UtilesXMLDom();
		this.listaEmpleados = new ArrayList<Empleado>();
		cargarDesdeFichero(rutaFichero);
	}

	public void cargarDesdeFichero(String rutaFichero) {
		if (rutaFichero != null && !rutaFichero.trim().isEmpty()) {
			this.listaEmpleados = this.utilesXMLDom.leerEmpleadosDesdeXML(rutaFichero);
		}
	}

	public boolean guardarEnFichero(String rutaFichero) {
		return this.utilesXMLDom.guardarEmpleadosEnXML(this.listaEmpleados, rutaFichero);
	}

	public void agregarEmpleado(Empleado empleado) {
		if (empleado != null) {
			this.listaEmpleados.add(empleado);
		}
	}

	public List<Empleado> getListaEmpleados() {
		return this.listaEmpleados;
	}

	public void setListaEmpleados(List<Empleado> listaEmpleados) {
		this.listaEmpleados = listaEmpleados;
	}
}

