package org.nta.lessons.lesson2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileLoader {
  private String fileName;
  private ArrayList<String> text;

  public FileLoader(String fileName) {
    this.fileName = fileName;
    text = new ArrayList<>(100);
    try (BufferedReader br = new BufferedReader(new FileReader(this.fileName))) {
      while (br.ready()) {
        String line = br.readLine();
        text.add(line); // переписываем всё в list
      }
    }
    catch (IOException e) {
    }
  }

  public ArrayList<String> getText() {
    return text;
  }
}
