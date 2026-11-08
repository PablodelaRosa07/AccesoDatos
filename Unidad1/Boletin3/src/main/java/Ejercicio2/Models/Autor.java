package Ejercicio2.Models;

public class Autor {
    private String nombre;
    private String apellidos;
    private String pais;

    public Autor() {}

    public Autor(String nombre, String apellidos, String pais) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.pais = pais;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
}
