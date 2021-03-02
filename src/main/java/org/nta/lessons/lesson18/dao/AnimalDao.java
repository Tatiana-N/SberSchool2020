package org.nta.lessons.lesson18.dao;

import org.nta.lessons.lesson18.model.Animal;
import org.nta.lessons.lesson18.model.Tabled;

import java.util.List;

public interface AnimalDao {
    void createAnimalSimple(Animal animal);

    void createAnimalWithPhoto(Animal animal);

    void createAnimals(Animal... animals);

    <T extends Tabled> void createNamedAnimals(List<T> animals);

    void showAnimals();

    void showZZZ();

    List<Animal> getAllAnimals();

    void errorSelect();

    Animal createAnimalBySimpleInsert(Animal animal);
}
