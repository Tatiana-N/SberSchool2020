package org.nta.lessons.lesson8;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;
import java.util.TreeMap;

public class LoadData {

  public static Map<Object, Object> load() {
    Map<Object, Object> map = null;
    try {
      map = (Map<Object, Object>) new ObjectInputStream(
        new FileInputStream("SaveData")).readObject();
    } catch (IOException e) {
      map = new TreeMap<>();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return map;
  }
}
