package Ejercicio1.Models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Empleado {
	private static final Logger logger = LogManager.getLogger(Empleado.class);

	private String identificador;
	private String nombreApellidos;
	private int edad;	
	private String empresa;
	private Puesto puesto;
	
	
	public Empleado(String nombreApellidos, String empresa, Puesto puesto) {
		super();
		try {
			if (nombreApellidos != "" && nombreApellidos != null) {
				this.nombreApellidos = nombreApellidos;
			}
			else {
				throw new Exception();
			}
			if (empresa != "" && empresa != null) {
				this.empresa = empresa;
			}
			else {
				throw new Exception();
			}
			if (puesto != null) {
				this.puesto = puesto;
			}
			else {
				throw new Exception();
			}
			
		} catch (Exception e) {
			logger.error("No se ha podido crear el objeto, problemas en el constructor");
		}
		
	}


	public String getIdentificador() {
		return identificador;
	}


	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}


	public String getNombreApellidos() {
		return nombreApellidos;
	}


	public void setNombreApellidos(String nombreApellidos) {
		this.nombreApellidos = nombreApellidos;
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


	public Puesto getPuesto() {
		return puesto;
	}


	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}


	public static Logger getLogger() {
		return logger;
	}


	@Override
	public String toString() {
		return "Empleado [nombreApellidos=" + nombreApellidos + ", empresa=" + empresa + ", puesto=" + puesto + "]";
	}


	
	
	
	
	
}
