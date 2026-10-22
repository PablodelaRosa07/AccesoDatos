

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Prueba2 {
	private static final Logger logger = LogManager.getLogger(Prueba2.class);
	
	public static void main(String[] args){
		Runtime rt = Runtime.getRuntime();
		String[] informacionProceso = {"notePad.exe","miFichero.txt"};
		Process proceso;
		try {
			proceso = rt.exec(informacionProceso);
			int codigoRetorno = proceso.waitFor();
			logger.debug(codigoRetorno);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
