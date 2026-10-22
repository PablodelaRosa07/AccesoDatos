package Controller;

import Exceptions.GestionFicherosException;
import Repository.FicheroRepository;
import java.io.File;
import java.io.IOException;

public class FicheroController {

    private final FicheroRepository repository;

    public FicheroController() {
        this.repository = new FicheroRepository();
    }

    public void ejecutarSecuencia() {
        System.out.println("INICIO DE LA SECUENCIA DE PASOS");

        boolean continuar = false;

        try {
            boolean exitoDir = repository.crearDirectorioUsuario();
            if (exitoDir) {
                System.out.println("[Paso 1] ÉXITO: Directorio 'miDirectorio' preparado en: " + repository.getMiDirectorio().getAbsolutePath());
                continuar = true;
            } else {
                System.out.println("[Paso 1] ERROR: No se pudo crear el directorio.");
            }
        } catch (GestionFicherosException e) {
            System.out.println("[Paso 1] ERROR: " + e.getMessage());
        }

        if (continuar) {

            try {
                boolean ficherosCreados = repository.crearFicheros();
                if (ficherosCreados) {
                    System.out.println("[Paso 2] ÉXITO: Ficheros 'lectura.txt' y 'normal.txt' creados correctamente.");
                } else {
                    System.out.println("[Paso 2] ERROR: No se pudieron crear los ficheros.");
                }
            } catch (IOException e) {
                System.out.println("[Paso 2] ERROR I/O: " + e.getMessage());
            }

            boolean soloLecturaOk = repository.marcarSoloLectura();
            if (soloLecturaOk) {
                System.out.println("[Paso 3] ÉXITO: 'lectura.txt' marcado como solo lectura.");
            } else {
                System.out.println("[Paso 3] ERROR: No se pudo cambiar el permiso a solo lectura.");
            }

            System.out.println("[Paso 4] Permisos actuales:");
            File fLectura = repository.getfLectura();
            File fNormal = repository.getfNormal();

            if (fLectura != null) {
                System.out.println("  - lectura.txt  -> " + repository.obtenerPermisos(fLectura));
            }
            if (fNormal != null) {
                System.out.println("  - normal.txt   -> " + repository.obtenerPermisos(fNormal));
            }

            boolean renombradoOk = repository.renombrarNormal();
            if (renombradoOk) {
                System.out.println("[Paso 5] ÉXITO: 'normal.txt' ha sido renombrado a 'renombrado.txt'.");
            } else {
                System.out.println("[Paso 5] ERROR: No se pudo renombrar el fichero.");
            }

            boolean borradoOk = repository.borrarLectura();
            if (borradoOk) {
                System.out.println("[Paso 6] ÉXITO: 'lectura.txt' ha sido borrado.");
            } else {
                System.out.println("[Paso 6] ERROR: No se pudo borrar 'lectura.txt' tras los intentos.");
            }

            System.out.println("[Paso 7] Contenido final de 'miDirectorio':");
            String[] elementos = repository.obtenerContenidoDirectorio();
            if (elementos.length > 0) {
                for (String elem : elementos) {
                    System.out.println("  - " + elem);
                }
            } else {
                System.out.println("  (El directorio está vacío)");
            }
        }

        System.out.println("FIN DE LA SECUENCIA");
    }
    
    public static void main(String[] args) {
        FicheroController controller = new FicheroController();
        controller.ejecutarSecuencia();
    }
}