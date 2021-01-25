package ru.sbrf.jschool.multithread.part1.hw;


import org.junit.Test;
import ru.sbrf.jschool.multithread.part2.hw.MyPool;
import ru.sbrf.jschool.multithread.part2.hw.MyThread;

import java.util.*;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
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

    MyPool myPool = new MyPool(3);
    myPool.start();

    Thread serviceThread = new Thread(() -> {
      while (!Thread.interrupted()) {
        myPool.myThreads.forEach(y -> System.out.println(y.getName() + " " + y.getState()));
        try {
          Thread.sleep(500);

        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    //serviceThread.start();

    myPool.execute(new ArrayDeque<>(list.stream().map(RunF::new).collect(Collectors.toList())));
    myPool.execute(new ArrayDeque<>(list2.stream().map(RunF::new).collect(Collectors.toList())));
  }

  @Test
  public void scalableThreadPool() {
    MyPool myPool = new MyPool();
    myPool.start();
    myPool.execute(new ArrayDeque<>(list.stream().map(RunF::new).collect(Collectors.toList())));
  }

  @Test
  public void FixedThreadPool () {
    MyPool myPool = new MyPool(3);
    myPool.start();
    myPool.execute(new ArrayDeque<>(list.stream().map(RunF::new).collect(Collectors.toList())));
    try {
      Thread.sleep(3000);

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    myPool.execute(new ArrayDeque<>(list2.stream().map(RunF::new).collect(Collectors.toList())));
    try {
      Thread.sleep(3000);

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}