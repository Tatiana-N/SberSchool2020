package org.nta.lessons.lesson8;


import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class SaveData {
  public static void save(Object o, String fileName, boolean zip) {
    File file = new File(fileName);
    try (
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
      objectOutputStream.writeObject(o);
    } catch (IOException e) {
      System.out.println("Не удалось записать данные. Данные будут потеряны");
      e.printStackTrace();
    }
    if (zip) {
      ZipEntry zipEntry = new ZipEntry(fileName);
      try (
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream(fileName + ".zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos)) {
        zipOut.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
          zipOut.write(bytes, 0, length);
        }

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
