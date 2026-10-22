package Controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import Exceptions.EldenException;
import Models.Encuentro;
import Models.RegistroEldenRing;
import Repository.SinLuz;

public class GestionaEncuentrosSinLuz {
    public static void main(String[] args) {
        RegistroEldenRing registro = new RegistroEldenRing();

        try {
            SinLuz sl1 = new SinLuz("Vyke");
            SinLuz sl2 = new SinLuz("Tarnished One");

            registro.registrarSinLuz(sl1);
            registro.registrarSinLuz(sl2);

            Encuentro enc1 = new Encuentro("ENC01", "Necrolimbo", LocalDate.now(), 5, Arrays.asList("Margit", "Godrick"));
            Encuentro enc2 = new Encuentro("ENC02", "Pico de los Gigantes", LocalDate.now(), 9, Arrays.asList("Gigante de Fuego"));

            registro.agregarEncuentroASinLuz(sl1.getId(), enc1);
            registro.agregarEncuentroASinLuz(sl2.getId(), enc2);

            System.out.println("Búsqueda por ID");
            SinLuz encontrado = registro.buscarPorId(1);
            System.out.println("Encontrado: " + encontrado.getNombre());

            System.out.println("SinLuz con encuentros de dificultad >= 8");
            List<SinLuz> masDificiles = registro.obtenerSinLuzPorDificultad(8);
            for (SinLuz s : masDificiles) {
                System.out.println("- " + s.getNombre());
            }

        } catch (EldenException e) {
            System.err.println("Error en la gestión de Elden Ring: " + e.getMessage());
        }
    }
}