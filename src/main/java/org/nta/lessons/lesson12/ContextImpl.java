package org.nta.lessons.lesson12;

import ru.sbrf.jschool.multithread.part1.hw.RunF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

public class ContextImpl implements Context {
  static List<FutureTask<?>> queue;


  public ContextImpl() {
    queue = new ArrayList<>();
  }

  public ContextImpl(List<FutureTask<?>> queue) {
    ContextImpl.queue = queue;
  }


  @Override
  public int getCompletedTaskCount() {
    int count = (int) queue.stream().filter(t -> queue.stream().filter(t-> t.)
    return count;
  }

  @Override
  public int getFailedTaskCount() {
    return (int) queue.stream().filter(t -> t.isCancelled()).count();
  }

  @Override
  public int getInterruptedTaskCount() {
    return (int) queue.stream().filter(t -> t.isCancelled()).count();
  }

  @Override
  public void interrupt() {
    queue.stream().filter(t -> !t.isDone()).map(t -> t.cancel(true));
  }

  @Override
  public boolean isFinished() {
    if (queue.stream().filter(t -> !t.isDone()).count() == 0 || queue.stream().filter(t -> t.isCancelled()).count() == queue.size())
      return true;
    return false;
  }

  @Override
  public void add(FutureTask<?> submit) {
    queue.add(submit);
  }

}
