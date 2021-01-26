package ru.sbrf.jschool.multithread.part1.hw;


import org.junit.Test;
import ru.sbrf.jschool.multithread.part2.hw.MyPoolFixedTreads;

import java.util.*;
import java.util.stream.Collectors;

public class MultithreadingFactorialTest {
  static List<Integer> list = new ArrayList<>();
  static List<Integer> list2 = new ArrayList<>();

  static {
    list.add(1);
    list.add(10);
    list.add(100);
    list.add(50);
    list.add(25);
    list2.add(13);
    list2.add(105);
    list2.add(111);
    list2.add(5);
    list2.add(18);
  }

  public static void main(String[] args) {

    MyPoolFixedTreads myPoolFixedTreads = new MyPoolFixedTreads(3);
    myPoolFixedTreads.start();

    Thread serviceThread = new Thread(() -> {
      while (!Thread.interrupted()) {
        myPoolFixedTreads.myThreads.forEach(y -> System.out.println(y.getName() + " " + y.getState()));
        try {
          Thread.sleep(500);

        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    //serviceThread.start();

    myPoolFixedTreads.execute(new ArrayDeque<>(list.stream().map(RunF::new).collect(Collectors.toList())));
    myPoolFixedTreads.execute(new ArrayDeque<>(list2.stream().map(RunF::new).collect(Collectors.toList())));
    myPoolFixedTreads.stop();
  }

  @Test
  public void scalableThreadPool() {
    MyPoolFixedTreads myPoolFixedTreads = new MyPoolFixedTreads();
    myPoolFixedTreads.start();
    myPoolFixedTreads.execute(new ArrayDeque<>(list.stream().map(RunF::new).collect(Collectors.toList())));
  }

  @Test
  public void FixedThreadPool () {
    MyPoolFixedTreads myPoolFixedTreads = new MyPoolFixedTreads(3);
    myPoolFixedTreads.start();
    myPoolFixedTreads.execute(new ArrayDeque<>(list.stream().map(RunF::new).collect(Collectors.toList())));
    try {
      Thread.sleep(3000);

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    myPoolFixedTreads.execute(new ArrayDeque<>(list2.stream().map(RunF::new).collect(Collectors.toList())));
    try {
      Thread.sleep(3000);

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}