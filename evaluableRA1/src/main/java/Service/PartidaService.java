package Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Models.Epoca;
import Models.Jefe;
import Models.Mazmorra;
import Repository.MazmorraRepository;


public class PartidaService {
	private static final Logger logger = LogManager.getLogger(PartidaService.class);
    private MazmorraRepository repositorio;
    private PartidaService servicio;

    public PartidaService(MazmorraRepository repositorio) {
        this.repositorio = repositorio;
    }
    
    private boolean validarMazmorra(Mazmorra mazmorra) {
        boolean valido = true;

        if (mazmorra.getNombre() == null || mazmorra.getNombre().equals("") || mazmorra.getNombre().isEmpty())  {
        	if (!mazmorra.getEpoca().equals("NINO")) {
        		if (!mazmorra.getEpoca().equals("ADULTO")) {
        			if (!mazmorra.getEpoca().equals("AMBAS")) {
        				valido = false;
        			}
        		}
        	}
            
        } 

        return valido; 
    }
    
    
    public void procesarYAlmacenarMazmorras(String rutaFichero) throws Exception {
        List<Mazmorra> mazmorrasCargadas = repositorio.findAll(rutaFichero);

        for (Mazmorra mazmorra : mazmorrasCargadas) {
            if (validarMazmorra(mazmorra)) {
                repositorio.guardar(mazmorra);
                logger.info("Mazmorra guardada exitosamente");
            } else {
                String nombre;
                if (mazmorra.getNombre() != null) {
                    nombre = mazmorra.getNombre();
                } else {
                    nombre = "Título desconocido";
                }
                logger.error("No se ha podido realizar el alta de la mazmorra "+ mazmorra.getNombre() +" por fallos en la información.");
            }
        }
        
    }
    
    
    public List<Mazmorra> completarMazmorra(String rutaFichero, String nombre, double tiempo) throws Exception{
    	List<Mazmorra> listaMazmorras = repositorio.findAll(rutaFichero);
    	
		Mazmorra mazmorraEncontrada = null;
		for (Mazmorra mazmorra : listaMazmorras) {
			if (mazmorra.getNombre().equals(nombre.toString())) {
				mazmorraEncontrada = mazmorra;
			}
		}
		
		if(mazmorraEncontrada == null || tiempo <= 0) {
			logger.error("No existe la mazmorra");
		}
		else {
			if (!mazmorraEncontrada.isCompletada()) {
				mazmorraEncontrada.setCompletada(true);
				mazmorraEncontrada.setTiempoEnCompletar(0);
			}
			else {
				if (mazmorraEncontrada.getTiempoEnCompletar() > tiempo) {
					mazmorraEncontrada.setTiempoEnCompletar(tiempo);
				}
			}
		}
		System.out.println(listaMazmorras);
		return listaMazmorras;
		
		
	}
    
    
    
    public boolean exportarCSV(String nombreFichero, String rutaFichero) throws Exception {
        boolean exportado = false;
        
        List<Mazmorra> mazmorrasFiltradas = repositorio.findAll(rutaFichero);

        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreFichero))) {

            for (Mazmorra mazmorra : mazmorrasFiltradas) {
                
                String nombreMazmorra;
                if (mazmorra != null && mazmorra.getNombre() != null) {
                    nombreMazmorra = mazmorra.getNombre();
                } else {
                    nombreMazmorra = "";
                }

                Epoca epoca;
                if (mazmorra != null && mazmorra.getEpoca() != null) {
                    epoca = mazmorra.getEpoca();
                } else {
                    epoca = null;
                }

                
                String jefe;
                if (mazmorra != null && mazmorra.getJefe() != null) {
                	jefe = mazmorra.getJefe().getNombre();
                } else {
                	jefe = null;
                }
                
                
                double mejorTiempo;
                if (mazmorra != null && mazmorra.getTiempoEnCompletar() <= 0) {
                	mejorTiempo = mazmorra.getTiempoEnCompletar();
                } else {
                	mejorTiempo = 0;
                }
                

                writer.println(nombreMazmorra + ";" + epoca +  ";"+ jefe + ";" +mejorTiempo);
            }

            logger.info("Exportación completada exitosamente. Se han guardado las mazmorras en CSV");
            exportado = true;
        } catch (IOException e) {
            logger.error("Error al generar el fichero CSV");
        }

        return exportado; 
    }
    
    
    
    
}
