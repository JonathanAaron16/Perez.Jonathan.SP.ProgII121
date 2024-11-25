package modelo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Zoologico<T> implements GestorAnimales {

     List<Animal> inventario;

    public Zoologico() {
        this.inventario = new ArrayList<>();
    }
    
    // Agregar animal
    @Override
    public void agregar(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("No se puede agregar un animal nulo.");
        }
        inventario.add(animal);
    }

    // Obtener animal por índice
    @Override
    public Animal obtener(int indice) {
        if (indice < 0 || indice >= inventario.size()) {
            throw new IndexOutOfBoundsException("El indice está fuera del rango : " + indice);
        }
        return inventario.get(indice);
    }

    // Eliminar animal por índice
    @Override
    public void eliminar(int indice) {
        if (indice < 0 || indice >= inventario.size()) {
            throw new IndexOutOfBoundsException("El indice está fuera del rango: " + indice);
        }
        inventario.remove(indice);
    }

    // Filtrar por criterio
    @Override
    public List<Animal> filtrar(Predicate<Animal> criterio) {
        List<Animal> toReturn = new ArrayList<>();
        for (Animal animal : inventario) {
            if (criterio.test(animal)) {
                toReturn.add(animal);
            }
        }
        return toReturn;
    }

    // Ordenar por orden natural
    @Override
    public void ordenarNatural() {
        Collections.sort(inventario);
    }

    // Ordenar por Comparator
    public void ordenarComparador(Comparator<Animal> comparator) {
        inventario.sort(comparator);
    }

    // Mostrar todos los animales en el inventario
    public void paraCadaElemento(Consumer<Animal> accion) {
        for (Animal animal : inventario) {
            accion.accept(animal);
        }
    }

   
    // Guardar en archivo binario
    public void guardarEnArchivo(String ruta) throws IOException {
        try (ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream(ruta))) {
            archivo.writeObject(inventario);
        }
    }

    // Cargar desde archivo binario
    public void cargarDesdeArchivo(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(ruta))) {
            inventario = (List<Animal>) (List<T>) input.readObject();
        }
    }


public static void guardarEnCSV(List<? extends Animal> lista, String path) {
        File archivo = new File(path);
        try {
            if (archivo.exists()) {
                System.out.println("El archivo ya exite");
            } else {
                archivo.createNewFile();
            }
        } catch (IOException ex) {
            System.out.println("Ocurrio un errror");

        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write("id, nombre, especie, alimentacion \n");
            for (Animal e : lista) {
                bw.write(e.toCSV() + "\n");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    public static List<Animal> cargarDesdeCSV(String path) {
        List<Animal> toReturn = new ArrayList<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {

            String linea;

            bf.readLine();
            while ((linea = bf.readLine()) != null) {
                if (linea.endsWith("\n")) {
                    linea = linea.substring(linea.length() - 1);
                }
                String[] data = linea.split(",");

                if (data.length == 4) {
                    int id = Integer.parseInt(data[0]);
                    String nombre = data[1];
                    String especie = data[2];
                    TipoAlimentacion alimentacion = TipoAlimentacion.valueOf(data[3]);
                    Animal e = new Animal(id, nombre, especie, alimentacion);
                    toReturn.add(e);
                }

            }


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            throw new RuntimeException("problema al cargar empleado");

        }
        return toReturn;
    }

}
