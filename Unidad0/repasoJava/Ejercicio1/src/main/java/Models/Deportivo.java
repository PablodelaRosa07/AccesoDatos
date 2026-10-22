package Models;

import java.time.LocalDate;
import Exceptions.CrafterException;

public class Deportivo extends Evento {

    private boolean televisado;

    public Deportivo(String nombre, LocalDate fechaEvento, int numEntradasVendidas, int capacidadMaxAsistentes, Estado estado, boolean televisado) throws CrafterException {
        super(capacidadMaxAsistentes, nombre, fechaEvento, numEntradasVendidas, capacidadMaxAsistentes, estado);
        this.televisado = televisado;
    }

    public boolean esTelevisado() {
        return televisado;
    }

    public void setTelevisado(boolean televisado) {
        this.televisado = televisado;
    }

    @Override
    public double calcularCosteBase() {
        double alquilerEstadio = 0;
        if (getCapacidadMaxAsistentes() >= 3000) {
        	alquilerEstadio = 150000;
        }
        else {
        	alquilerEstadio = 75000;
        }
        if (televisado) {
            alquilerEstadio -= 10000.0;
        }
        return alquilerEstadio;
    }
}