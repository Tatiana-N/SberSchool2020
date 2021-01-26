package ru.sbrf.jschool.multithread.part1.hw;


import org.junit.Test;
import ru.sbrf.jschool.multithread.part2.hw.*;

import java.util.*;
import java.util.stream.Collectors;

public class MultithreadingFactorialTest {
  static List<Integer> list = new ArrayList<>();
  static List<Integer> list2 = new ArrayList<>();

  static {
    for (int i = 0; i < 100; i++) {
      list.add(i); //list.add((int) (Math.random() * 100));
    }
    list2.add(13);
    list2.add(105);
    list2.add(111);
    list2.add(5);
    list2.add(18);
  }

  public static void main(String[] args) {
    MyPool myPoolFixedTreads = new MyPoolFixedTreads(3); // FixedTreads
    myPoolFixedTreads.start();
    Thread serviceThread = new Thread(() -> {
      while (!Thread.interrupted()) {
        Set<Thread> myThreads = myPoolFixedTreads.getMyThreads();
        System.out.println("Количество нитей в данный момент : " + myThreads.size());
        myPoolFixedTreads.getMyThreads().forEach(y -> System.out.println(y.getName() + " " + y.getState()));
        try {
          Thread.sleep(500);

        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    myPoolFixedTreads.execute(new ArrayDeque<>(list.stream().map(RunF::new).collect(Collectors.toList())));
    serviceThread.setDaemon(true);
    serviceThread.start();
    myPoolFixedTreads.execute(new ArrayDeque<>(list2.stream().map(RunF::new).collect(Collectors.toList())));
    myPoolFixedTreads.stop();
  }

  @Test
  public void myPoolFixedTreads() {
    MyPool myPool = new MyPoolFixedTreads(2);//  2 Threads
    myPool.start();
    myPool.execute(new ArrayDeque<>(list.stream().map(RunF::new).collect(Collectors.toList())));
    myPool.join();
    myPool.stop();

  }

  @Test
  public void scalableThreadPool2() {
    MyPoolScalableTreads myPool = new MyPoolScalableTreads(2, 7); //  from 2 to 7 Threads
    myPool.start();
    myPool.setTimeRemoving(1000); // как часто приходит сборщик мусора
    MyThread.setTimeRemoving(2000); // устанавливаем время после которого бездействующие потоки будут удаляться
    ArrayDeque<RunF> runFS = new ArrayDeque<>(list.stream().map(RunF::new).collect(Collectors.toList()));
    for (RunF runF : runFS) {
      myPool.execute(runF);
      try {
        Thread.sleep(700);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    myPool.stop();

  }

  @Test
  public void scalableThreadPool3() {
    MyPoolScalableTreads myPool = new MyPoolScalableTreads(2); // min = 2   max = 200
    myPool.start();
    ArrayDeque<RunF> runFS = new ArrayDeque<>(list.stream().map(RunF::new).collect(Collectors.toList()));
    for (RunF runF : runFS) {
      myPool.execute(runF);
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    myPool.stop();

  }

  @Test
  public void FirstTryFixedThreadPool() {
    FirstTryMyPoolFixedTreads firstTryMyPoolFixedTreads = new FirstTryMyPoolFixedTreads(3);
    firstTryMyPoolFixedTreads.start();
    firstTryMyPoolFixedTreads.execute(new ArrayDeque<>(list.stream().map(RunF::new).limit(20).collect(Collectors.toList())));
    firstTryMyPoolFixedTreads.join();
    firstTryMyPoolFixedTreads.execute(new ArrayDeque<>(list2.stream().map(RunF::new).collect(Collectors.toList())));
    firstTryMyPoolFixedTreads.stop();
  }
}