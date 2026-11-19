package EjercicioXMLprueba;

import java.util.Objects;

public class Libro {
	
	private String id;
	private String categoria;
	private String titulo;
	private String autor;
	
	public Libro(String id, String titulo) {
		super();
		this.id = id;
		this.titulo = titulo;
	}

	public Libro(String titulo) {
		super();
		this.titulo = titulo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
	
	

	@Override
	public int hashCode() {
		return Objects.hash(autor, categoria, id, titulo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(autor, other.autor) && Objects.equals(categoria, other.categoria)
				&& Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo);
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", categoria=" + categoria + ", titulo=" + titulo + ", autor=" + autor + "]";
	}
	
	
	
	
	
}
