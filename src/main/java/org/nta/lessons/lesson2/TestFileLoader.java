package org.nta.lessons.lesson2;

import java.util.Iterator;

public class TestFileLoader {
  public static void main(String[] args) {
    FileLoader fileLoader = new FileLoader("C:/Tanusha/BOOKS/test/1.txt");
    FileAnalize.differentWords(fileLoader.getText()); // Задание 1 и 2 и 3 и 4
    Iterator<String> revIterator = FileAnalize.reversedIterator(fileLoader.getText()); // Задание 5
    revIterator.forEachRemaining(System.out::println);
    FileAnalize.printLineByNumber(fileLoader.getText()); // Задание 6
  }
}
