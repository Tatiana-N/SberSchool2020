package org.nta.lessons.lesson7.plugins.plugin2;


import org.nta.lessons.lesson7.Plugin;

public class PluginImpl implements Plugin {
  @Override
  public int hashCode() {
    return 60;
  }

  @Override
  public boolean equals(Object obj) {
    return this.hashCode() == obj.hashCode();
  }

  public void doUseful() {
    System.out.println("Ещё кто-то написал меня и я что-то делаю");
  }
}
