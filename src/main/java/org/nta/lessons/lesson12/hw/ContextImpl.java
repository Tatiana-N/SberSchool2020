package org.nta.lessons.lesson12.hw;


import org.nta.lessons.lesson12.interfaces.Context;

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
    int count = (int) queue.stream().filter(t -> t.toString().contains("Completed normally")).count();
    return count;
  }

  @Override
  public int getFailedTaskCount() {
    return (int) queue.stream().filter(t -> t.toString().contains("Completed exceptionally")).count();
  }

  @Override
  public int getInterruptedTaskCount() {
    return (int) queue.stream().filter(t -> t.isCancelled()).count();
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
    if (getCompletedTaskCount() == queue.size() || getInterruptedTaskCount() == queue.size()) {
      return true;
    }
    return false;
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
