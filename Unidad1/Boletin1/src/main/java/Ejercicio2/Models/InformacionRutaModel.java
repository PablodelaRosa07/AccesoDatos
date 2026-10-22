package Models;

import java.nio.file.Path;

public class InformacionRutaModel {
    private String nombre;
    private String rutaEscrita;
    private Path rutaAbsoluta;
    private String rutaCanonica;
    private Path directorioPadre;
    private String tipo;
    private boolean lectura;
    private boolean escritura;
    private boolean ejecucion;
    private boolean oculto;
    private long tamanoBytes;
    private Long numeroElementos; 
    private String fechaUltimaModificacion;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRutaEscrita() {
		return rutaEscrita;
	}
	public void setRutaEscrita(String rutaEscrita) {
		this.rutaEscrita = rutaEscrita;
	}
	public Path getRutaAbsoluta() {
		return rutaAbsoluta;
	}
	public void setRutaAbsoluta(Path rutaAbsoluta) {
		this.rutaAbsoluta = rutaAbsoluta;
	}
	public String getRutaCanonica() {
		return rutaCanonica;
	}
	public void setRutaCanonica(String rutaCanonica) {
		this.rutaCanonica = rutaCanonica;
	}
	public Path getDirectorioPadre() {
		return directorioPadre;
	}
	public void setDirectorioPadre(Path directorioPadre) {
		this.directorioPadre = directorioPadre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isLectura() {
		return lectura;
	}
	public void setLectura(boolean lectura) {
		this.lectura = lectura;
	}
	public boolean isEscritura() {
		return escritura;
	}
	public void setEscritura(boolean escritura) {
		this.escritura = escritura;
	}
	public boolean isEjecucion() {
		return ejecucion;
	}
	public void setEjecucion(boolean ejecucion) {
		this.ejecucion = ejecucion;
	}
	public boolean isOculto() {
		return oculto;
	}
	public void setOculto(boolean oculto) {
		this.oculto = oculto;
	}
	public long getTamanoBytes() {
		return tamanoBytes;
	}
	public void setTamanoBytes(long tamanoBytes) {
		this.tamanoBytes = tamanoBytes;
	}
	public Long getNumeroElementos() {
		return numeroElementos;
	}
	public void setNumeroElementos(Long numeroElementos) {
		this.numeroElementos = numeroElementos;
	}
	public String getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}
	public void setFechaUltimaModificacion(String fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

}