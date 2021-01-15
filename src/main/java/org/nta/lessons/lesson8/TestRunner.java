package org.nta.lessons.lesson8;


import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import java.io.*;
import java.lang.reflect.Proxy;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestRunner {
  @Test
  public void check5size() throws IOException {
    File file1 = new File("SaveData5");
    File file = new File("SaveData");
    file.delete();
    Files.copy(file1.toPath(), file.toPath());

    Service delegate = new Runner();
    Service service = (Service) Proxy.newProxyInstance(ClassLoader.
        getSystemClassLoader(),
      delegate.getClass().getInterfaces(),
      new CachedInvocationHandler(delegate,5));
    PrintStream stream1 = mock(PrintStream.class);
    System.setOut(stream1);

    service.doHardWork("work1", 1); //считает результат
    service.doHardWork("work11", 2); //считает результат
    service.doHardWork("work112", 3); //считает результат
    service.doHardWork("work13", 5); //считает результат
    service.doHardWork("work11", 6); //считает результат

    verify(stream1, times(5)).println("Значение из кэш");
  }
  @Test
  public void check12size() throws IOException {
    File file1 = new File("SaveData12");
    File file = new File("SaveData");
    file.delete();
    Files.copy(file1.toPath(), file.toPath());

    Service delegate = new Runner();
    Service service = (Service) Proxy.newProxyInstance(ClassLoader.
        getSystemClassLoader(),
      delegate.getClass().getInterfaces(),
      new CachedInvocationHandler(delegate,12));
    PrintStream stream1 = mock(PrintStream.class);
    System.setOut(stream1);

     service.doHardWork("work1", 1); //считает результат
     service.doHardWork("work11", 2); //считает результат
     service.doHardWork("work112", 3); //считает результат
    service.doHardWork("work13", 5); //считает результат
    service.doHardWork("work11", 6); //считает результат
    service.doHardWork("work3", 7); //считает результат
    service.doHardWork("work2", 8);  //считает результат
     service.doHardWork("work12", 4); //считает результат
     service.doHardWork("work13", 5); //считает результат
     service.doHardWork("work11", 6); //считает результат
     service.doHardWork("work3", 7); //считает результат
     double r3 = service.doHardWork("work2", 8);  //считает результат

    Assert.assertEquals(8, r3, 0.0);
    verify(stream1, times(12)).println("Значение из кэш");
  }
@Test
  public void soutInConSole(){

  File file = new File("SaveData");
  file.delete();//сначала удалить файл
  PrintStream stream1 = mock(PrintStream.class);
  ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
  System.setOut(stream1);
  Service delegate = new Runner();
  Service service = (Service) Proxy.newProxyInstance(ClassLoader.
      getSystemClassLoader(),
    delegate.getClass().getInterfaces(),
    new CachedInvocationHandler(delegate,3));
  service.doHardWork("work1", 1); //считает результат
  service.doHardWork("work1", 1); //считает результат
  String params = "Значение из кэш";
  verify(stream1).println(captor.capture());
  assertEquals(captor.getValue(), params);




}
}
