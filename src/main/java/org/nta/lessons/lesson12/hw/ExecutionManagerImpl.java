package org.nta.lessons.lesson12.hw;


import org.nta.lessons.lesson12.hw.ContextImpl;
import org.nta.lessons.lesson12.interfaces.Context;
import org.nta.lessons.lesson12.interfaces.ExecutionManager;

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
    new Thread(new Runnable() {

      @Override
      public void run() {
        while (true){
          if(context.getCompletedTaskCount() + context.getFailedTaskCount() + context.getInterruptedTaskCount() == ContextImpl.queue.size()){
            callback.run(); break;
          }
        }
      }
    }).start();
    return context;
  }
}
