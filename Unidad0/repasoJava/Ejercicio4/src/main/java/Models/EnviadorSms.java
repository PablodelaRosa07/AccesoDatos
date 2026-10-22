package Models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnviadorSms {
private static final Logger logger = LogManager.getLogger(EnviadorSms.class);    
public void enviarSms(String telefono, String texto) {
        logger.debug("SMS a " + telefono + ": " + texto);
    }
}

