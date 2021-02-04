package org.nta.lessons.lesson12;


import org.junit.Test;
import org.nta.lessons.lesson12.hw2.ContextImpl;
import org.nta.lessons.lesson12.hw2.ExecutionManagerImpl;
import org.nta.lessons.lesson12.hwinterfaces.Context;
import org.nta.lessons.lesson12.hwinterfaces.ExecutionManager;
import org.nta.lessons.lesson12.newhw2.NewContextImpl;
import org.nta.lessons.lesson12.newhw2.NewManager;
import ru.sbrf.jschool.multithread.part1.hw.RunF;


public class ContextImplTest {
  private static final String ANSI_PURPLE = "\u001B[35m";
  private static final String ANSI_RESET = "\u001B[0m";

  @Test
  public void test2Exception8Cancelled() {
    // если задачи отменены останавливает
    // и те которые не успели закончить выполнение,
    // вылетают Exceptions
    Runnable callBack = () -> System.out.println("Все выполнено");
    Runnable[] list = new Runnable[10];
    for (int i = 0; i < 10; i++) {
      list[i] = new RunF(i - 2); //list.add((int) (Math.random() * 100));
    }
    ExecutionManager manager = new ExecutionManagerImpl(3);
    Context context = manager.execute(callBack, list);
    try {
      Thread.sleep(900);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Задачи успешно завершились " + context.getCompletedTaskCount());
    System.out.println("Задачи выбросили исключения " + context.getFailedTaskCount());
    System.out.println("Задачи были не завершены " + context.getInterruptedTaskCount());
    System.out.print(ANSI_PURPLE + "Тут должен быть callBack : " + ANSI_RESET);
    context.interrupt();
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(" если все задачи завершились или все отменены " + context.isFinished());
    System.out.println("Задачи были не завершены " + context.getInterruptedTaskCount());
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(ANSI_PURPLE);
    ContextImpl.getInformation();
    System.out.println(ANSI_RESET);
    System.out.println("Задачи успешно завершились " + context.getCompletedTaskCount());
    System.out.println("Задачи выбросили исключения " + context.getFailedTaskCount());
    System.out.println("Задачи были не завершены " + context.getInterruptedTaskCount());
  }

  @Test
  public void testNewManager() {
    Runnable callBack = () -> System.out.println("Все выполнено");
    Runnable[] list = new Runnable[10];
    for (int i = 0; i < 10; i++) {
      list[i] = new RunF(i - 2); //list.add((int) (Math.random() * 100));
    }
    ExecutionManager manager = new NewManager(3);
    NewContextImpl context = (NewContextImpl) manager.execute(callBack, list);
    try {
      Thread.sleep(200);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(ANSI_PURPLE + "Всего 10 задач 2 из них кидают Exception" + ANSI_RESET);
    System.out.println("Задачи успешно завершились " + context.getCompletedTaskCount());
    System.out.println("Задачи выбросили исключения " + context.getFailedTaskCount());
    System.out.println("Задачи были не завершены " + context.getInterruptedTaskCount());
    System.out.print(ANSI_PURPLE + "Тут должен быть callBack : " + ANSI_RESET);
    context.interrupt();
    try {
      Thread.sleep(900);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("Все задачи завершились или все отменены?  " + context.isFinished());
    System.out.println("Задачи были не завершены " + context.getInterruptedTaskCount());
    //System.out.println(ANSI_PURPLE);
    //NewContextImpl.getInformation();
    //  System.out.println(ANSI_RESET);
    System.out.println("Задачи успешно завершились " + context.getCompletedTaskCount());
    System.out.println("Задачи выбросили исключения " + context.getFailedTaskCount());
    //System.out.println("Задачи были не завершены " + context.getInterruptedTaskCount());
  }

}
