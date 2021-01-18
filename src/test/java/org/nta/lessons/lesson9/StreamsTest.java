package org.nta.lessons.lesson9;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsTest {
  List<Person> people = new ArrayList<>();

  public void getList() {
    people.add(new Person(67, "Sam"));
    people.add(new Person(1, "Mark"));
    people.add(new Person(21, "Elis"));
    people.add(new Person(3, "Kit"));
    people.add(new Person(43, "Olli"));
    people.add(new Person(13, "Tom"));
    people.add(new Person(25, "Alice"));
    people.add(new Person(31, "Kate"));
  }
  @Test
  public void of() {
    getList();
    final List<Person> listImmutable = new ArrayList<>(people); // для проверки, что исходный лист не меняется
    List<Person> list = Streams.of(people).toList();
    Assert.assertArrayEquals(list.toArray(), people.toArray());
    Assert.assertArrayEquals(listImmutable.toArray(), people.toArray());
  }
  @Test
  public void filter() {
    getList();
    final List<Person> listImmutable = new ArrayList<>(people); // для проверки, что исходный лист не меняется
    List<Person> list = Streams.of(people)
      .filter(p -> p.getAge() > 20).toList();
    Assert.assertArrayEquals(list.toArray(), people.stream().filter(p -> p.getAge() > 20).toArray());
    Assert.assertArrayEquals(listImmutable.toArray(), people.toArray());
  }

  @Test
  public void transform() {
    getList();
    final List<Person> listImmutable = new ArrayList<>(people); // для проверки, что исходный лист не меняется
    List<Person> list = Streams.of(people)
      .transform(p -> new Person(p.getAge() + 30, p.getName())).toList();
    Assert.assertArrayEquals(list.toArray(), people.stream().map(p -> new Person(p.getAge() + 30, p.getName())).toArray());
    Assert.assertArrayEquals(listImmutable.toArray(), people.toArray());
  }
  @Test
  public void toMap() {
    getList();
    final List<Person> listImmutable = new ArrayList<>(people); // для проверки, что исходный лист не меняется
    Map<String, Person> map =  Streams.of(people).toMap( Person::getName, person -> person);
    Assert.assertArrayEquals(map.entrySet().toArray(), people.stream().collect(Collectors.toMap(Person::getName, p -> p)).entrySet().toArray());
    Assert.assertArrayEquals(listImmutable.toArray(), people.toArray());
  }
}