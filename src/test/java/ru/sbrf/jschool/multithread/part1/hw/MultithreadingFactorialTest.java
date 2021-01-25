package ru.sbrf.jschool.multithread.part1.hw;


import org.junit.Test;
import ru.sbrf.jschool.multithread.part2.hw.MyPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MultithreadingFactorialTest {
  public static void main(String[] args) {
      List<Integer> list = new ArrayList<>();
      list.add(1);
      list.add(10);
      list.add(100);
      list.add(50);
      list.add(25);
     // list.forEach(t->  new Thread(new RunF(t)).start());
      MyPool my = new MyPool(1,4);

      System.out.println("-------------------");
      List <Runnable> tasks = new ArrayList<>();
      for (Integer a: list){

          tasks.add(new RunF(a));
      }
      my.doWork(tasks);
      System.out.println("Программа закончена");
    }

@Test
  public void fact() throws InterruptedException {
   List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(10);
    list.add(100);
    list.add(50);
    list.add(25);
   // list.stream().forEach(t->  new Thread(new RunF(t)).start());
    ExecutorService ex = Executors.newFixedThreadPool(3);
    MyPool my = new MyPool(1,4);

    System.out.println("-------------------");
    List <Runnable> tasks = new ArrayList<>();
    for (Integer a: list){

    tasks.add(new RunF(a));
    }
  my.doWork(tasks);
Thread.sleep(1000); //без засыпания не будет писать (если будет main) будет писать
  System.out.println("Программа закончена");
}
}