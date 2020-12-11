package org.nta.lessons.lesson1;

import java.util.Arrays;

public class SearcherInArray<T extends Comparable> {
    public static void main(String[] args) {
        SearcherInArray bSort = new SearcherInArray();
        Integer[] array1 = {4, 3, 7, 6, 4, 8, 5, 0, 9, 1, 2};
        String[] array2 = {"sun", "apple", "in", "row", "pare", "asia", "knot"};

        for (Integer a : array1) {
            System.out.print(a + " ");
        }
        System.out.println();
        for (String a : array2) {
            System.out.print(a + " ");
        }
        System.out.println();
        System.out.println(bSort.searchNumberOfElementInArray(array1, 9));
        System.out.println(bSort.searchNumberOfElementInArray(array2, "apple"));
        System.out.println(bSort.searchNumberOfElementInArray(array2, "trtr"));
    }

    /**
     *
     * @param array
     * @param element element for searching in array
     * @return number of element in Array
     * return -1 if this array isn't consist this element
     */
    public int searchNumberOfElementInArray(T[] array, T element) {
        this.sortArray(array);
      // Arrays.sort(array);
        int BeginEdge = 0;
        int find = array.length / 2;
        while (!array[find].equals(element) && BeginEdge != find) {
            if (array[find].compareTo(element) > 0) {

                find = find / 2;
            } else {
                BeginEdge = find;
                find = find + (array.length - find) / 2;
            }
        }
        if (array[find].compareTo(element) == 0) {
            return find;
        } else {
            return -1;
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
