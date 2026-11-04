package Ejercicio1.Exceptions;

public class RutaNoValidaException extends Exception {
    public RutaNoValidaException(String mensaje) {
        super(mensaje);
    }
}