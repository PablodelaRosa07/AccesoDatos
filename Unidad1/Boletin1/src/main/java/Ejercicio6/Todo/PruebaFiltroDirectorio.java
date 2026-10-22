package Todo;

import java.io.File;
import Todo.FiltroExtensionTxt;

public class PruebaFiltroDirectorio {
    public static void main(String[] args) {

        String rutaCarpeta = "."; 
        
        File directorio = new File(rutaCarpeta);

        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("Error: La ruta especificada no es un directorio válido.");
            
        }

        System.out.println("--- 1. Usando la clase separada ---");
        FilenameFilter filtroClase = new FiltroExtensionTxt();
        File[] resultadosClase = directorio.listFiles(filtroClase);
        
        mostrarResultados(resultadosClase);


        System.out.println("\n--- 2. Usando una Expresión Lambda ---");
        // Forma directa, sin clases auxiliares
        File[] resultadosLambda = directorio.listFiles((dir, name) -> name != null && name.toLowerCase().endsWith(".txt"));
        
        mostrarResultados(resultadosLambda);
    }

    private void mostrarResultados(File[] ficheros) {
        if (ficheros == null) {
            System.out.println("Ocurrió un error al leer el directorio (devuelve null).");
            return;
        }

        if (ficheros.length == 0) {
            System.out.println("No se encontraron ficheros .txt en este directorio.");
            return;
        }

        for (File f : ficheros) {
            if (f.isFile()) {
                System.out.println(" - Archivo encontrado: " + f.getName());
            }
        }
    }
