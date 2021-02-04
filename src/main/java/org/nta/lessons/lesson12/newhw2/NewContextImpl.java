package org.nta.lessons.lesson12.newhw2;

import org.nta.lessons.lesson12.hwinterfaces.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

public class NewContextImpl implements Context {
  private final List<Future<?>> queue;
  private volatile int completedTaskCount;
  private volatile int failedTaskCount;

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
      this.completedTaskCount++;
    }
  }

  public void setFailedTaskCount() {
    synchronized (this) {
      this.failedTaskCount++;
    }
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
    if(interrupt){ return queue.size() - completedTaskCount - failedTaskCount;}
    return 0;
  }

  @Override
  public void interrupt() {
    interrupt = true;
    queue.forEach(t -> t.cancel(false));
  }

  @Override
  public boolean isFinished() {
    return getCompletedTaskCount() == queue.size() || getInterruptedTaskCount() == queue.size();
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
