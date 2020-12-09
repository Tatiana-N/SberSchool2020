package org.nta.lessons.lesson1;

public class BubbleSort<T extends Comparable> {
  public static void main(String[] args) {
    BubbleSort bSort = new BubbleSort();
    Integer [] array1 = {4, 3, 7, 6, 4, 8, 5, 0, 9, 1, 2};
    String [] array2 = {"sun","apple","in","row","pare","asia","knot"};
    bSort.sortArray(array1);
    bSort.sortArray(array2);
    for (Integer a : array1) {
      System.out.print(a + " ");
    }
    System.out.println();
    for (String a : array2) {
      System.out.print(a + " ");
    }
  }

  public T[] sortArray(T[] array) {
    boolean changed = true;
    while (changed) {
      changed = false;
      for (int i = 1; i < array.length; i++) {
        if (array[i - 1].compareTo(array[i]) > 0) {
          changed = true;
          T temp = array[i - 1];
          array[i - 1] = array[i];
          array[i] = temp;
        }
      }
    }
    return array;
  }
}
