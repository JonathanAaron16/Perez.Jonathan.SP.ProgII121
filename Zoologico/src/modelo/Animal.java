package modelo;

import java.io.Serializable;
import servicios.CSVSerializable;

public class Animal implements Comparable<Animal>, CSVSerializable {

    private int id;
    private String nombre;
    private String especie;
    private TipoAlimentacion alimentacion;

    public Animal(int id, String nombre, String especie, TipoAlimentacion alimentacion) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.alimentacion = alimentacion;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoAlimentacion getAlimentacion() {
        return alimentacion;
    }

    

    @Override
    public int compareTo(Animal o) {
        return Integer.compare(id, o.id);
    }

    // Implementación de CSVSerializable
    @Override
    public String toCSV() {
        return id + "," + nombre + "," + especie + "," + alimentacion;
    }

    public static Animal fromCSV(String csv) {
        String[] parts = csv.split(",");
        int id = Integer.parseInt(parts[0]);
        String nombre = parts[1];
        String especie = parts[2];
        TipoAlimentacion alimentacion = TipoAlimentacion.valueOf(parts[3]);
        return new Animal(id, nombre, especie, alimentacion);
    }

    @Override
    public String toString() {
        return "Animal{"
                + "id=" + id
                + ", nombre='" + nombre + '\''
                + ", especie='" + especie + '\''
                + ", alimentacion=" + alimentacion
                + '}';
    }

}
