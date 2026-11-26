package Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Exception.VeterinariaException;
import Models.Especie;
import Models.Mascota;
import Models.Propietario;


public class MascotaDAO {
	
	private static final Logger logger = LogManager.getLogger(MascotaDAO.class);
	
	private final String rutaResources;

    public MascotaDAO() {
        this.rutaResources = "src/main/resources/";
    }

    public MascotaDAO(String rutaResources) {
        this.rutaResources = rutaResources;
    }
    
    
    
    public Document getDocumentFromXML(String nombreFichero) { 
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder builder = factory.newDocumentBuilder();					
            doc = builder.parse(new File(rutaResources + nombreFichero));			
            doc.getDocumentElement().normalize();									
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }
    
    
    public List<Mascota> findAll(String nombreFichero) throws VeterinariaException{
    	List<Mascota> mascotas = new ArrayList<Mascota>();
        Document doc = getDocumentFromXML(nombreFichero);
        
        if (doc != null) {
            NodeList nodos = doc.getElementsByTagName("mascota"); 			 
            for (int i = 0; i < nodos.getLength(); i++) { 					
                Node nodo = nodos.item(i);   // 							
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {				
                    Element elem = (Element) nodo;							
                    String id = obtenerTextoAtributo(elem, "id");
                    String especie = obtenerTextoAtributo(elem, "especie");		
                    String nombre = obtenerTexto(elem, "nombre");
                    List<String> nombrePropietario = obtenerNombrePropietario(elem);	
                    
                    Integer idNum = Integer.parseInt(id);  
                    Propietario pro = new Propietario(nombrePropietario.get(0));
                    Especie esp = null;
                    
                    if (especie.equals("PERRO")) {
                    	esp = Especie.PERRO;
                    }
                    if (especie.equals("GATO")) {
                    	esp = Especie.GATO;
                    }
                    if (especie.equals("AVE")) {
                    	esp = Especie.AVE;
                    }
                    if (especie.equals("EXOTICO")) {
                    	esp = Especie.EXOTICO;
                    }
                    

                    Mascota m = new Mascota(idNum, nombre, esp, pro);   
                    mascotas.add(m);																				
                }
            }
        }

        return mascotas;
    	
    }
    
    
    private String obtenerTexto(Element padre, String etiqueta) {		 // Obtiene el texto de la etiqueta		
        String texto = "";
        NodeList lista = padre.getElementsByTagName(etiqueta);   				
        if (lista != null && lista.getLength() > 0 && lista.item(0) != null) {	
            texto = lista.item(0).getTextContent().trim();						
        }
        return texto;															
    }
    
    private String obtenerTextoAtributo(Element padre, String etiqueta) {		 // Obtiene el texto de la etiqueta		
        String texto = padre.getAttribute(etiqueta);
        
        if (texto != null && texto != "") {	
            texto = padre.getAttribute(etiqueta).trim();						
        }
        return texto;															
    }
    
    
    private List<String> obtenerNombrePropietario(Element padreMascota) {
        List<String> listaPropietario = new ArrayList<String>();
        NodeList nodosPropietario = padreMascota.getElementsByTagName("propietario");

        for (int i = 0; i < nodosPropietario.getLength(); i++) {
            Node nodoProp = nodosPropietario.item(i);
            if (nodoProp != null && nodoProp.getNodeType() == Node.ELEMENT_NODE) {
                Element elemProp = (Element) nodoProp;
                NodeList nodosNombre = elemProp.getElementsByTagName("nombre");
                if (nodosNombre.getLength() > 0 && nodosNombre.item(0) != null) {
                    String nombreProp = nodosNombre.item(0).getTextContent().trim();
                    if (!nombreProp.isEmpty()) {
                        listaPropietario.add(nombreProp);
                    }
                }
            }
        }
        return listaPropietario;
    }
    
    
    
    
    public boolean altaMascotas(List<Mascota> listaMascotas, String rutaFichero) {
		boolean guardadoCorrectamente = false; 

		if (listaMascotas != null && rutaFichero != null && !rutaFichero.isEmpty()) {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document documento = builder.newDocument();

				Element raizMascotas = documento.createElement("clinica"); 
				documento.appendChild(raizMascotas); 

				for (int i = 0; i < listaMascotas.size(); i++) { 
					Mascota mascota = listaMascotas.get(i); 

					if (mascota != null) { 
						Element elementoMascota = documento.createElement("mascota"); 
						if (String.valueOf(mascota.getId()) != null && !String.valueOf(mascota.getId()).trim().isEmpty()) {
							elementoMascota.setAttribute("id", String.valueOf(mascota.getId())); 
						}
						raizMascotas.appendChild(elementoMascota); 
						if (String.valueOf(mascota.getEspecie()) != null && !String.valueOf(mascota.getEspecie()).trim().isEmpty()) {
							elementoMascota.setAttribute("especie", String.valueOf(mascota.getEspecie())); 
						}
						raizMascotas.appendChild(elementoMascota); 
				

						Element elementoNombre = documento.createElement("nombre");
						elementoNombre.setTextContent(mascota.getNombre());
						elementoMascota.appendChild(elementoNombre);
						
						Element elementoPropietario = documento.createElement("nombrePropietario");
						elementoPropietario.setTextContent(String.valueOf(mascota.getPropietario()));
						elementoMascota.appendChild(elementoPropietario);
			
					}
				}

				TransformerFactory transformerFactory = TransformerFactory.newInstance(); // Crea el transformador que convertirá el árbol DOM en texto XML.
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // Indica que el archivo se codifique en UTF-8.
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

				DOMSource origen = new DOMSource(documento); // Envuelve el Document en memoria como origen de datos.
				StreamResult destino = new StreamResult(new File(rutaFichero)); // Indica el archivo de destino en disco donde se escribirá el XML.
				transformer.transform(origen, destino); 

				guardadoCorrectamente = true;
			} catch (Exception e) {
				logger.error("Error al guardar las mascotas en el fichero XML: " + rutaFichero, e);
				guardadoCorrectamente = false;
			}
		} else {
			logger.error("La lista de mascotas o la ruta del fichero no son validas");
			guardadoCorrectamente = false;
		}

		return guardadoCorrectamente;
	}

}
