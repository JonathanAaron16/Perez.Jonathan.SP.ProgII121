
package modelo;

import modelo.Animal;
import java.util.List;
import java.util.function.Predicate;


public interface GestorAnimales {
    void agregar(Animal animal);
    Animal obtener(int index);
    void eliminar(int index);
    List<Animal> filtrar(Predicate<Animal> criterio);
    void ordenarNatural();
}
