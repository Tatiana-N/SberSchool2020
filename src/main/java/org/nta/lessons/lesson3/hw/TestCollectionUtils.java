package org.nta.lessons.lesson3.hw;

import org.junit.Test;

import java.util.*;


public class TestCollectionUtils {
  ArrayList<Integer> listInteger = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 7));
  ArrayList<Double> listDouble = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 5.0, 7.0));
  ArrayList<Double> listAny = new ArrayList<>(Arrays.asList(1.0));
  List<Number> list = new ArrayList<>();

  @Test
  public void addAll() {

    CollectionUtils.addAll(listInteger, list);
    CollectionUtils.addAll(listDouble, list);
    System.out.println(list);
  }

  @Test
  public void newArrayList() {
    List<String> arrayList = CollectionUtils.newArrayList();
    arrayList.add("2");
    System.out.println(arrayList);
  }

  @Test
  public void indexOf() {
    addAll();
    System.out.println(CollectionUtils.indexOf(list, 5.0));
  }

  @Test
  public void limit() {
    addAll();
    System.out.println(CollectionUtils.limit(list, 4));
  }

  @Test
  public void add() {
    CollectionUtils.add(list, 1);
    CollectionUtils.add(list, 1.0);
    //  CollectionUtils.add(list , "list,new Car()");//не дает класть не понятно что
    System.out.println(CollectionUtils.get(list, 0).getClass());
  }

  @Test
  public void removeAll() {
    addAll();
    CollectionUtils.removeAll(list, listInteger);
    System.out.println(list);
  }

  @Test
  public void containsAll() {
    addAll();
    System.out.println(CollectionUtils.containsAll(list, listInteger));
  }

  @Test
  public void containsAny() {
    addAll();
    System.out.println(CollectionUtils.containsAny(list, listAny));
  }

  @Test
  public void range() {
    System.out.println(CollectionUtils.range(listDouble, 2.0, 5.0));
  }

  @Test
  public void rangeWithComparator() {
    System.out.println(CollectionUtils.range(listInteger,
      2,
      5, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
          return o1 - o2;
        }
      }));
  }
}
