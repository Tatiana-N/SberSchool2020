package org.nta.lessons.lesson3.hw;

import java.util.Map;

public interface CountMap<T>  {

  void add(T o);

  int getCount(T o);

  //current count
  int remove(T o);

  int size();

  void addAll(CountMap<T> source);

  Map<T, Integer> toMap();

  void toMap(Map<T, Integer> destination);

  public static void main(String[] args) {
    CountMap<Integer> map = new CountMapClass<>();

    map.add(10);
    map.add(10);
    map.add(5);
    map.add(6);
    map.add(5);
    map.add(10);

        int count = map.getCount(5); // 2
        int count2 = map.getCount(6); // 1
        int count3 = map.getCount(10); // 3
  }
}
