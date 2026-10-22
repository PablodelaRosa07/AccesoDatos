package EjercicioLectura.Models;

import java.util.List;

public class Persona {
	
	private double notaMin;
	private double notaMax;
	private String nombre;
	private List<Double> notas;
	
	
	public Persona(double notaMin, double notaMax, String nombre, List<Double> notas) {
		super();
		this.notaMin = notaMin;
		this.notaMax = notaMax;
		this.nombre = nombre;
		this.notas = notas;
	}
	
	


	public Persona(String nombre) {
		super();
		this.nombre = nombre;
	}




	public double getNotaMin() {
		return notaMin;
	}


	public void setNotaMin(double notaMin) {
		this.notaMin = notaMin;
	}


	public double getNotaMax() {
		return notaMax;
	}


	public void setNotaMax(double notaMax) {
		this.notaMax = notaMax;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public List<Double> getNotas() {
		return notas;
	}


	public void setNotas(List<Double> notas) {
		this.notas = notas;
	}


	@Override
	public String toString() {
		return "Persona [notaMin=" + notaMin + ", notaMax=" + notaMax + ", nombre=" + nombre + ", notas=" + notas + "]";
	}
	
	
	
}
