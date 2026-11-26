package Models;

import java.util.Objects;

import Exception.VeterinariaException;

public class Mascota {

	private int id;
	private final String nombre;
	private Especie especie;
	private final Propietario propietario;
	
	
	public Mascota(int id, String nombre, Especie especie, Propietario propietario) throws VeterinariaException {
		super();
		this.id = id;
		
		if (nombre == null) {
			throw new VeterinariaException("Falta el nombre de la mascota, id: " +id);
		}
		else {
			this.nombre = nombre;
		}
		
		if (propietario == null) {
			throw new VeterinariaException("Falta el propietario de la mascota, id: " +id);
		}
		else {
			this.propietario = propietario;
		}
		
		this.especie = especie;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	public String getNombre() {
		return nombre;
	}

	public Propietario getPropietario() {
		return propietario;
	}


	@Override
	public int hashCode() {
		return Objects.hash(especie, id, nombre, propietario);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mascota other = (Mascota) obj;
		return especie == other.especie && id == other.id && Objects.equals(nombre, other.nombre)
				&& Objects.equals(propietario, other.propietario);
	}


	@Override
	public String toString() {
		return "Mascota [id=" + id + ", nombre=" + nombre + ", especie=" + especie + ", propietario=" + propietario
				+ "]";
	}
	
	
}
