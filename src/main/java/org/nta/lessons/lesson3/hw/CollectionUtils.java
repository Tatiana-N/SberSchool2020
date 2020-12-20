package org.nta.lessons.lesson3.hw;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class CollectionUtils {
  public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
    destination.addAll(source);
  }

  public static <T> List<T> newArrayList() {
    return new ArrayList<>();
  }

  public static <T> int indexOf(List<? extends T> source, T o) {
    return source.indexOf(o);
  }

  public static <T> List<? super T> limit(List<? extends T> source, int size) {
    List<? super T> out = newArrayList();
    for (T t : source) {
      if (size-- == 0) {
        break;
      }
      out.add(t);
    }
    return out;
  }

  public static <T> void add(List<T> source, T o) {
    source.add(o);
  }

  public static <T> T get(List<? extends T> source, int index) { //для себя
    return source.get(index);
  }

  public static <T> void removeAll(List<? extends T> removeFrom, List<? extends T> c2) {
    removeFrom.removeAll(c2);
  }

  //true если первый лист содержит все элементы второго
  public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
    return c1.containsAll(c2);
  }

  //true если первый лист содержит хотя-бы 1 второго
  public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
    for (T t : c2)
      if (c1.contains(t)) {
        return true;
      }
    return false;
  }

  //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
// Элементы сравнивать через Comparable.
// Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
  public static <T extends Comparable<? super T>> List<? super T> range(List<? extends T> list, T min, T max) {
    List<? super T> rangedList = newArrayList();
    for (T t : list) {
      if (t.compareTo(min) >= 0 && t.compareTo(max) <= 0) {
        rangedList.add(t);
      }
    }
    return rangedList;
  }

  //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
// Элементы сравнивать через Comparable.
// Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
  public static <T extends Comparable<? super T>> List<? super T> range(List<? extends T> list, T min, T max, Comparator<T> comparator) {
    List<? super T> rangedList = newArrayList();
    for (T t : list) {
      if (comparator.compare(t, min) >= 0 && comparator.compare(t, max) <= 0) {
        rangedList.add(t);
      }
    }
    return rangedList;
  }
}