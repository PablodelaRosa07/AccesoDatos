package Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import Models.*;

public class RepoNotificador {

	private List<String> listaNotificaciones;

	
	public RepoNotificador(List<String> listaNotificaciones) {
		super();
		this.listaNotificaciones = new ArrayList<String>();
	}

	
	
	public List<String> getListaNotificaciones() {
		return listaNotificaciones;
	}

	public void setListaNotificaciones(List<String> listaNotificaciones) {
		this.listaNotificaciones = listaNotificaciones;
	}



	@Override
	public int hashCode() {
		return Objects.hash(listaNotificaciones);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RepoNotificador other = (RepoNotificador) obj;
		return Objects.equals(listaNotificaciones, other.listaNotificaciones);
	}



	@Override
	public String toString() {
		return "RepoNotificador [listaNotificaciones=" + listaNotificaciones + "]";
	}
	
	
	
	
	
	
}
