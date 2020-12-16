package org.nta.lessons.lesson2;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

class ReverseIteratorWithLIFO<T> implements Iterator<T> {
  private final Stack<T> data;

  public ReverseIteratorWithLIFO(List<T> data) {
    System.out.println("Задание 5: Реализую свой Iterator для обхода списка в обратном порядке.");
    this.data =  new Stack<>();
    for (T obj : data) {
      this.data.push(obj);
    }
  }

  @Override
  public boolean hasNext() {
    return 0 < data.size();
  }

  @Override
  public T next() {
    if (!hasNext()) {
      throw new NoSuchElementException();
    }
    return data.pop();
  }
}