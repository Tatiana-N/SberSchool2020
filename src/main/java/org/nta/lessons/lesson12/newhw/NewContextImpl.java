package org.nta.lessons.lesson12.newhw;

import org.nta.lessons.lesson12.interfaces.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class NewContextImpl implements Context {
  private List<Future<?>> queue;
  private volatile int completedTaskCount;
  private volatile int failedTaskCount;
  private volatile int InterruptedTaskCount;

  public boolean isInterrupt() {
    return interrupt;
  }

  private volatile boolean interrupt;

  public List<Future<?>> getQueue() {
    return queue;
  }

  public NewContextImpl() {
    queue = new ArrayList<>();
  }

  public void setCompletedTaskCount() {
    synchronized (this) {
      this.InterruptedTaskCount--;
      this.completedTaskCount++;
    }
  }

  public void setFailedTaskCount() {
    synchronized (this) {
      this.InterruptedTaskCount--;
      this.failedTaskCount++;
    }
  }

  public void setInterruptedTaskCount(int t) {
    InterruptedTaskCount = t;
  }

  @Override
  public int getCompletedTaskCount() {
    return completedTaskCount;
  }

  @Override
  public int getFailedTaskCount() {
    return failedTaskCount;
  }

  @Override
  public int getInterruptedTaskCount() {
    return InterruptedTaskCount;
  }

  @Override
  public void interrupt() {
    interrupt = true;
    queue.forEach(t -> t.cancel(false));
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
//  public static void getInformation() {
//    for (Future<?> futureTask : queue) {
//     // System.out.println(futureTask.toString());
//    }
//  }
}
