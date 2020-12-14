package org.nta.lessons.lesson2.carsplitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestSplitter {
  public static void main(String[] args) {
    Splitter splitter = new Splitter();
    List<Car> cars = new ArrayList<>();
    cars.add(new Car("Лада", "седан"));
    cars.add(new Car("Лада", "хэтчбек"));
    cars.add(new Car("Мерседес", "седан"));
    cars.add(new Car("Бмв", "кроссовер"));
    cars.add(new Car("Форд", "хэтчбек"));
    cars.add(new Car("Пежо", "кроссовер"));
    cars.add(new Car("Тойота", "седан"));
    Map<String, List<Car>> map = splitter.split(cars);
    for (String type : map.keySet()) {
      System.out.println(type + " " + map.get(type));
    }
  }
}

