package Repository;

import Exceptions.RutaNoValidaException;
import Models.InformacionRutaModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RutaRepository {

    public InformacionRutaModel obtenerInformacionRuta(String rutaEscrita) throws RutaNoValidaException {
        File file = new File(rutaEscrita);

        if (!file.exists()) {
            throw new RutaNoValidaException("La ruta especificada no existe: " + rutaEscrita);
        }

        InformacionRutaModel info = new InformacionRutaModel();

        info.setRutaEscrita(rutaEscrita);
        
        if (file.getName().isEmpty()) {
            info.setNombre(rutaEscrita);
        } else {
            info.setNombre(file.getName());
        }

        info.setRutaAbsoluta(file.getAbsoluteFile().toPath());
        info.setRutaCanonica(obtenerRutaCanonica(file));

        if (file.getParentFile() != null) {
            info.setDirectorioPadre(file.getParentFile().toPath());
        } else {
            info.setDirectorioPadre(null);
        }

        if (file.isDirectory()) {
            info.setTipo("Directorio");
        } else {
            info.setTipo("Fichero");
        }

        info.setLectura(file.canRead());
        info.setEscritura(file.canWrite());
        info.setEjecucion(file.canExecute());
        info.setOculto(file.isHidden());

        info.setTamanoBytes(file.length());
        info.setNumeroElementos(contarElementos(file));

        info.setFechaUltimaModificacion(formatearFecha(file.lastModified()));

        return info;
    }


    private String obtenerRutaCanonica(File file) {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            return "No accesible";
        }
    }

    private Long contarElementos(File file) {
        if (file.isDirectory()) {
            String[] contenido = file.list();
            if (contenido != null) {
                return (long) contenido.length;
            }
        }
        return 0L;
    }

    private String formatearFecha(long milisegundos) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date(milisegundos));
    }
}