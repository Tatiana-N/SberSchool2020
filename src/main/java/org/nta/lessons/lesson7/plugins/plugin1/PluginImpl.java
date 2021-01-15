package org.nta.lessons.lesson7.plugins.plugin1;


import org.nta.lessons.lesson7.Plugin;

public class PluginImpl implements Plugin {
  @Override
  public int hashCode() {
    return 42;
  }

  @Override
  public boolean equals(Object obj) {
    return this.hashCode() == obj.hashCode();
  }

  public void doUseful() {
    System.out.println("Меня написал какой-то человек и я что-то делаю");
  }

}
