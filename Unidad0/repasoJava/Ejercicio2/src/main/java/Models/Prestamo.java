package Models;

import java.time.LocalDate;

public class Prestamo {
    private Usuario usuario;
    private Libro libro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucionPrevista;
    private boolean devuelto;

    public Prestamo(Usuario usuario, Libro libro, LocalDate fechaPrestamo, LocalDate fechaDevolucionPrevista) {
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionPrevista = fechaDevolucionPrevista;
        this.devuelto = false;
    }

    

    public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}



	public Libro getLibro() {
		return libro;
	}



	public void setLibro(Libro libro) {
		this.libro = libro;
	}



	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}



	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}



	public LocalDate getFechaDevolucionPrevista() {
		return fechaDevolucionPrevista;
	}



	public void setFechaDevolucionPrevista(LocalDate fechaDevolucionPrevista) {
		this.fechaDevolucionPrevista = fechaDevolucionPrevista;
	}



	public boolean isDevuelto() {
		return devuelto;
	}



	public void setDevuelto(boolean devuelto) {
		this.devuelto = devuelto;
	}



	@Override
	public String toString() {
		return "Prestamo [usuario=" + usuario + ", libro=" + libro + ", fechaPrestamo=" + fechaPrestamo
				+ ", fechaDevolucionPrevista=" + fechaDevolucionPrevista + ", devuelto=" + devuelto + "]";
	}



	
}
