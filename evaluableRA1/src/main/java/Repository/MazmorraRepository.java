package Repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import Models.Epoca;
import Models.Mazmorra;

public class MazmorraRepository {
	private static final Logger logger = LogManager.getLogger(MazmorraRepository.class);

	private List<Mazmorra> listaMazmorrasBBDD;

	public MazmorraRepository() {
		this.listaMazmorrasBBDD = new ArrayList<Mazmorra>();
	}
	
	public List<Mazmorra> findAll(String rutaFichero) throws Exception {
		List<Mazmorra> listaMazmorras = new ArrayList<Mazmorra>();
        Gson gson = new Gson();

        try (FileReader reader = new FileReader(rutaFichero)) {
        	Mazmorra[] arrayMazmorras = gson.fromJson(reader, Mazmorra[].class);
            if (arrayMazmorras != null) {
                listaMazmorras = new ArrayList<>(Arrays.asList(arrayMazmorras));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        List<Mazmorra> resultado = listaMazmorras;
        return resultado;
	}
	
	public List<Mazmorra> guardar(Mazmorra mazmorra) {
        listaMazmorrasBBDD.add(mazmorra);
        return listaMazmorrasBBDD;
    }
	
	public List<Mazmorra> agruparPorEpoca(Epoca epoca) {
        List<Mazmorra> listaMazmorrasFiltradas = new ArrayList<>();

        for (Mazmorra mazmorra : listaMazmorrasBBDD) {
            if (mazmorra.getNombre() != "" && mazmorra.getEpoca() != null) {
                if (mazmorra.getEpoca().equals(epoca.toString())) {
                	listaMazmorrasFiltradas.add(mazmorra);
                }
            }
        }
        
        logger.info(listaMazmorrasFiltradas);
        return listaMazmorrasFiltradas;
    }
	
	

	
	
	
}
