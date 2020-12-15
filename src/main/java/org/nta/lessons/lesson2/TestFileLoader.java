package org.nta.lessons.lesson2;

public class TestFileLoader {
  public static void main(String[] args) {
    FileLoader fileLoader = new FileLoader("C:/Tanusha/BOOKS/test/1.txt");
    FileAnalysis fileAnalysis = new FileAnalysis(fileLoader.getText());
    fileAnalysis.getNumberOfUniqueWords();// Задание 1
fileAnalysis.getOrderedByLengthWords(); // Задание 2
    fileAnalysis.getNumberOfEachWord(); // Задание 3
    fileAnalysis.getReversedOderLines(); // Задание 4 и 5
    FileAnalysis.printLineByNumber(fileLoader.getText()); // Задание 6
  }
}
