package DiffFolder.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import DiffFolder.Exceptions.DiffFolderException;
import DiffFolder.Exceptions.GestionFicherosException;
import DiffFolder.Models.ResultadoComparacion;
import DiffFolder.Models.ValorComparacion;
 
public class DiffFolder {
 
    private File folder1;
    private File folder2;
 
    public void setFolders(File folder1, File folder2) throws GestionFicherosException {
        validar(folder1, "primera");
        validar(folder2, "segunda");
        this.folder1 = folder1;
        this.folder2 = folder2;
    }
 
    private void validar(File f, String posicion) throws GestionFicherosException {
        if (f == null) {
            throw new GestionFicherosException("La " + posicion + " carpeta es nula");
        }
        if (!f.exists()) {
            throw new GestionFicherosException("La " + posicion + " carpeta no existe");
        }
        if (!f.isDirectory()) {
            throw new GestionFicherosException("La " + posicion + " ruta no es un directorio");
        }
    }
 
    public File getFolder1() {
        return folder1;
    }
 
    public File getFolder2() {
        return folder2;
    }
 
    public Collection<ResultadoComparacion> comparame() {
        Collection<ResultadoComparacion> resultados = new ArrayList<>();
        if (folder1 == null || folder2 == null) {
            System.err.println("comparame() llamado sin haber asignado las carpetas con setFolders");
            return resultados;
        }
 
        String[] nombres1 = listarFicheros(folder1);
        String[] nombres2 = listarFicheros(folder2);
 
        String[] mayor = nombres1.length >= nombres2.length ? nombres1 : nombres2;
        String[] menor = mayor == nombres1 ? nombres2 : nombres1;
 
        Set<String> nombres = new LinkedHashSet<>();
        for (String n : mayor) {
            nombres.add(n);
        }
        for (String n : menor) {
            nombres.add(n);
        }
 
        for (String nombre : nombres) {
            File f1 = new File(folder1, nombre);
            File f2 = new File(folder2, nombre);
            ValorComparacion valor;
            try {
                valor = compararFichero(f1, f2);
            } catch (DiffFolderException e) {
                System.out.println("ADVERTENCIA: " + e.getMessage());
                e.printStackTrace();
                valor = !f1.isFile() ? ValorComparacion.FALTA_EN_1 : ValorComparacion.FALTA_EN_2;
            }
            resultados.add(new ResultadoComparacion(nombre, valor));
        }
        return resultados;
    }
 
    private String[] listarFicheros(File carpeta) {
        File[] ficheros = carpeta.listFiles(File::isFile);
        if (ficheros == null) { 
            System.out.println("AVISO: No se pudo listar el contenido de " + carpeta.getPath());
            return new String[0];
        }
        String[] nombres = new String[ficheros.length];
        for (int i = 0; i < ficheros.length; i++) {
            nombres[i] = ficheros[i].getName();
        }
        return nombres;
    }
 
    private ValorComparacion compararFichero(File f1, File f2) throws DiffFolderException {
        if (!f1.isFile()) {
            throw new DiffFolderException("El fichero '" + f1.getName() + "' no existe en " + folder1.getPath());
        }
        if (!f2.isFile()) {
            throw new DiffFolderException("El fichero '" + f2.getName() + "' no existe en " + folder2.getPath());
        }
        long m1 = f1.lastModified();
        long m2 = f2.lastModified();
        if (m1 == 0L || m2 == 0L) { // File.lastModified() devuelve 0 si hay error de E/S
            throw new DiffFolderException("No se pudo leer la fecha de modificación de '" + f1.getName() + "'");
        }
        if (m1 == m2) {
            return ValorComparacion.IGUALES;
        }
        return m1 > m2 ? ValorComparacion.MENOS_NUEVO_EN_1 : ValorComparacion.MENOS_NUEVO_EN_2;
    }
}