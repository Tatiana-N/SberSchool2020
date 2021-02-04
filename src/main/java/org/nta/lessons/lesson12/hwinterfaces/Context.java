package org.nta.lessons.lesson12.hwinterfaces;

import java.util.concurrent.Future;


public interface Context {

  int getCompletedTaskCount();

  int getFailedTaskCount();

  int getInterruptedTaskCount();

  void interrupt();

  boolean isFinished();

  void add(Future<?> submit);
}
