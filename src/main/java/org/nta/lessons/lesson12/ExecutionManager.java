package org.nta.lessons.lesson12;

import java.util.Queue;

public interface ExecutionManager {
  Context execute(Runnable callback, Queue<Runnable> queue);
}
