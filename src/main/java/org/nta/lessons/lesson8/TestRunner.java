package org.nta.lessons.lesson8;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Proxy;

import static org.mockito.Mockito.*;

public class TestRunner {
  @Test
  public void fileSecondParameters() {
    // @Cache(cacheType = cacheType.FILE, zip = false)
    // doHardWorkFile(String name, @This Integer value);
    PrintStream stream = mock(PrintStream.class);
    System.setOut(stream);
    Service delegate = new Runner();
    Service service = (Service) Proxy.newProxyInstance(ClassLoader.
        getSystemClassLoader(),
      delegate.getClass().getInterfaces(),
      new CachedInvocationHandler(delegate));
    service.doHardWorkFile("work1", 1); //считает результат
    service.doHardWorkFile("work2", 2); //считает результат
    service.doHardWorkFile("work3", 2);                                              //возьмет из кэша
    service.doHardWorkFile("work4", 1);                                              //возьмет из кэша
    Assert.assertEquals(service.doHardWorkFile("work5", 2), 2.0, 0.0);   //возьмет из кэша
    verify(stream, times(3)).println("Значение из кэш");
  }

  @Test
  public void inMemoryFirstParameters() {
    // @Cache(cacheType = cacheType.IN_MEMORY,limit = 20,zip = false)
    // doHardWorkInMemory(@This String name, Integer value);
    PrintStream stream = mock(PrintStream.class);
    System.setOut(stream);
    Service delegate = new Runner();
    Service service = (Service) Proxy.newProxyInstance(ClassLoader.
        getSystemClassLoader(),
      delegate.getClass().getInterfaces(),
      new CachedInvocationHandler(delegate));
    service.doHardWorkInMemory("work1", 1); //считает результат
    service.doHardWorkInMemory("work11", 2); //считает результат
    service.doHardWorkInMemory("work112", 3); //считает результат
    service.doHardWorkInMemory("work13", 5); //считает результат
    service.doHardWorkInMemory("work11", 6);                                             //возьмет из кэша
    service.doHardWorkInMemory("work3", 7); //считает результат
    service.doHardWorkInMemory("work2", 8);  //считает результат
    service.doHardWorkInMemory("work12", 4); //считает результат
    service.doHardWorkInMemory("work13", 5);                                            //возьмет из кэша
    service.doHardWorkInMemory("work11", 645);                                          //возьмет из кэша
    service.doHardWorkInMemory("work3", 7); //возьмет из кэша
    double r3 = service.doHardWorkInMemory("work2", 234);                               //возьмет из кэша
    Assert.assertEquals(r3, 8.0, 0.0);                                                  //возьмет из кэша
    Assert.assertEquals(service.doHardWorkInMemory("work11", 6), 2.0, 0.0); //возьмет из кэша
    verify(stream, times(6)).println("Значение из кэш");
  }

  @Test
  public void zipBothParameters() {
    // @Cache(name = "One", cacheType = cacheType.FILE, limit = 100_000, zip = true)
    // doHardWorkZip(@This String name, @This Integer value);
    PrintStream stream = mock(PrintStream.class);
    System.setOut(stream);
    Service delegate = new Runner();
    Service service = (Service) Proxy.newProxyInstance(ClassLoader.
        getSystemClassLoader(),
      delegate.getClass().getInterfaces(),
      new CachedInvocationHandler(delegate));
    service.doHardWorkZip("work1", 1); //считает результат
    service.doHardWorkZip("work1", 3); //считает результат
    verify(stream, times(0)).println("Значение из кэш");
    service.doHardWorkZip("work1", 3);                                                 //возьмет из кэша
    verify(stream, times(1)).println("Значение из кэш");
  }
}
