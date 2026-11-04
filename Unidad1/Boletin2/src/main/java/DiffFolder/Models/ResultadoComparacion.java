package DiffFolder.Models;

public class ResultadoComparacion {
	 
    private final String nomFichero;
    private final ValorComparacion valorComparacion;
 
    public ResultadoComparacion(String nomFichero, ValorComparacion valorComparacion) {
        this.nomFichero = nomFichero;
        this.valorComparacion = valorComparacion;
    }
 
    /** Nombre del elemento que se ha comparado. */
    public String getNomFichero() {
        return nomFichero;
    }
 
    /** Resultado de la comparación para ese elemento. */
    public ValorComparacion getValorComparacion() {
        return valorComparacion;
    }
 
    @Override
    public String toString() {
        return nomFichero + " -> " + valorComparacion;
    }
}