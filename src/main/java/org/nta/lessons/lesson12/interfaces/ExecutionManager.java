package org.nta.lessons.lesson12.interfaces;

import org.nta.lessons.lesson12.interfaces.Context;

public interface ExecutionManager {
  Context execute(Runnable callback, Runnable... tasks);
}
