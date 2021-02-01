package org.nta.lessons.lesson12;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task2<T> {
  private Callable<? extends T> callable;

  public Task2(Callable<? extends T> callable) {
    this.callable = callable;

  }

  public T get() {
    return null;
  }

  public static void main(String[] args) {
    LinkedHashMap<Integer,String> map = new LinkedHashMap<>(250, 0.75f, true);
    map.put(1, "7");
    map.put(3, "3");
    map.put(2, "3");
    map.put(74, "3");
    map.put(0, "3");
    map.put(-1, "3");


    Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();

    while (it.hasNext()) {
      Map.Entry<Integer, String> next = it.next();
      if (next.getKey() > 2) {
        System.out.println(next.getKey());
      }
    }
    System.out.println("======================");
      it = map.entrySet().iterator();
      while (it.hasNext()) {
        Map.Entry<Integer, String> next = it.next();
        System.out.println(next.getKey() + " " + next.getValue());

      }


  //  map.entrySet().forEach(t -> System.out.println(t.getKey() + " " + t.getValue() + " " + map2.get(t.getKey()) ));
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    Task2<String> task2 = new Task2<>(new Callable<String>() {
      @Override
      public String call() throws Exception {
        System.out.println("Do work");
        Thread.sleep(5000);
        return "DONE";
      }
    });
   // task.get();

  }
}
