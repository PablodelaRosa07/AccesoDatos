package Models;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import Repository.SinLuz;
import Exceptions.EldenException;

public class RegistroEldenRing {
    private Map<Integer, SinLuz> mapaSinLuz;

    public RegistroEldenRing() {
        this.mapaSinLuz = new TreeMap<>();
    }

    public void registrarSinLuz(SinLuz sinLuz) throws EldenException {
        if (mapaSinLuz.containsKey(sinLuz.getId())) {
            throw new EldenException("El SinLuz con ID " + sinLuz.getId() + " ya está registrado.");
        }
        mapaSinLuz.put(sinLuz.getId(), sinLuz);
    }

    public SinLuz buscarPorId(int id) throws EldenException {
        SinLuz encontrado = mapaSinLuz.get(id);
        if (encontrado == null) {
            throw new EldenException("No se encontró ningún SinLuz con ID: " + id);
        }
        return encontrado;
    }

    public void agregarEncuentroASinLuz(int idSinLuz, Encuentro encuentro) throws EldenException {
        SinLuz personaje = buscarPorId(idSinLuz);
        personaje.agregarEncuentro(encuentro);
    }


    public List<SinLuz> obtenerSinLuzPorDificultad(int dificultadMinima) {
        List<SinLuz> resultado = new ArrayList<>();

        for (Map.Entry<Integer, SinLuz> entrySinLuz : mapaSinLuz.entrySet()) {
            SinLuz personaje = entrySinLuz.getValue();
            boolean cumpleFiltro = false;

            for (Map.Entry<String, Encuentro> entryEncuentro : personaje.getMapaEncuentros().entrySet()) {
                Encuentro enc = entryEncuentro.getValue();
                if (enc.getDificultad() >= dificultadMinima) {
                    cumpleFiltro = true;
                }
            }

            if (cumpleFiltro) {
                resultado.add(personaje);
            }
        }

        return resultado;
    }

    public Map<Integer, SinLuz> getMapaSinLuz() {
        return mapaSinLuz;
    }
}