package Ejercicio1.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Ejercicio1.Models.Pelicula;


public class XMLDomPeliculas {

    private final String rutaResources;

    public XMLDomPeliculas() {
        this.rutaResources = "src/main/resources/";
    }

    public XMLDomPeliculas(String rutaResources) {
        this.rutaResources = rutaResources;
    }

    public Document getDocumentFromXML(String nombreFichero) { // "Convierte" el xml en legible
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  // Preguntar a Soraya qué hace exactamente
            DocumentBuilder builder = factory.newDocumentBuilder();					// ===
            doc = builder.parse(new File(rutaResources + nombreFichero));			// ===
            doc.getDocumentElement().normalize();									// Unifica el texto para que sea más limpio y legible el dom
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    public List<Pelicula> cargarPeliculas(String nombreFichero) { // Carga los valores de los atributos
        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        Document doc = getDocumentFromXML(nombreFichero);			// Guarda el fichero en una variable llamada doc, que llama a una funcion que returnea el documento

        if (doc != null) {
            NodeList nodos = doc.getElementsByTagName("Pelicula"); 			 // Obtiene el objeto donde se encuentran todos los valores y los guarda en una listaNode
            for (int i = 0; i < nodos.getLength(); i++) { 					 // Recorre esa listaNode creada 
                Node nodo = nodos.item(i);   // 							// Obtiene el nodo (un nodo es un objeto basico con datos dinamicos) de la listaNode
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {				// Comprueba que ese nodo sea realmente una etiqueta XML (y no un salto de línea, espacio en blanco o comentario que también cuentan como nodos en DOM).
                    Element elem = (Element) nodo;							// Lo convierte en un elemento para poder mirar las cosas que hay dentro
                    String titulo = obtenerTexto(elem, "Titulo");
                    String duracion = obtenerTexto(elem, "Duracion");		// Obtiene el texto returneado de la funcion y la guarda en una variable
                    String genero = obtenerTexto(elem, "Genero");
                    String sinopsis = obtenerTexto(elem, "sinopsis");
                    String fecha = obtenerTexto(elem, "Fecha");
                    String director = obtenerTexto(elem, "Director");
                    List<String> listaActores = obtenerListaActores(elem);	// En este caso, el objeto tiene muchos valores, por tanto hay que hacer una lista y no un String

                    Pelicula p = new Pelicula(titulo, duracion, genero, sinopsis, fecha, director, listaActores);   // Crea el objeto con los valores
                    peliculas.add(p);																				// Mete el objeto en una lista
                }
            }
        }

        return peliculas;
    }

    private List<String> obtenerListaActores(Element padrePelicula) {			  // Obtiene el objeto ya buscado
        List<String> actores = new ArrayList<String>();
        NodeList nodosActores = padrePelicula.getElementsByTagName("Actor");	// Crea una listaNode del objeto pelicula 
        for (int i = 0; i < nodosActores.getLength(); i++) {					// En vez de iterar sobre el objeto pelicula, lo hace sobre el objeto actor, cada actor se guarda por separado en la lista
            Node nodoActor = nodosActores.item(i);
            if (nodoActor != null && nodoActor.getNodeType() == Node.ELEMENT_NODE) {    // Igual que antes
                String nombreActor = nodoActor.getTextContent().trim();
                if (!nombreActor.isEmpty()) {                                   // Comprueba que no esté vacío (la cadena String ya está creada por tanto, o está vacía, o tiene el valor)
                    actores.add(nombreActor);
                }
            }
        }
        return actores;
    }

    private String obtenerTexto(Element padre, String etiqueta) {				
        String texto = "";
        NodeList lista = padre.getElementsByTagName(etiqueta);   				// Crea una listaNode del objeto buscando la etiqueta que se pasa por parametro.
        if (lista != null && lista.getLength() > 0 && lista.item(0) != null) {	// Si la lista no está vacía y no es nula y el primer item no es nulo
            texto = lista.item(0).getTextContent().trim();						// Obtiene el texto del item ese y lo guarda en una variable "texto"
        }
        return texto;															// Returnea texto
    }
}