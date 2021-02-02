package org.nta.lessons.lesson12;

import java.util.Queue;
import java.util.concurrent.*;

public class ExecutionManagerImpl implements ExecutionManager {
  ExecutorService executorService = Executors.newFixedThreadPool(5);
  Context context = new ContextImpl();

  @Override
  public Context execute(Runnable callback, Queue<Runnable> queue) {
   new Thread(new Runnable() {
      @Override
      public void run() {
        while (!queue.isEmpty()) {
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          context.add(new FutureTask<>(new Callable<Future>() {
            @Override
            public Future call() throws Exception {
              return executorService.submit(queue.poll());
            }
          }));
        }
      }
   }
    ).start();
    if (context.isFinished()){executorService.execute(callback);}
    return context;
  }
}
