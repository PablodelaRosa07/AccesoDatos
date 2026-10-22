
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LanzadorContadoresPalabras {

    private  String[] palabras;
    private  String ficheroEntrada;

    public LanzadorContadoresPalabras(String[] palabras, String ficheroEntrada) {
        this.palabras = palabras;
        this.ficheroEntrada = ficheroEntrada;
    }

    public static void main(String[] args) {
        String[] misPalabras = { "servicio", "hilo", "proceso", "multihilo", "concurrencia" };
        String archivoTexto = "texto.txt";

        LanzadorContadoresPalabras lanzador = new LanzadorContadoresPalabras(misPalabras, archivoTexto);

        try {
            lanzador.compila();
            
            lanzador.ejecuta();
            
        } catch (Exception e) {
            System.err.println("Ocurrió un error en la ejecución del lanzador: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void compila() throws IOException, InterruptedException {
        System.out.println("Compilando el fichero ProcesadorFichero.java...");
        
        ProcessBuilder pb = new ProcessBuilder("javac", "ProcesadorFichero.java");
        Process proceso = pb.start();
        
        int codigoRetorno = proceso.waitFor();
        
        if (codigoRetorno == 0) {
            System.out.println("¡Compilación completada con éxito!");
        } else {
            System.err.println("Error durante la compilación del código.");
        }
    }

    /**
     */
    public void ejecuta() {
        List<Process> listaProcesos = new ArrayList<>();

        System.out.println("Iniciando la ejecución de los procesos hijos...");

        for (int i = 0; i < palabras.length; i++) {
            String palabraActual = palabras[i];
            String ficheroResultado = "resultado_" + palabraActual + ".txt";

            try {
                ProcessBuilder pb = new ProcessBuilder("java", "ProcesadorFichero", ficheroEntrada, palabraActual, ficheroResultado
                );

                pb.inheritIO();
                Process p = pb.start();
                listaProcesos.add(p);
                
                System.out.println("Proceso hijo lanzado para la palabra: [" + palabraActual + 
                                   "] (Guardando en: " + ficheroResultado + ")");

            } catch (IOException e) {
                System.err.println("No se pudo iniciar el proceso para la palabra: " + palabraActual);
                e.printStackTrace();
            }
        }

        System.out.println("Esperando a que finalicen todos los procesos hijos...");
        for (int i = 0; i < listaProcesos.size(); i++) {
            try {
                listaProcesos.get(i).waitFor();
                System.out.println("Proceso de la palabra [" + palabras[i] + "] finalizado.");
            } catch (InterruptedException e) {
                System.err.println("El proceso hijo fue interrumpido.");
                e.printStackTrace();
            }
        }

        System.out.println("Todos los procesos han terminado correctamente");
    }
}