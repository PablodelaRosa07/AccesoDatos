package Models;

public class EstadisticasDirectorio {
    private int totalFicheros;
    private int totalDirectorios;

    public EstadisticasDirectorio() {
        this.totalFicheros = 0;
        this.totalDirectorios = 0;
    }

    public void incrementarFicheros() {
        this.totalFicheros++;
    }

    public void incrementarDirectorios() {
        this.totalDirectorios++;
    }

    public int getTotalFicheros() {
        return totalFicheros;
    }

    public int getTotalDirectorios() {
        return totalDirectorios;
    }

	@Override
	public String toString() {
		return "EstadisticasDirectorio [totalFicheros=" + totalFicheros + ", totalDirectorios=" + totalDirectorios
				+ "]";
	}

    
}