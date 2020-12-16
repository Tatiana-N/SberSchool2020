package org.nta.lessons.lesson2;

import java.util.*;
import java.util.stream.Collectors;

public class FileAnalysis {
  private Map<String, Integer> differentWords;
  private List<String> linesOfFile;

  public FileAnalysis(List<String> linesOfFile) {
    this.linesOfFile = linesOfFile;
    differentWords(linesOfFile);
  }

  public void differentWords(List<String> list) {
    differentWords = new HashMap<>();
    Iterator<String> iterator = list.iterator();
    while (iterator.hasNext()) {
      String line = iterator.next();
      List<String> stringList = Arrays.stream(line.toLowerCase().split("\\s*(\\s|,|!|;|:|\\.)\\s*")).collect(Collectors.toList());
      for (String word : stringList) {
        if (differentWords.containsKey(word)) {
          differentWords.replace(word, differentWords.get(word) + 1);
        } else {
          differentWords.put(word, 1);
        }
      }
    }
  }

  public void getNumberOfUniqueWords() {
    System.out.println("Задание 1: Подсчитайте количество различных слов в файле.");
    System.out.println("Количество уникальных слов = " + differentWords.size());
  }

  public void getOrderedByLengthWords() {
    System.out.println("Задание 2: Выведите на экран список различных слов файла, отсортированный по возрастанию их длины (компаратор сначала по длине слова, потом по тексту).");
    differentWords.keySet().stream().sorted(Comparator.comparing(String::length)).forEach(System.out::println);
  }

  public void getNumberOfEachWord() {
    System.out.println("Задание 3: Подсчитайте и выведите на экран сколько раз каждое слово встречается в файле.");
    differentWords.forEach((key, value) -> System.out.println(String.format("%15s %2s", key, value)));
  }

  public void getReversedOderLines() {
    System.out.println("Задание 4: Выведите на экран все строки файла в обратном порядке.");
    ReverseIterator<String> reverseIterator = new ReverseIterator<>(this.linesOfFile);
    while (reverseIterator.hasNext()) {
      System.out.println(reverseIterator.next());
    }
  }

  public static void printLineByNumber(List<String> list) {
    System.out.println("Задание 6: Выведите на экран строки, номера которых задаются пользователем в произвольном порядке.");
    System.out.println("------------------------------------------------------------------------------------------------------");
    try (Scanner scanner = new Scanner(System.in)) {

      System.out.println("Для вывода строки введите цифру строки, для выхода введите \"exit\"");
      String str = scanner.nextLine();
      while (!str.equals("exit")) {
        int line = Integer.parseInt(str);
        if (line <= list.size()) {
          System.out.println(list.get(line - 1));
        } else {
          System.out.println("в документе нет строки с таким номером");
        }

        str = scanner.nextLine();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //  public Iterator<String> reversedIterator() {
//    List<String> copyList = new ArrayList<>(linesOfFile);
//    Collections.reverse(copyList);
//    System.out.println("Задание 5: Реализуйте свой Iterator для обхода списка в обратном порядке.");
//    return copyList.iterator();
//  }

}
