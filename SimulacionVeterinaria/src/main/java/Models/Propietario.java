package Models;

import java.util.Objects;

public class Propietario {

	private final String nombre;

	public Propietario(String nombre) {
		super();
		this.nombre = nombre;
	}

	
	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Propietario other = (Propietario) obj;
		return Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return "Propietario [nombre=" + nombre + "]";
	}
	
	
}
