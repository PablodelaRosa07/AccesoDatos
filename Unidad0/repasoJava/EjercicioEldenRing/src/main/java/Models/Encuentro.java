package Models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Encuentro {
    private String idEncuentro; 
    private String zona;
    private LocalDate fecha;
    private int dificultad; 
    private List<String> enemigos;

    public Encuentro(String idEncuentro, String zona, LocalDate fecha, int dificultad, List<String> enemigos) {
        this.idEncuentro = idEncuentro;
        this.zona = zona;
        this.fecha = fecha;
        this.dificultad = dificultad;
        this.enemigos = enemigos;
    }

    public String getIdEncuentro() {
        return idEncuentro;
    }

    public String getZona() {
        return zona;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getDificultad() {
        return dificultad;
    }

    public List<String> getEnemigos() {
        return enemigos;
    }

	@Override
	public String toString() {
		return "Encuentro [idEncuentro=" + idEncuentro + ", zona=" + zona + ", fecha=" + fecha + ", dificultad="
				+ dificultad + ", enemigos=" + enemigos + "]";
	}

    
}