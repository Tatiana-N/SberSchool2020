package org.nta.lessons.lesson9;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsTest {
  static Set<Person> people = new HashSet<>();

  static {
    ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    while (people.size() < 8) {
      Person bean = context.getBean(Person.class);
      people.add(bean);
    }
  }


  @Test
  public void ofList() {
    final List<Person> listImmutable = new ArrayList<>(people); // для проверки, что исходный лист не меняется
    List<Person> list = Streams.of(people).toList();
    Assert.assertArrayEquals(list.toArray(), people.toArray());
    Assert.assertArrayEquals(listImmutable.toArray(), people.toArray());
  }

  @Test
  public void ofTreeSet() {
    final List<Person> listImmutable = new ArrayList<>(people); // для проверки, что исходный лист не меняется
    LinkedHashSet<Person> hashSet = new LinkedHashSet<>(people); // LinkedHashSet чтобы сравнивать
    List<Person> list2 = Streams.of(hashSet).filter(p -> p.getAge() > 20).toList();
    Assert.assertArrayEquals(list2.toArray(), people.stream().filter(p -> p.getAge() > 20).toArray());
    Assert.assertArrayEquals(listImmutable.toArray(), people.toArray());
  }

  @Test
  public void filter() {
    final List<Person> listImmutable = new ArrayList<>(people); // для проверки, что исходный лист не меняется
    List<Person> list = Streams.of(people)
      .filter(p -> p.getAge() > 20).toList();
    Assert.assertArrayEquals(list.toArray(), people.stream().filter(p -> p.getAge() > 20).toArray());
    Assert.assertArrayEquals(listImmutable.toArray(), people.toArray());
  }

  @Test
  public void transform() {
    final List<Person> listImmutable = new ArrayList<>(people); // для проверки, что исходный лист не меняется
    //   Streams.of(people).transform(p ->  p.getName()).toList().forEach(System.out::println);
    List<Person> list = Streams.of(people)
      .transform(p -> new Person(p.getAge() + 30, p.getName())).toList();
    Assert.assertArrayEquals(list.toArray(), people.stream().map(p -> new Person(p.getAge() + 30, p.getName())).toArray());
    Assert.assertArrayEquals(listImmutable.toArray(), people.toArray());
  }

  @Test
  public void toMap() {
    final List<Person> listImmutable = new ArrayList<>(people); // для проверки, что исходный лист не меняется
    Map< Person,String> map = Streams.of(people).toMap(person -> person, Person::getName);
    Assert.assertArrayEquals(map.entrySet().toArray(), people.stream().collect(Collectors.toMap( p -> p, Person::getName)).entrySet().toArray());
    Assert.assertArrayEquals(listImmutable.toArray(), people.toArray());
  }
}