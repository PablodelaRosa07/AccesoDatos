package Models;

import java.time.LocalDate;

import Repository.RepositorioConversaciones;

public class ServicioConversaciones {
    private RepositorioConversaciones repositorioConversaciones;

    public ServicioConversaciones() {
        this.repositorioConversaciones = new RepositorioConversaciones();
    }

    public boolean registraNuevaConversacion(TipoAgente tipo, String pregunta, String respuesta) {
        boolean exito = false;
        if (tipo != null && pregunta != null && !pregunta.isEmpty() && respuesta != null && !respuesta.isEmpty()) {
            repositorioConversaciones.agregaConversacion(tipo, pregunta, respuesta);
            exito = true;
        }
        return exito;
    }

    public Conversacion getRecuperaConversacion(TipoAgente tipo, String pregunta, LocalDate fecha) {
        Conversacion conversacion = null;
        try {
            conversacion = repositorioConversaciones.getConversacion(fecha, tipo, pregunta);
        } catch (Exception e) {
        }
        return conversacion;
    }

    public boolean eliminaConversacion(LocalDate fecha, TipoAgente tipo, String pregunta) {
        boolean eliminada = false;
        try {
            repositorioConversaciones.eliminaConversacion(fecha, tipo, pregunta);
            eliminada = true;
        } catch (Exception e) {
        }
        return eliminada;
    }

    public boolean incrementaNumeroValoraciones(LocalDate fecha, TipoAgente tipo, String pregunta) {
        boolean incrementado = false;
        try {
            repositorioConversaciones.incrementaNumeroValoraciones(fecha, tipo, pregunta, 1.0);
            incrementado = true;
        } catch (Exception e) {
        }
        return incrementado;
    }

    public double getValoracionMediaParaHumanos() {
        return calcularMediaPorTipo(TipoAgente.HUMANO);
    }

    public double getValoracionMediaParaBots() {
        return calcularMediaPorTipo(TipoAgente.IA);
    }

    public double calcularMediaPorTipo(TipoAgente tipo) {
        double suma = 0;
        int contador = 0;
        double media = 0.0;

        for (Conversacion conversacion : repositorioConversaciones.getTodas()) {
            if (conversacion.getTipoAgente() == tipo) {
                suma += conversacion.getNumValoracionesPositivas();
                contador++;
            }
        }

        if (contador > 0) {
            media = suma / contador;
        }

        return media;
    }
}