package Controller;

import java.time.LocalDate;

import Models.Conversacion;
import Models.ServicioConversaciones;
import Models.TipoAgente;

public class GestionaPeticionesAChat {
    public static void main(String[] args) {
        ServicioConversaciones servicio = new ServicioConversaciones();

        servicio.registraNuevaConversacion(TipoAgente.IA, "¿Cómo se restablece la contraseña?", "Haga clic en 'Olvidé mi contraseña'.");
        servicio.registraNuevaConversacion(TipoAgente.HUMANO, "¿Cuál es el horario de atención?", "Atendemos de 9:00 a 18:00.");

        LocalDate hoy = LocalDate.now();

        servicio.incrementaNumeroValoraciones(hoy, TipoAgente.IA, "¿Cómo se restablece la contraseña?");
        servicio.incrementaNumeroValoraciones(hoy, TipoAgente.HUMANO, "¿Cuál es el horario de atención?");

        System.out.println("Valoración Media para IA: " + servicio.getValoracionMediaParaBots());
        System.out.println("Valoración Media para Humanos: " + servicio.getValoracionMediaParaHumanos());

        Conversacion conversacion = servicio.getRecuperaConversacion(TipoAgente.IA, "¿Cómo se restablece la contraseña?", hoy);
        if (conversacion != null) {
            System.out.println("Conversación recuperada: " + conversacion);
        }

        boolean eliminada = servicio.eliminaConversacion(hoy, TipoAgente.IA, "¿Cómo se restablece la contraseña?");
        System.out.println("¿Eliminada con éxito?: " + eliminada);
    }
}
