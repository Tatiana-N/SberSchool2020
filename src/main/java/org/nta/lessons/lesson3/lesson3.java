package org.nta.lessons.lesson3;

import org.nta.lessons.lesson2.carsplitter.Car;

import java.util.ArrayList;
import java.util.List;

public class lesson3 {
  public static<T> void copy(List<? extends Number> from, List<? super Number> to){
    to.set(0, from.get(0));
  }
  public static void main(String[] args) {
List<Integer> from = new ArrayList<>();
    List<Number> to = new ArrayList<>();
    from.add(10);
    to.add(2.0);
    copy(from, to);
    System.out.println(from);
    System.out.println(to);



  }
}
