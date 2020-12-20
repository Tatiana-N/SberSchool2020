package org.nta.lessons.lesson3.hw;

import java.util.*;
import java.util.stream.Collectors;

public class CountMapWithMap<T> implements CountMap<T>{
  private final Map<T,Integer> map;

  public CountMapWithMap(Map<T, Integer> map) {
    this.map = map;
  }
  public CountMapWithMap() {
    this.map = new LinkedHashMap<>();
  }

  @Override
  public void add(T o) {
    if (map.containsKey(o)) {
      map.put(o, map.get(o) + 1);
    } else {
      map.put(o, 1);
    }
  }

  @Override
  public int getCount(T o) {
    return map.get(o);
  }

  @Override
  public int remove(T o) {
    return map.remove(o);
  }

  @Override
  public int size() {
    return map.size();
  }

  @Override
  public void addAll(CountMap<T> source) {
  map.putAll(source.toMap());
  }

  @Override
  public Map<T, Integer> toMap() {
    return map;
  }

  @Override
  public void toMap(Map<T, Integer> destination) {
  destination.putAll(map);
  }

  public List<T> toList() {
    return new ArrayList<>(map.keySet());
  }
}
