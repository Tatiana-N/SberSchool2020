package org.nta.lessons.lesson2;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class ReverseIterator<T> implements Iterator<T> {
  private final T[] data;
  private int position;

  public ReverseIterator(List<T> data) {
    System.out.println("Задание 5: Реализуйте свой Iterator для обхода списка в обратном порядке.");
    this.data = (T[]) new Object[data.size()];
    int i = data.size() - 1;
    for (T obj : data) {
      this.data[i--] = obj;
    }
    position = 0;
  }

  @Override
  public boolean hasNext() {
    return position < data.length;
  }

  @Override
  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    return data[position++];
  }
}