package zoologico;

import modelo.TipoAlimentacion;
import modelo.Animal;
import modelo.Zoologico;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        try {
// Crear un zoológico de animales
            Zoologico<Animal> zoologico = new Zoologico<>();

            zoologico.agregar(new Animal(1, "Leon", "Panthera leo",
                    TipoAlimentacion.CARNIVORO));
            zoologico.agregar(new Animal(2, "Elefante", "Loxodonta",
                    TipoAlimentacion.HERBIVORO));
            zoologico.agregar(new Animal(3, "Oso", "Ursus arctos",
                    TipoAlimentacion.OMNIVORO));
            zoologico.agregar(new Animal(4, "Zorro", "Vulpes vulpes",
                    TipoAlimentacion.CARNIVORO));
            zoologico.agregar(new Animal(5, "Gorila", "Gorilla gorilla",
                    TipoAlimentacion.OMNIVORO));

            // Mostrar todos los animales en el zoológico
            System.out.println("Inventario de animales:");
            zoologico.paraCadaElemento(animal -> System.out.println(animal));

            separador();

            // Filtrar animales por tipo de alimentación
            System.out.println("Animales herbivoros:");
            zoologico.filtrar(a -> a.getAlimentacion() == TipoAlimentacion.HERBIVORO)
                    .forEach(System.out::println);

            separador();

            // Ordenar por nombre leon
            List<Animal> filtrados = zoologico.filtrar(a -> a.getNombre().toLowerCase().contains("leon".toLowerCase()));
            filtrados.forEach(System.out::println);

            separador();

            // Ordenar de manera natural (por id)
            zoologico.ordenarNatural();
            System.out.println("Inventario de animales ordenados:");
            zoologico.paraCadaElemento(animal -> System.out.println(animal));

            separador();

            // Ordenar animales por nombre utilizando un Comparator
            zoologico.ordenarComparador((a1, a2) -> a1.getNombre().compareTo(a2.getNombre()));
            zoologico.paraCadaElemento(animal -> System.out.println(animal));

            separador();

            // Guardar el zoológico en un archivo binario
            zoologico.guardarEnArchivo("src/data/animales.dat");

            // Cargar el zoológico desde el archivo binario
            Zoologico<Animal> zoologicoCargado = new Zoologico<>();

            zoologicoCargado.cargarDesdeArchivo("src/data/animales.dat");
            System.out.println("\nAnimales cargados desde archivo binario:");
            zoologicoCargado.paraCadaElemento(animal -> System.out.println(animal));

            separador();

            // Guardar el zoológico en un archivo CSV
            Zoologico.cargarDesdeCSV("src/data/animales.csv");
            // Cargar el zoológico desde el archivo CSV
            
            
            Zoologico.guardarEnCSV(filtrados, "src/data/animales.csv");
            System.out.println("\nAnimales cargados desde archivo CSV:");
            zoologicoCargado.paraCadaElemento(animal -> System.out.println(animal));

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void separador() {
        System.out.println("-------------------------------------");
    }

}
