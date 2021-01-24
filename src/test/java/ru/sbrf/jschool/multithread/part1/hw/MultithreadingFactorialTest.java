package ru.sbrf.jschool.multithread.part1.hw;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MultithreadingFactorialTest {
  public static void main(String[] args) {
      List<Integer> list = new ArrayList<>();
      list.add(1);
      list.add(10);
      list.add(100);
      list.add(50);
      list.add(25);
      for (Integer integer : list) {
        new Thread(new RunF(integer)).start();
      }
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
  for (Integer integer : list) {
    new Thread(new RunF(integer)).start();
  }

Thread.sleep(1000); //без засыпания не будет писать (если будет main) будет писать
  System.out.println("Программа закончена");
}
}