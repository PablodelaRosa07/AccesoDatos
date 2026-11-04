package DiffFolder.Exceptions;

public class GestionFicherosException extends Exception {
	 
    private static final long serialVersionUID = 1L;
 
    public GestionFicherosException(String mensaje) {
        super(mensaje);
    }
 
    public GestionFicherosException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
 