package DiffFolder.Exceptions;

public class DiffFolderException extends Exception {
	 
    private static final long serialVersionUID = 1L;
 
    public DiffFolderException(String mensaje) {
        super(mensaje);
    }
 
    public DiffFolderException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
 
