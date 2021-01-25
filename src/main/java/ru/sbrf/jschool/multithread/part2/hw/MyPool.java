package ru.sbrf.jschool.multithread.part2.hw;


import java.util.*;
import java.util.stream.Collectors;

public class MyPool {
  public Set<MyThread> myThreads;
  private int min;
  private int max;

  public MyPool() {
    this.myThreads = new HashSet<>();
  }
  public MyPool(int min) {
    this.myThreads = new HashSet<>();
    this.min = min;
  }
  public MyPool(int min, int max) {
    this.myThreads = new HashSet<>(max);
    this.min = min;
    this.max = max;

  }
  public void start() {
    if(min == 0 ){min = 5;}
      for (int i = 0; i < min ; i++) {
        MyThread myThread = new MyThread();
        myThread.setName("Thread" + i);
        myThread.start();
        myThreads.add(myThread);
      }
  }

  public void execute(Queue<Runnable> queue) {
    while (!queue.isEmpty()){
    for (MyThread myThread : myThreads) {
    if (!queue.isEmpty()) {
      myThread.doThis(queue.poll());
    }
      }
    }
  }

}
