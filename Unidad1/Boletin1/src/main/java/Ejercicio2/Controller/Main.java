package Controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Exceptions.RutaNoValidaException;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce una ruta: ");
        String rutaEscrita = sc.nextLine();

        try {
            mostrarInformacion(rutaEscrita);
        } catch (RutaNoValidaException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    public static void mostrarInformacion(String rutaEscrita) throws RutaNoValidaException {
        File file = new File(rutaEscrita);

        if (!file.exists()) {
            throw new RutaNoValidaException("La ruta especificada no existe: " + rutaEscrita);
        }

        System.out.println("INFORMACIÓN DE LA RUTA");
        System.out.println("Nombre: " + (file.getName().isEmpty() ? rutaEscrita : file.getName()));
        System.out.println("Ruta escrita: " + rutaEscrita);
        System.out.println("Ruta absoluta: " + file.getAbsolutePath());
        System.out.println("Ruta canónica: " + obtenerCanonica(file));
        System.out.println("Directorio padre: " + (file.getParent() != null ? file.getParent() : "No tiene"));
        System.out.println("Tipo: " + (file.isDirectory() ? "Directorio" : "Fichero"));
        System.out.printf("Permisos: Lectura [] | Escritura [] | Ejecución []",
                file.canRead() ? "SÍ" : "NO",
                file.canWrite() ? "SÍ" : "NO",
                file.canExecute() ? "SÍ" : "NO");
        System.out.println("Oculto: " + (file.isHidden() ? "SÍ" : "NO"));
        System.out.println("Tamaño en bytes: " + file.length() + " bytes");

        if (file.isDirectory()) {
            String[] lista = file.list();
            int numElementos = (lista != null) ? lista.length : 0;
            System.out.println("Número de elementos: " + numElementos);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("Fecha de última modificación: " + sdf.format(new Date(file.lastModified())));
    }

    private static String obtenerCanonica(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            return "No accesible";
        }
    }
}