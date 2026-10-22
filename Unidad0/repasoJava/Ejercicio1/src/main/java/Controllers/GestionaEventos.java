package Controllers;

import java.time.LocalDate;
import Exceptions.CrafterException;
import Models.Concierto;
import Models.Deportivo;
import Models.Estado;
import Models.Evento;
import Repository.EventosRepository;

public class GestionaEventos {
    
    public static void main(String[] args) throws CrafterException {
        GestionaEventos gestionaEventos = new GestionaEventos();
        
        EventosRepository repo = new EventosRepository(null);
                
        Evento evento1 = new Concierto("EventoSevilla", LocalDate.now(), 1000, 1500, Estado.PENDIENTE, "Artista A");
        Evento evento2 = new Deportivo("EventoSevillaCartuja", LocalDate.now(), 3000, 4800, Estado.PENDIENTE, true);
        
        repo.getListaEventos().add(evento1);
        repo.getListaEventos().add(evento2);
        
        gestionaEventos.modificaEstado(evento1, Estado.PROGRAMADO);
        
        try {
            gestionaEventos.modificaEstado(evento2, Estado.FINALIZADO);
        } 
        catch (CrafterException e) {
            System.out.println("Error esperado en evento2: " + e.getMessage());
        }
        
        gestionaEventos.getPorcentajeOcupacion(evento1);
        gestionaEventos.getPorcentajeOcupacion(evento2);
        
        gestionaEventos.getPorcentajeOcupacionEntradasVendidas(evento1, 100);
        gestionaEventos.getPorcentajeOcupacionEntradasVendidas(evento2, 50);
        

        // 1
        try {
            Deportivo deportivoInvalido = new Deportivo("Partido invalido", LocalDate.now(), 600, 500, Estado.PENDIENTE, true);
        } 
        catch (CrafterException e) {
            System.out.println("Excepción capturada correctamente: " + e.getMessage());
        }

        // 2
        try {
            Concierto concierto1 = new Concierto("Rock Fest", LocalDate.now(), 200, 500, Estado.APLAZADO, "Banda A");
            System.out.println("Porcentaje ocupación vendiendo 50 entradas más: " + concierto1.getPorcentajeOcupacion(50) + "%");
            System.out.println("Información del evento: " + concierto1);

            concierto1.modificaEstado(Estado.PROGRAMADO);
            System.out.println("Nuevo estado tras modificación: " + concierto1.getEstado());
        } catch (CrafterException e) {
            System.out.println("Error no esperado: " + e.getMessage());
        }

        // 3
        try {
            Concierto conciertoAgotado = new Concierto("Pop Night", LocalDate.now(), 500, 500, Estado.PENDIENTE, "Cantante B");
            conciertoAgotado.addArtistaInvitado("Invitado 1");

            try {
                conciertoAgotado.getPorcentajeOcupacion(20);
            } 
            catch (CrafterException e) {
                System.out.println("Excepción capturada al exceder aforo: " + e.getMessage());
            }

            System.out.println("Coste Base: " + conciertoAgotado.calcularCosteBase() + " €");
            System.out.println("% de Ocupación actual: " + conciertoAgotado.getPorcentajeOcupacion() + "%");
        } 
        catch (CrafterException e) {
            System.out.println("Error no esperado: " + e.getMessage());
        }

        // 4
        try {
            Deportivo deportivoGrande = new Deportivo("Gran Clásico", LocalDate.now(), 1000, 5000, Estado.CANCELADO, true);
            System.out.println("Coste Base: " + deportivoGrande.calcularCosteBase() + " €");
            System.out.println("Información del evento: " + deportivoGrande);
        } catch (CrafterException e) {
            System.out.println("Error no esperado: " + e.getMessage());
        }

        // 5

        Deportivo deportivoPequeno = new Deportivo("Torneo Local", LocalDate.of(2026, 8, 10), 100, 500, Estado.PROGRAMADO, false);
        System.out.println("Coste Base: " + deportivoPequeno.calcularCosteBase() + " €");
        System.out.println("Información del evento: " + deportivoPequeno);

        System.out.println("Listado final del repositorio:");
        System.out.println(repo.getListaEventos());

        gestionaEventos.modificaEstado(deportivoPequeno, Estado.PENDIENTE);
    }
    
    public void modificaEstado(Evento evento, Estado nuevoEstado) throws CrafterException {
        Estado estadoActual = evento.getEstado();
        
        if (estadoActual.equals(Estado.PENDIENTE) && !nuevoEstado.equals(Estado.FINALIZADO)) {
            evento.setEstado(nuevoEstado);
        } 
        else if (estadoActual.equals(Estado.APLAZADO) && nuevoEstado.equals(Estado.PROGRAMADO)) {
            evento.setEstado(nuevoEstado);
        } 
        else {
            throw new CrafterException("No es posible pasar al estado: " + nuevoEstado);
        }
    }
    
    public double getPorcentajeOcupacion(Evento evento) {
        double porcentajeOcupacion = (evento.getNumEntradasVendidas() * 100.0) / evento.getCapacidadMaxAsistentes();
        System.out.println("Porcentaje de ocupación en el evento " + evento.getNombre() + " es: " + porcentajeOcupacion + "%");
        return porcentajeOcupacion;
    }
    
    public double getPorcentajeOcupacionEntradasVendidas(Evento evento, int numeroEntradas) throws CrafterException {
        double totalEntradas = evento.getNumEntradasVendidas() + numeroEntradas;

        if (totalEntradas > evento.getCapacidadMaxAsistentes() || numeroEntradas < 0) {
            throw new CrafterException("El número de entradas supera la capacidad máxima del evento.");
        }

        double porcentajeFinal = (totalEntradas * 100.0) / evento.getCapacidadMaxAsistentes();
        System.out.println("Porcentaje de ocupación más entradas vendidas en el evento " + evento.getNombre() + " es: " + porcentajeFinal + "%");
        return porcentajeFinal;
    }
}