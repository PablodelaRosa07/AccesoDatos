import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LanzaComandosJava {
	private final static String directorioGenerarClasses = "target\\classes";
	private final static String rutaSourceJava = "src\\main\\java\\unidad1\\ProcesoHijo.java";
	private static final Logger logger = LogManager.getLogger(LanzaComandosJava.class);

	public int lanzaCompilador(String rutaFicheroFuente, String rutaParaGenerarClass) {
		ProcessBuilder pb;
		int exit = 0;
		try {
			String[] comando = { "javac", "-d", directorioGenerarClasses, rutaSourceJava};
			pb = new ProcessBuilder(comando);
			Process proceso = pb.start();
			logger.debug("javac terminó con código {}", exit); 

		} catch (Exception e){ 			
			logger.error(e.getMessage()); 		
			}
		return exit;
	}
	public static void main(String[] args) {
		LanzaComandosJava l = new LanzaComandosJava();
		int codigo= l.lanzaCompilador(rutaSourceJava, directorioGenerarClasses);                 
	} }

