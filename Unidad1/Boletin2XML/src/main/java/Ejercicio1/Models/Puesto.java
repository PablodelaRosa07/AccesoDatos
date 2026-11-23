package Ejercicio1.Models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Puesto {
	
	private static final Logger logger = LogManager.getLogger(Puesto.class);

	private String idPuesto;
	private String nombrePuesto;
	private String departamento;
	private double salario;
	
	
	
	public Puesto(String nombrePuesto, double salario) {
		super();
		try {
			if (nombrePuesto != null) {
				this.nombrePuesto = nombrePuesto;
			}
			else {
				throw new Exception();
			}
			if (salario > 0.0) {
				this.salario = salario;
			}
			else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			logger.error("No se ha podido crear el objeto, problemas en el constructor");
		}
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
		return "Puesto [nombrePuesto=" + nombrePuesto + ", salario=" + salario + "]";
	}



	
	
	
	
	
}
