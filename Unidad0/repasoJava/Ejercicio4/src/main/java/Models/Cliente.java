package Models;

import java.util.Objects;

public class Cliente {
	
	private String nombre;
	private String direccionCorreoE;
	private int numTelefono;
	
	
	public Cliente(String nombre, String direccionCorreoE, int numTelefono) {
		super();
		this.nombre = nombre;
		this.direccionCorreoE = direccionCorreoE;
		this.numTelefono = numTelefono;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccionCorreoE() {
		return direccionCorreoE;
	}


	public void setDireccionCorreoE(String direccionCorreoE) {
		this.direccionCorreoE = direccionCorreoE;
	}


	public int getNumTelefono() {
		return numTelefono;
	}


	public void setNumTelefono(int numTelefono) {
		this.numTelefono = numTelefono;
	}


	@Override
	public int hashCode() {
		return Objects.hash(direccionCorreoE, nombre, numTelefono);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(direccionCorreoE, other.direccionCorreoE) && Objects.equals(nombre, other.nombre)
				&& numTelefono == other.numTelefono;
	}


	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", direccionCorreoE=" + direccionCorreoE + ", numTelefono=" + numTelefono
				+ "]";
	}

	
	
}
