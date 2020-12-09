package org.nta.lessons.lesson1;

public class BubbleSort {
  public static void main(String[] args) {
    BubbleSort bSort = new BubbleSort();
    int[] massive = {4, 3, 7, 6, 4, 8, 5, 0, 9, 1, 2};
    bSort.sortMassive(massive);
    for (int a : massive) {
      System.out.print(a + " ");

    }
  }

  public int[] sortMassive(int[] massive) {
    boolean changed = true;
    while (changed) {
      changed = false;
      for (int i = 1; i < massive.length; i++) {
        if (massive[i - 1] > massive[i]) {
          changed = true;
          int temp = massive[i - 1];
          massive[i - 1] = massive[i];
          massive[i] = temp;
        }
      }
    }
    return massive;
  }
}
