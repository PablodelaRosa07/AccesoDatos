package EjercicioPruebaJson.Models;

public class Empleado {

	private String id;
	private String nombreApellido;
	private int edad;
	private String empresa;
	
	public Empleado(String id, String nombreApellido, int edad, String empresa) {
		super();
		this.id = id;
		this.nombreApellido = nombreApellido;
		this.edad = edad;
		this.empresa = empresa;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
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

	@Override
	public String toString() {
		return "Empleado [id=" + id + ", nombreApellido=" + nombreApellido + ", edad=" + edad + ", empresa=" + empresa
				+ "]";
	}
	
	
}
