package Ejercicio1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanzadorContadoresPalabras {

    public static void main(String[] args) {

            String ficheroEntrada = "src/main/resources/Fichero.txt";
            compila();
            ejecuta(ficheroEntrada);
        
    }

    public static void compila() {
        try {
            ProcessBuilder pb = new ProcessBuilder("javac", "Ejercicio1.ProcesadorFichero.java");
            pb.inheritIO();
            Process proceso = pb.start();
            
            int codigoRetorno = proceso.waitFor();
            if (codigoRetorno == 0) {
                System.out.println("[Lanzador] Compilación realizada con éxito.");
            }
        } catch (IOException | InterruptedException e) {
        }
    }

    public static void ejecuta(String ficheroEntrada) {
        String[] palabras = { "servicio", "hilo", "proceso", "multihilo", "concurrencia" };
        List<Process> listaProcesos = new ArrayList<>();

        System.out.println("[Lanzador] Lanzando procesos hijos...");

        for (String palabra : palabras) {
            String ficheroResultado = palabra + ".txt";
            try {
                ProcessBuilder pb = new ProcessBuilder("java", "Ejercicio1.ProcesadorFichero", ficheroEntrada, palabra);
                pb.inheritIO();
                
                Process proceso = pb.start();
                listaProcesos.add(proceso);
                
                System.out.println("Proceso lanzado para: '" + palabra + "' (Guardando en " + ficheroResultado + ")");
            } catch (IOException e) {
            }
        }

        for (int i = 0; i < listaProcesos.size(); i++) {
            try {
                listaProcesos.get(i).waitFor();
            } catch (InterruptedException e) {
            }
        }

        System.out.println("[Lanzador] Todos los procesos hijos han finalizado su ejecución.");
    }
}