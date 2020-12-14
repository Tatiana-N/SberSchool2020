package org.nta.lessons.lesson2.carsplitter;

import java.util.*;
import java.util.stream.Collectors;

public class Splitter {
  public Map<String, List<Car>> split(List<Car> list) {
    Set<String> types = list.stream()
                            .map(Car::getType)
                            .collect(Collectors.toSet());
    Map<String, List<Car>> map = new HashMap<>();
    for (String type : types) {
      map.put(type, list.stream()
                        .filter(car -> car.getType().equals(type))
                        .collect(Collectors.toList()));
    }
    return map;
  }
}
