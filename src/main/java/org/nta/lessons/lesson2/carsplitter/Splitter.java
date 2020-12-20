package org.nta.lessons.lesson2.carsplitter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Splitter {
  public Map<String, List<Car>> split(List<Car> list) {
    return list.stream()
      .map(Car::getType)
      .collect(Collectors.toSet()).stream()
      .collect(Collectors.toMap(
        p -> p,
        t -> list.stream()
          .collect(Collectors.partitioningBy(element -> element.getType().equals(t))).get(true)));
  }
}
