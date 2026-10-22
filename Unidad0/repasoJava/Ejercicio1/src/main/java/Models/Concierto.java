package Models;

import java.time.LocalDate;
import Exceptions.CrafterException;

public class Concierto extends Evento {

    private String artistaPrincipal;
    private String[] artistasInvitados;
    private int numInvitados;

    public Concierto(String nombre, LocalDate fechaEvento, int numEntradasVendidas, int capacidadMaxAsistentes, Estado estado, String artistaPrincipal) throws CrafterException {
        super(capacidadMaxAsistentes, nombre, fechaEvento, numEntradasVendidas, capacidadMaxAsistentes, estado);
        this.artistaPrincipal = artistaPrincipal;
        this.artistasInvitados = new String[10];
        this.numInvitados = 0;
    }

    public String getArtistaPrincipal() {
        return artistaPrincipal;
    }

    public void setArtistaPrincipal(String artistaPrincipal) {
        this.artistaPrincipal = artistaPrincipal;
    }

    public void addArtistaInvitado(String artista) {
        if (numInvitados < 10 && artista != null) {
            artistasInvitados[numInvitados] = artista;
            numInvitados++;
          
        }
   
    }

    public String[] getArtistasInvitados() {
        return artistasInvitados;
    }

    @Override
    public double calcularCosteBase() {
        return 5000.0 + 2000.0 + (500.0 * numInvitados);
    }

	public void modificaEstado(Estado programado) {

		
	}
}