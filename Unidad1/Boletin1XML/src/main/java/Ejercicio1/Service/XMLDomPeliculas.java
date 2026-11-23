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
        List<Pelicula> peliculas = new ArrayList<Pelicula>();
        Document doc = getDocumentFromXML(nombreFichero);

        if (doc != null) {
            NodeList nodos = doc.getElementsByTagName("Pelicula");
            for (int i = 0; i < nodos.getLength(); i++) {
                Node nodo = nodos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nodo;
                    String titulo = obtenerTexto(elem, "Titulo");
                    String duracion = obtenerTexto(elem, "Duracion");
                    String genero = obtenerTexto(elem, "Genero");
                    String sinopsis = obtenerTexto(elem, "sinopsis");
                    String fecha = obtenerTexto(elem, "Fecha");
                    String director = obtenerTexto(elem, "Director");
                    List<String> listaActores = obtenerListaActores(elem);

                    Pelicula p = new Pelicula(titulo, duracion, genero, sinopsis, fecha, director, listaActores);
                    peliculas.add(p);
                }
            }
        }

        return peliculas;
    }

    private List<String> obtenerListaActores(Element padrePelicula) {
        List<String> actores = new ArrayList<String>();
        NodeList nodosActores = padrePelicula.getElementsByTagName("Actor");
        for (int i = 0; i < nodosActores.getLength(); i++) {
            Node nodoActor = nodosActores.item(i);
            if (nodoActor != null && nodoActor.getNodeType() == Node.ELEMENT_NODE) {
                String nombreActor = nodoActor.getTextContent().trim();
                if (!nombreActor.isEmpty()) {
                    actores.add(nombreActor);
                }
            }
        }
        return actores;
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