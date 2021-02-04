package org.nta.lessons.lesson12.hw2;


import org.nta.lessons.lesson12.hwinterfaces.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ContextImpl implements Context {
  static List<Future<?>> queue;

  public ContextImpl() {
    queue = new ArrayList<>();
  }

  public ContextImpl(List<Future<?>> queue) {
    ContextImpl.queue = queue;
  }

  @Override
  public int getCompletedTaskCount() {
    return (int) queue.stream().filter(t -> t.toString().contains("Completed normally")).count();
  }

  @Override
  public int getFailedTaskCount() {
    return (int) queue.stream().filter(t -> t.toString().contains("Completed exceptionally")).count();
  }

  @Override
  public int getInterruptedTaskCount() {
    return (int) queue.stream().filter(Future::isCancelled).count();
  }

  @Override
  public void interrupt() {
//    Field state = null;
//    try {
//      state = FutureTask.class.getDeclaredField("state");
//    } catch (NoSuchFieldException e) {
//      e.printStackTrace();
//    }Field finalState = state;
//    state.setAccessible(true);
    queue.stream().filter(t -> !t.isDone()).forEach(t -> t.cancel(true));
  }


  @Override
  public boolean isFinished() {
    return getCompletedTaskCount() == queue.size() || getInterruptedTaskCount() == queue.size();
  }

  @Override
  public void add(Future<?> submit) {
    queue.add(submit);
  }

  public static void getInformation() {
    for (Future<?> futureTask : queue) {
      System.out.println(futureTask.toString());
    }
  }
}
