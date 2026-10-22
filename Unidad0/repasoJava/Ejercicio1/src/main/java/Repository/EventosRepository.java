package Repository;

import Models.Evento;
import java.util.List; 
import java.util.ArrayList;

public class EventosRepository {
    
    private List<Evento> listaEventos;

    public EventosRepository(List<Evento> listaEventos) {
        super();
        this.listaEventos = new ArrayList<>(); 
    }

	public List<Evento> getListaEventos() {
		return listaEventos;
	}

	public void setListaEventos(List<Evento> listaEventos) {
		this.listaEventos = listaEventos;
	}
    
    
}

