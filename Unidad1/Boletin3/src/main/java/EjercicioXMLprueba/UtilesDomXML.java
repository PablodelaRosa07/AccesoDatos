package EjercicioXMLprueba;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class UtilesDomXML {
    private static final String RUTA_RESOURCES = "src/main/resources/";
    
    public static Document getDocumentFromXML(String nombreFichero) {
        Document doc = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(new File(RUTA_RESOURCES + nombreFichero));
            
        } catch (SAXException | IOException | ParserConfigurationException e){
            e.printStackTrace();
        }
        return doc;
    }
}