package org.nta.lessons.lesson12;

import java.util.concurrent.FutureTask;

public interface Context {
  
  int getCompletedTaskCount();

  int getFailedTaskCount();

  int getInterruptedTaskCount();

  void interrupt();

  boolean isFinished();

  void add(FutureTask<?> submit);
}
