package EjercicioXMLprueba;

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class EjemploDOM {
    private static final String RUTA_RESOURCES = "src/main/resources/";
    private static final Logger logger = LogManager.getLogger(EjemploDOM.class);

    public static void main(String[] args) {
        Document doc = UtilesDomXML.getDocumentFromXML("libro.xml");
        
        if (doc != null) {
            Element elementoRaiz = doc.getDocumentElement();
            
            logger.info("Elemento raíz: " + elementoRaiz.getNodeName());
            
            NodeList listaTitulos = elementoRaiz.getElementsByTagName("titulo");
            
            if (listaTitulos.getLength() > 0) {
                Element tituloElement = (Element) listaTitulos.item(0);
                String titulo = tituloElement.getTextContent();
                logger.info("Primer título encontrado: " + titulo);
            } else {
            	logger.info("No se encontró ningún elemento <titulo> en el XML.");
            }
        } else {
        	logger.info("No se pudo cargar el documento XML.");
        }
    }
}