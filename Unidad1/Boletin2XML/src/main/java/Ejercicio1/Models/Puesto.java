package Ejercicio1.Models;

public class Puesto {

	private String idPuesto;
	private String nombrePuesto;
	private String departamento;
	private double salario;

	public Puesto(String nombrePuesto, double salario) {
		super();
		if (nombrePuesto == null || nombrePuesto.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre del puesto no es valido");
		}
		if (salario <= 0.0) {
			throw new IllegalArgumentException("El salario debe ser mayor que 0.0");
		}
		this.nombrePuesto = nombrePuesto.trim();
		this.salario = salario;
	}

	public Puesto(String idPuesto, String nombrePuesto, String departamento, double salario) {
		super();
		if (nombrePuesto == null || nombrePuesto.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre del puesto no es valido");
		}
		if (salario <= 0.0) {
			throw new IllegalArgumentException("El salario debe ser mayor que 0.0");
		}
		this.idPuesto = idPuesto;
		this.nombrePuesto = nombrePuesto.trim();
		this.departamento = departamento;
		this.salario = salario;
	}

	public String getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(String idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getNombrePuesto() {
		return nombrePuesto;
	}

	public void setNombrePuesto(String nombrePuesto) {
		this.nombrePuesto = nombrePuesto;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Puesto [idPuesto=" + idPuesto + ", nombrePuesto=" + nombrePuesto + ", departamento=" + departamento
				+ ", salario=" + salario + "]";
	}
}