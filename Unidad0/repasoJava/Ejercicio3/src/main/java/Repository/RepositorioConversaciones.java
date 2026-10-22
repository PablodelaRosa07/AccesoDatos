package Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import Models.Conversacion;
import Models.TipoAgente;

public class RepositorioConversaciones {
    private List<Conversacion> conversaciones;

    public RepositorioConversaciones() {
        this.conversaciones = new ArrayList<>();
    }

    public void agregaConversacion(TipoAgente tipo, String pregunta, String respuesta) {
        Conversacion nueva = new Conversacion(respuesta, tipo, pregunta, respuesta, LocalDate.now(), 0);
        this.conversaciones.add(nueva);
    }

    public Conversacion getConversacion(LocalDate fecha, TipoAgente tipo, String pregunta) throws Exception {
        for (Conversacion conversacion : conversaciones) {
            if (conversacion.getFechaConversacion().equals(fecha) && conversacion.getTipoAgente() == tipo && conversacion.getPregunta().equals(pregunta)) {
                return conversacion;
            }
        }
        throw new Exception("No existe ninguna conversación para los datos especificados.");
    }

    public boolean contieneConversacion(Conversacion conversacion) {
        return conversaciones.contains(conversacion);
    }

    public void eliminaConversacion(LocalDate fecha, TipoAgente tipo, String pregunta) throws Exception {
        Conversacion eliminar = getConversacion(fecha, tipo, pregunta);
        conversaciones.remove(eliminar);
    }

    public void incrementaNumeroValoraciones(LocalDate fecha, TipoAgente tipo, String pregunta, double valoracion) throws Exception {
        Conversacion conversacion = getConversacion(fecha, tipo, pregunta);
        conversacion.incrementarValoracion();
    }

    public List<Conversacion> getTodas() {
        return conversaciones;
    }
}