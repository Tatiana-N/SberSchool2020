package org.nta.lessons.lesson3.hw;

import java.util.*;

public class CountMapClass<T> implements CountMap<T> {
  private final List<T> list;

  public List<T> getList() {
    return list;
  }

  public CountMapClass() {
    this.list = new ArrayList<>();
  }
  public CountMapClass(List<T> list) {
    this.list = list;
  }

  @Override
  public void add(T o) {
    list.add(o);
  }

  @Override
  public int getCount(T o) {
    int count = 0;
    for (T t : list) {
      if(t.equals(o)){
        count++;
      }
    }

    return count; //   (int) list.stream().filter(t -> t.equals(o)).count();
  }

  @Override
  public int remove(T o) {
    int count = getCount(o);
    list.remove(o);
    return count;
  }

  public int removeAll(T o) {
    int count = getCount(o);
    while (list.contains(o)) {
      list.remove(o);
    }
    return count;
  }

  @Override
  public int size() {
    return new HashSet<>(list).size();
  }

  @Override
  public void addAll(CountMap<T> source) {
    CountMapClass<T> s = (CountMapClass<T>) source;
    Iterator<T> iterator = s.list.iterator();
    while (iterator.hasNext()) {
      list.add(iterator.next());
    }
  }

  @Override
  public Map<T, Integer> toMap() {
    Map<T, Integer> map = new HashMap<>();
    for (T obj : list) {
      if (!map.containsKey(obj)) {
        map.put(obj, 1);
      } else {
        map.put(obj, map.get(obj) + 1);
      }
    }
    return map;
  }

  public List<T> toList() {
    return list;
  }

  @Override
  public void toMap(Map<T, Integer> destination) {
    for (T obj : list) {
      if (!destination.containsKey(obj)) {
        destination.put(obj, 1);
      } else {
        destination.put(obj, destination.get(obj) + 1);
      }
    }
  }
}
