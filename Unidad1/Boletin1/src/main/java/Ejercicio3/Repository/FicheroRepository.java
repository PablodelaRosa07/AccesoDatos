package Repository;

import Exceptions.GestionFicherosException;
import java.io.File;
import java.io.IOException;

public class FicheroRepository {

    private File miDirectorio;
    private File fLectura;
    private File fNormal;
    private File fRenombrado;

    public boolean crearDirectorioUsuario() throws GestionFicherosException {
        boolean resultado = false;
        String userHome = System.getProperty("user.home");
        File directorioPadre = new File(userHome);

        miDirectorio = new File(directorioPadre, "miDirectorio");

        if (!miDirectorio.exists()) {
            resultado = miDirectorio.mkdir();
            if (!resultado) {
                throw new GestionFicherosException("No se pudo crear el directorio: " + miDirectorio.getAbsolutePath());
            }
        } else {
            resultado = true;
        }

        return resultado;
    }

    public boolean crearFicheros() throws IOException {
        boolean resultado = false;
        fLectura = new File(miDirectorio, "lectura.txt");
        fNormal = new File(miDirectorio, "normal.txt");

        boolean creado1 = fLectura.createNewFile();
        boolean creado2 = fNormal.createNewFile();

        if (creado1 || fLectura.exists()) {
            if (creado2 || fNormal.exists()) {
                resultado = true;
            }
        }

        return resultado;
    }

    public boolean marcarSoloLectura() {
        boolean resultado = false;
        if (fLectura != null) {
            if (fLectura.exists()) {
                resultado = fLectura.setReadOnly();
            }
        }
        return resultado;
    }

    public String obtenerPermisos(File archivo) {
        String lectura = "NO";
        String escritura = "NO";
        String ejecucion = "NO";

        if (archivo != null) {
            if (archivo.canRead()) {
                lectura = "SÍ";
            }
            if (archivo.canWrite()) {
                escritura = "SÍ";
            }
            if (archivo.canExecute()) {
                ejecucion = "SÍ";
            }
        }

        return "Lectura: " + lectura + " | Escritura: " + escritura + " | Ejecución: " + ejecucion;
    }

    public boolean renombrarNormal() {
        boolean resultado = false;
        fRenombrado = new File(miDirectorio, "renombrado.txt");

        if (fNormal != null) {
            if (fNormal.exists()) {
                resultado = fNormal.renameTo(fRenombrado);
            }
        }

        return resultado;
    }

    public boolean borrarLectura() {
        boolean borrado = false;

        if (fLectura != null) {
            if (fLectura.exists()) {
                borrado = fLectura.delete();

                if (!borrado) {
                    boolean permisoCambiado = fLectura.setWritable(true);
                    if (permisoCambiado) {
                        borrado = fLectura.delete();
                    }
                }
            } else {
                borrado = true;
            }
        }

        return borrado;
    }

    public String[] obtenerContenidoDirectorio() {
        String[] contenido = new String[0];

        if (miDirectorio != null) {
            if (miDirectorio.exists()) {
                String[] listado = miDirectorio.list();
                if (listado != null) {
                    contenido = listado;
                }
            }
        }

        return contenido;
    }

    public File getfLectura() {
        return fLectura;
    }

    public File getfNormal() {
        return fNormal;
    }

    public File getMiDirectorio() {
        return miDirectorio;
    }
}