package org.nta.lessons.lesson12.hw2;


import org.nta.lessons.lesson12.hwinterfaces.Context;
import org.nta.lessons.lesson12.hwinterfaces.ExecutionManager;

import java.util.concurrent.*;

public class ExecutionManagerImpl implements ExecutionManager {
  ExecutorService executorService;
  public ExecutionManagerImpl(int r) {
    executorService = Executors.newFixedThreadPool(r);
  }
  Context context = new ContextImpl();

  @Override
  public Context execute(Runnable callback, Runnable... tasks)  {

    for (Runnable runnable : tasks) {
      Future<String> stringFutureTask = new FutureTask<>(runnable, "DONE");
      context.add(stringFutureTask);
    }
try {
    Thread.sleep(1000);
  } catch (InterruptedException e) {
    e.printStackTrace();
  }
    for (Future<?> futureTask : ContextImpl.queue) {
      executorService.execute((FutureTask<?>)futureTask);
    }
    new Thread(() -> {
        while (true){
          if(context.getCompletedTaskCount() + context.getFailedTaskCount() + context.getInterruptedTaskCount() == ContextImpl.queue.size()){
            callback.run(); break;
          }
      }
    }).start();
    return context;
  }
}
