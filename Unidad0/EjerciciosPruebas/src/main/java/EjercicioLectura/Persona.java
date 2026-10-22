package EjercicioLectura;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Persona {
	
	private String nombre;
	private List<Double> listaNotas;
	
	public Persona(String nombre, List<Double> listaNotas) {
		super();
		this.nombre = nombre;
		listaNotas = new ArrayList<Double>();
	}
	
	
	

	public Persona(String nombre) {
		super();
		this.nombre = nombre;
	}




	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Double> getListaNotas() {
		return listaNotas;
	}

	public void setListaNotas(List<Double> listaNotas) {
		this.listaNotas = listaNotas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(listaNotas, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(listaNotas, other.listaNotas) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", listaNotas=" + listaNotas + "]";
	}

	public List<Double> agregarNota(double nota) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce nota: ");
		double meter = sc.nextInt();
		listaNotas.add(meter);
		return listaNotas;
	}
	
	
	
}
