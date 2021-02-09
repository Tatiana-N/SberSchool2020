package org.nta.lessons.lesson14;

import org.junit.Assert;
import org.junit.Test;
import org.nta.lessons.lesson8.CachedInvocationHandler;
import org.nta.lessons.lesson8.Runner;
import org.nta.lessons.lesson8.Service;

import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestRunnerMulti {


  @Test
  public void testUnSafeWithMultiThreaded0() {
    for (int i = 0; i < 1000; i++) {
      testing();
    }
  }

  @Test
  public void threadSafeTest0() {
    for (int i = 0; i < 1000; i++) {
      threadSafeTesting();
    }
  }


  public static void testing() {
    //long time1 = new Date().getTime();
    Service delegate = new Runner();
    Service service = (Service) Proxy.newProxyInstance(ClassLoader.
        getSystemClassLoader(),
      delegate.getClass().getInterfaces(),
      new CachedInvocationHandler(delegate));
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < 20; i++) {
      map.put("work" + (int) (Math.random() * 20), (int) (Math.random() * 10));
    }
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    List<Runnable> list = new ArrayList<>();
    for (Map.Entry<String, Integer> stringDoubleEntry : map.entrySet()) {
      list.add(() -> service.doHardWorkInMemory(stringDoubleEntry.getKey(), stringDoubleEntry.getValue()));
    }
    for (Runnable runnable : list) {
      executorService.submit(runnable);
    }
    try {
      Thread.sleep(10);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Assert.assertEquals(map.size(), CachedInvocationHandler.getCacheMap().size());
    executorService.shutdown();
    // System.out.println(new Date().getTime() - time1 + " милиСекунды");
  }

  public static void threadSafeTesting() {
    // long time1 = new Date().getTime();
    Service delegate = new Runner();
    Service service = (Service) Proxy.newProxyInstance(ClassLoader.
        getSystemClassLoader(),
      delegate.getClass().getInterfaces(),
      new ThreadSafeCachedInvocationHandler(delegate));
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < 20; i++) {
      map.put("work" + (int) (Math.random() * 20), (int) (Math.random() * 10));
    }
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    List<Runnable> list = new ArrayList<>();
    for (Map.Entry<String, Integer> stringDoubleEntry : map.entrySet()) {
      list.add(() -> service.doHardWorkInMemory(stringDoubleEntry.getKey(), stringDoubleEntry.getValue()));
    }
    for (Runnable runnable : list) {
      executorService.submit(runnable);
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    Assert.assertEquals(map.size(), ThreadSafeCachedInvocationHandler.getCacheMap().size());
    executorService.shutdown();
    // System.out.println(new Date().getTime() - time1 + " милиСекунды");
  }
}
