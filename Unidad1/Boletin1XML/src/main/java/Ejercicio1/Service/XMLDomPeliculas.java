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

    public List<Pelicula> cargarPeliculas(String nombreFichero) {
        List<Pelicula> peliculas = new ArrayList<>();
        List<Pelicula> peliculasDefinitivas = new ArrayList<>();
        List<String> listaActores = new ArrayList<String>();
        Document doc = getDocumentFromXML(nombreFichero);

        if (doc != null) {
            NodeList nodos = doc.getElementsByTagName("Pelicula");
            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nodo;
                    String titulo = obtenerTexto(elem, "Titulo");
                    String fecha = obtenerTexto(elem, "Fecha");
                    String director = obtenerTexto(elem, "Director");                    
                    String actor = obtenerTexto(elem, "Actor");
                    listaActores.add(actor);
                    peliculas.add(new Pelicula(titulo, fecha, director, listaActores));
                }
            }
            
            nodos = doc.getElementsByTagName("Actores");
            for (int i = 0; i < nodos.getLength(); i++) {
            	Node nodo = nodos.item(0);
            	if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nodo;                   
                    String actor = obtenerTexto(elem, "Actor");
                    listaActores.add(i, actor);
                }
            }
        }

        return peliculas;
    }

    private String obtenerTexto(Element padre, String etiqueta) {
        String texto = "";
        NodeList lista = padre.getElementsByTagName(etiqueta);
        if (lista != null && lista.getLength() > 0 && lista.item(0) != null) {
            texto = lista.item(0).getTextContent().trim();
        }
        return texto;
    }
}
