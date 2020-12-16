package org.nta.lessons.lesson2.carsplitter;

import java.util.*;
import java.util.stream.Collectors;

public class Splitter {
  public Map<String, List<Car>> split(List<Car> list) {

    Set<String> types = new HashSet<>();
    for (Car car:list) {
      types.add(car.getType()); // list.stream().map(Car::getType).collect(Collectors.toSet()); замена
    }

    Map<String, List<Car>> map = new HashMap<>();

    for (String type : types) {
      List<Car> listWithOneType = new ArrayList<>();
      for (Car car:list) {
        if (car.getType().equals(type)) {
          listWithOneType.add(car); //list.stream().filter(car -> car.getType().equals(type)).collect(Collectors.toList())); замена
        }
      }
      map.put(type, listWithOneType);}
    return map;
  }
}
