package org.nta.lessons.lesson12.newhw;

import org.nta.lessons.lesson12.FailedMethodCall;
import org.nta.lessons.lesson12.interfaces.Context;
import org.nta.lessons.lesson12.interfaces.ExecutionManager;
import org.nta.lessons.lesson12.newhw.NewContextImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class NewManager implements ExecutionManager {
  ExecutorService executorService;

  public NewManager(int r) {
    executorService = Executors.newFixedThreadPool(r);
  }

  Context context = new NewContextImpl();


  @Override
  public Context execute(Runnable callback, Runnable... tasks) {
    ((NewContextImpl) context).setInterruptedTaskCount(tasks.length);
    for (Runnable runnable : tasks) {
      Runnable newRunnable = () -> {
        try {
          runnable.run();
          ((NewContextImpl) context).setCompletedTaskCount();
        } catch (FailedMethodCall e) {
          ((NewContextImpl) context).setFailedTaskCount();
          throw new FailedMethodCall();
        }
      };
      Future<String> stringFutureTask = new FutureTask<>(newRunnable, "DONE");
      context.add(stringFutureTask);
      executorService.execute((FutureTask<?>) stringFutureTask);
    }
    new Thread(() -> { //поток для выполнения callBack
      while (true) {
        if (((NewContextImpl) context).isInterrupt() || context.isFinished()) {
          callback.run();
          break;
        }
      }
    }).start();
    return context;
  }
}
