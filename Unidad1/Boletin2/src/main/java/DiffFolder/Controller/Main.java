package DiffFolder.Controller;

import java.io.File;

import DiffFolder.Models.ResultadoComparacion;
import DiffFolder.Repository.DiffFolder;
import DiffFolder.Exceptions.GestionFicherosException;

public class Main {
 
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java Main <carpeta1> <carpeta2>");
            return;
        }
        DiffFolder diff = new DiffFolder();
        try {
            diff.setFolders(new File(args[0]), new File(args[1]));
        } catch (GestionFicherosException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }
        for (ResultadoComparacion r : diff.comparame()) {
            System.out.println(r.getNomFichero() + " -> " + r.getValorComparacion());
        }
    }
}
 