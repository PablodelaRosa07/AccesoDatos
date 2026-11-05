package Ejercicio1.Models;

import java.util.ArrayList;
import java.util.List;

public class Pais {
	
	private String nombre;
	private String continente;
	private double poblacion;
	private List<String> idiomas;
	private String moneda;
	
	public Pais(String nombre, String continente, double poblacion, List<String> idiomas, String moneda) {
		super();
		this.nombre = nombre;
		this.continente = continente;
		this.poblacion = poblacion;
		idiomas = new ArrayList<String>();
		this.moneda = moneda;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContinente() {
		return continente;
	}

	public void setContinente(String continente) {
		this.continente = continente;
	}

	public double getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(double poblacion) {
		this.poblacion = poblacion;
	}

	public List<String> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(List<String> idiomas) {
		this.idiomas = idiomas;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	@Override
	public String toString() {
		return "Pais [nombre=" + nombre + ", continente=" + continente + ", poblacion=" + poblacion + ", idiomas="
				+ idiomas + ", moneda=" + moneda + "]";
	}
	
	
	
	
}
