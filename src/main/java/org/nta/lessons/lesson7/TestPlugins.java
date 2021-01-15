package org.nta.lessons.lesson7;

import org.junit.Assert;
import org.junit.Test;


public class TestPlugins {
  @Test
  public void getPlugin() {
    PluginManager pluginManager = new PluginManager("C:/projects/SberSchool/target/classes/org/nta/lessons/lesson7/plugins");
    Plugin plugin1 = pluginManager.load("plugin1", "org.nta.lessons.lesson7.plugins.plugin1.PluginImpl");
    System.out.print(plugin1.getClass().getSimpleName() + " делает: ");
    plugin1.doUseful();
    Plugin check1 = new org.nta.lessons.lesson7.plugins.plugin1.PluginImpl(); // это тот что мы хотим получить
    Assert.assertEquals(plugin1, check1);
    Plugin plugin2 = pluginManager.load("plugin2", "org.nta.lessons.lesson7.plugins.plugin2.PluginImpl");
    System.out.print(plugin2.getClass().getSimpleName() + " делает: ");
    plugin2.doUseful();
    Plugin check2 = new org.nta.lessons.lesson7.plugins.plugin2.PluginImpl();// это тот что мы хотим получить
    Assert.assertEquals(plugin2, check2);
  }
}
