package org.nta.lessons.lesson8;

public interface Service {
  @Cache
  double doHardWork(@This String name, Integer value);
}
