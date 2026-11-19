package Models;

public class Jefe {
	
	private String nombre;
	private boolean contenedorCorazon;
	
	
	public Jefe(String nombre, boolean objeto) {
		super();
		this.nombre = nombre;
		this.contenedorCorazon = objeto;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean getObjeto() {
		return contenedorCorazon;
	}
	public void setObjeto(boolean objeto) {
		this.contenedorCorazon = objeto;
	}
	@Override
	public String toString() {
		return "Jefe [nombre=" + nombre + ", contenedorCorazon=" + contenedorCorazon + "]";
	}
	
	

}
