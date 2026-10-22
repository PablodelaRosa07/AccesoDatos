package Models;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Biblioteca {
    private String nombre;
    private String direccion;
    private Map<String, Libro> catalogo;
    private Map<String, Usuario> usuarios;
    private List<Prestamo> prestamos;

    public Biblioteca(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.catalogo = new HashMap<>();
        this.usuarios = new HashMap<>();
        this.prestamos = new ArrayList<>();
    }


    public void agregarLibro(Libro libro) {
        catalogo.put(libro.getId(), libro);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }


    public boolean devolverLibro(String idUsuario, String idLibro) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getUsuario().getId().equals(idUsuario) &&
                prestamo.getLibro().getId().equals(idLibro) &&
                !prestamo.isDevuelto()) {

                prestamo.setDevuelto(true);
                Libro libro = prestamo.getLibro();
                libro.setEjemplaresDisponibles(libro.getEjemplaresDisponibles() + 1);
                System.out.println("Devolución completada: '" + libro.getTitulo() + "' devuelto por " + prestamo.getUsuario().getNombreCompleto());
                return true;
            }
        }
        System.out.println("Error: No se encontró un préstamo activo para ese usuario y libro.");
        return false;
    }

    // --- Consultas e Históricos ---

    public void mostrarHistoricoPrestamos() {
        System.out.println("\n=== REGISTRO HISTÓRICO DE PRÉSTAMOS ===");
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
        } else {
            prestamos.forEach(System.out::println);
        }
    }

    /**
     * Imprime la lista de los 10 libros más prestados ordenados por año de publicación.
     */
    public void imprimirTop10LibrosMasPrestados() {
        System.out.println("\n=== TOP 10 LIBROS MÁS PRESTADOS (ORDENADOS POR AÑO DE PUBLICACIÓN) ===");

        // 1. Contar número de préstamos por libro
        Map<Libro, Long> conteoPrestamos = prestamos.stream()
                .collect(Collectors.groupingBy(Prestamo::getLibro, Collectors.counting()));

        // 2. Obtener los 10 más prestados y ordenarlos por año de publicación
        List<Libro> top10 = conteoPrestamos.entrySet().stream()
                .sorted(Map.Entry.<Libro, Long>comparingByValue().reversed())
                .limit(10)
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparingInt(Libro::getAnioPublicacion))
                .collect(Collectors.toList());

        if (top10.isEmpty()) {
            System.out.println("No hay préstamos registrados para generar el ranking.");
        } else {
            top10.forEach(libro -> 
                System.out.printf("%s | Préstamos totales: %d%n", libro, conteoPrestamos.get(libro))
            );
        }
    }

    /**
     * Filtra los usuarios registrados que tienen o han tenido préstamos y los ordena
     * alfabéticamente por nombre y apellidos.
     */
    public List<Usuario> obtenerUsuariosConPrestamosOrdenados() {
        return prestamos.stream()
                .map(Prestamo::getUsuario)
                .distinct()
                .sorted(Comparator.comparing(Usuario::getNombre)
                        .thenComparing(Usuario::getApellidos))
                .collect(Collectors.toList());
    }

    /**
     * Genera un mapa con los libros de género INFANTIL (ID -> Libro).
     */
    public Map<String, Libro> obtenerLibrosInfantiles() {
        return catalogo.values().stream()
                .filter(l -> l.getGenero() == Genero.INFANTIL)
                .collect(Collectors.toMap(Libro::getId, libro -> libro));
    }
}