package org.nta.lessons.lesson12.hwinterfaces;

public interface ExecutionManager {
  Context execute(Runnable callback, Runnable... tasks);
}
