package org.nta.lessons.lesson9;

import java.util.ArrayList;
import java.util.List;

public class ListPersons {
  List<Person> list = new ArrayList<>();

  public ListPersons(Person person) {
    this.list.add(person);
  }
}
