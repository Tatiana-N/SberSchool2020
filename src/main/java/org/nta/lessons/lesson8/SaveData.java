package org.nta.lessons.lesson8;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveData {
  public static void save(Object o) {
    ObjectOutputStream objectOutputStream = null;
    try {
      objectOutputStream = new ObjectOutputStream(new FileOutputStream("SaveData"));
      objectOutputStream.writeObject(o);
    } catch (IOException e) {
      System.out.println("Не удалось записать данные. Данные будут потеряны");
      e.printStackTrace();
    }
  }
}
