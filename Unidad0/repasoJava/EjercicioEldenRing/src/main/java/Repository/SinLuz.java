package Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import Models.Encuentro;

public class SinLuz implements Comparable<SinLuz> {
    private static int contadorId = 1;
    
    private int id;
    private String nombre;
    private Map<String, Encuentro> mapaEncuentros;

    public SinLuz(String nombre) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.mapaEncuentros = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Map<String, Encuentro> getMapaEncuentros() {
        return mapaEncuentros;
    }

    public void agregarEncuentro(Encuentro encuentro) {
        this.mapaEncuentros.put(encuentro.getIdEncuentro(), encuentro);
    }

    
    
    @Override
    public int compareTo(SinLuz otro) {
        return this.nombre.compareToIgnoreCase(otro.nombre);
    }

	@Override
	public String toString() {
		return "SinLuz [id=" + id + ", nombre=" + nombre + ", mapaEncuentros=" + mapaEncuentros + "]";
	}

    
}