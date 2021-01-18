package org.nta.lessons.lesson8;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class LoadData {
  public static Map<Object, Object> load(String fileName, boolean zip) {
    File file = new File(fileName.substring(0, fileName.indexOf(".")));
    Map<Object, Object> map = null;
    if (zip) {
      try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(fileName))) {
        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
          // распаковка
          FileOutputStream fout = new FileOutputStream(file);
          int c = zipInputStream.read();
          while (c != -1) {
            fout.write(c);
            c = zipInputStream.read();
          }
          fout.flush();
          zipInputStream.closeEntry();
          fout.close();
        }

      } catch (IOException ex) {
        try {
          Files.createFile(Path.of(fileName));
          map = new TreeMap<>();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    try {
      map = (Map<Object, Object>) new ObjectInputStream(new FileInputStream(file)).readObject();
    } catch (IOException e) {
      map = new TreeMap<>();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    return map;
  }
}

