package Ejercicio1.Service;

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

import Ejercicio1.Models.Empleado;
import Ejercicio1.Models.Puesto;

public class UtilesXMLDom {

	private static final Logger logger = LogManager.getLogger(UtilesXMLDom.class);

	public boolean guardarEmpleadosEnXML(List<Empleado> listaEmpleados, String rutaFichero) {
		boolean guardadoCorrectamente = false; // Indica al final si el fichero se guardó bien o no

		if (listaEmpleados != null && rutaFichero != null && !rutaFichero.isEmpty()) { // Comprueba que la lista no es nula ni está vacía, primero va empty, luego null
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				Document documento = builder.newDocument();

				Element raizEmpleados = documento.createElement("empleados"); // Crea la etiqueta raíz <empleados> y
				documento.appendChild(raizEmpleados); // la añade al documento.

				for (int i = 0; i < listaEmpleados.size(); i++) { // Recorre la lista de empleados uno por uno usando un índice i.
					Empleado empleado = listaEmpleados.get(i); // Obtiene el empleado en la posición i

					if (empleado != null) { // Aqui ya está dentro de la etiqueta empleados, va iterando por cada empleado para meter sus datos
						Element elementoEmpleado = documento.createElement("empleado"); // Crea la etiqueta
						if (empleado.getIdentificador() != null && !empleado.getIdentificador().trim().isEmpty()) {
							elementoEmpleado.setAttribute("id", String.valueOf(empleado.getIdentificador())); // Mete el atributo (dato). Lo pasa a String
						}
						raizEmpleados.appendChild(elementoEmpleado); // Mete este <empleado> dentro de <empleados>

						Element elementoNombre = documento.createElement("nombreApellidos");
						elementoNombre.setTextContent(empleado.getNombreApellidos());
						elementoEmpleado.appendChild(elementoNombre);

						Element elementoEdad = documento.createElement("edad");
						elementoEdad.setTextContent(String.valueOf(empleado.getEdad()));
						elementoEmpleado.appendChild(elementoEdad);

						Element elementoEmpresa = documento.createElement("empresa");
						elementoEmpresa.setTextContent(empleado.getEmpresa());
						elementoEmpleado.appendChild(elementoEmpresa);

						List<Puesto> puestos = empleado.getPuestos(); // Recupera la lista de objetos Puesto asociada al empleado.
						if (puestos != null && !puestos.isEmpty()) { // Como puestos contiene varias etiquetas puesto dentro:
							Element elementoPuestos = documento.createElement("puestos");
							elementoEmpleado.appendChild(elementoPuestos);

							for (int j = 0; j < puestos.size(); j++) {
								Puesto puesto = puestos.get(j);
								if (puesto != null) {
									Element elementoPuesto = documento.createElement("puesto");
									if (puesto.getIdPuesto() != null && !puesto.getIdPuesto().trim().isEmpty()) {
										elementoPuesto.setAttribute("id", String.valueOf(puesto.getIdPuesto()));
									}
									elementoPuestos.appendChild(elementoPuesto);

									Element elementoNombrePuesto = documento.createElement("nombrePuesto");
									elementoNombrePuesto.setTextContent(puesto.getNombrePuesto());
									elementoPuesto.appendChild(elementoNombrePuesto);

									if (puesto.getDepartamento() != null && !puesto.getDepartamento().trim().isEmpty()) {
										Element elementoDepartamento = documento.createElement("departamento");
										elementoDepartamento.setTextContent(puesto.getDepartamento());
										elementoPuesto.appendChild(elementoDepartamento);
									}

									Element elementoSalario = documento.createElement("salario");
									elementoSalario.setTextContent(String.valueOf(puesto.getSalario()));
									elementoPuesto.appendChild(elementoSalario);
								}
							}
						}
					}
				}

				TransformerFactory transformerFactory = TransformerFactory.newInstance(); // Preguntar a Soraya --- Crea el transformador que convertirá el árbol DOM en texto XML.
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // Indica que el archivo se codifique en UTF-8.
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

				DOMSource origen = new DOMSource(documento); // Preguntar a Soraya --- Envuelve el Document en memoria como origen de datos.
				StreamResult destino = new StreamResult(new File(rutaFichero)); // Preguntar a Soraya --- Indica el archivo de destino en disco donde se escribirá el XML.
				transformer.transform(origen, destino); // Preguntar a Soraya --- Ejecuta la escritura real del XML en el fichero.

				guardadoCorrectamente = true;
			} catch (Exception e) {
				logger.error("Error al guardar los empleados en el fichero XML: " + rutaFichero, e);
				guardadoCorrectamente = false;
			}
		} else {
			logger.error("La lista de empleados o la ruta del fichero no son validas");
			guardadoCorrectamente = false;
		}

		return guardadoCorrectamente;
	}

	public List<Empleado> leerEmpleadosDesdeXML(String rutaFichero) {
		List<Empleado> empleadosLeidos = new ArrayList<Empleado>();

		if (rutaFichero != null && !rutaFichero.trim().isEmpty()) {
			File fichero = new File(rutaFichero);
			if (fichero.exists() && fichero.isFile()) {
				try {
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					Document documento = builder.parse(fichero);
					documento.getDocumentElement().normalize();

					NodeList nodosEmpleado = documento.getElementsByTagName("empleado");

					for (int i = 0; i < nodosEmpleado.getLength(); i++) {
						Node nodoEmpleado = nodosEmpleado.item(i);

						if (nodoEmpleado != null && nodoEmpleado.getNodeType() == Node.ELEMENT_NODE) {
							try {
								Element elementoEmpleado = (Element) nodoEmpleado;
								String idEmpleado = elementoEmpleado.getAttribute("id");
								String nombreApellidos = obtenerTextoHijoDirecto(elementoEmpleado, "nombreApellidos");
								String edadTexto = obtenerTextoHijoDirecto(elementoEmpleado, "edad");
								String empresa = obtenerTextoHijoDirecto(elementoEmpleado, "empresa");

								int edad = 0;
								if (!edadTexto.isEmpty()) {
									edad = Integer.parseInt(edadTexto);
								}

								List<Puesto> puestosLeidos = leerPuestosDeEmpleado(elementoEmpleado);

								Empleado empleado = new Empleado(idEmpleado, nombreApellidos, edad, empresa, puestosLeidos);
								empleadosLeidos.add(empleado);
							} catch (Exception e) {
								logger.error("Error al procesar el empleado en la posicion " + (i + 1) + " del XML", e);
							}
						}
					}
				} catch (Exception e) {
					logger.error("Error general al parsear el fichero XML: " + rutaFichero, e);
				}
			} else {
				logger.error("El fichero XML no existe: " + rutaFichero);
			}
		} else {
			logger.error("La ruta del fichero XML no es valida");
		}

		return empleadosLeidos;
	}

	private List<Puesto> leerPuestosDeEmpleado(Element elementoEmpleado) {
		List<Puesto> puestos = new ArrayList<Puesto>();
		NodeList nodosPuesto = elementoEmpleado.getElementsByTagName("puesto");

		if (nodosPuesto.getLength() == 0) {
			throw new IllegalArgumentException("El empleado no tiene ningun puesto asociado");
		}

		for (int j = 0; j < nodosPuesto.getLength(); j++) {
			Node nodoPuesto = nodosPuesto.item(j);
			if (nodoPuesto != null && nodoPuesto.getNodeType() == Node.ELEMENT_NODE) {
				Element elementoPuesto = (Element) nodoPuesto;
				String idPuesto = elementoPuesto.getAttribute("id");
				String nombrePuesto = obtenerTextoHijoDirecto(elementoPuesto, "nombrePuesto");
				String departamento = obtenerTextoHijoDirecto(elementoPuesto, "departamento");
				String salarioTexto = obtenerTextoHijoDirecto(elementoPuesto, "salario");

				if (salarioTexto.isEmpty()) {
					throw new IllegalArgumentException("El salario del puesto no puede estar vacio");
				}

				double salario = Double.parseDouble(salarioTexto);
				Puesto puesto = new Puesto(idPuesto, nombrePuesto, departamento, salario);
				puestos.add(puesto);
			}
		}

		return puestos;
	}

	private String obtenerTextoHijoDirecto(Element padre, String nombreEtiqueta) {
		String resultado = "";
		if (padre != null && nombreEtiqueta != null) {
			NodeList hijos = padre.getChildNodes();
			for (int i = 0; i < hijos.getLength(); i++) {
				Node hijo = hijos.item(i);
				if (hijo != null && hijo.getNodeType() == Node.ELEMENT_NODE
						&& nombreEtiqueta.equals(hijo.getNodeName())) {
					if (hijo.getTextContent() != null) {
						resultado = hijo.getTextContent().trim();
					}
				}
			}
		}
		return resultado;
	}
}