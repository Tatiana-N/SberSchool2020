package org.nta.lessons.lesson12;

import org.nta.lessons.lesson10_11.multithread.part1.hw.RunF;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.*;

public class ContextImpl implements Context {
  Queue<Runnable> queue = new LinkedBlockingDeque<>();

  public ContextImpl(Runnable... tasks) {
    Collections.addAll(this.queue, tasks);
  }
  public ContextImpl(Queue<Runnable> queue) {
    this.queue = queue;
  }

  public static void main(String[] args) {
    ExecutorService service = Executors.newFixedThreadPool(5);
    Queue<Runnable> list = new LinkedBlockingDeque<>();
    for (int i = 0; i < 100; i++) {
      list.add(new RunF(i)); //list.add((int) (Math.random() * 100));
    }
  }

  @Override
  public int getCompletedTaskCount() {
    return 0;
  }

  @Override
  public int getFailedTaskCount() {
    return 0;
  }

  @Override
  public int getInterruptedTaskCount() {
    return 0;
  }

  @Override
  public void interrupt() {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
