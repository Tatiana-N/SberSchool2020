package org.nta.lessons.lesson8;


public class Runner implements Service {

  @Override
  public double doHardWorkInMemory(String name, Integer value) {

    return value;
  }

  @Override
  public double doHardWorkFile(String name, Integer value) {

    return value;
  }

  @Override
  public double doHardWorkZip(String name, Integer value) {

    return value;
  }
}
