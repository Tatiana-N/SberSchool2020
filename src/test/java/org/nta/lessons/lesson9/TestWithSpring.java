package org.nta.lessons.lesson9;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class TestWithSpring {
  public static void main(String[] args) {
    ApplicationContext context = new GenericXmlApplicationContext("configForLesson9.xml");
   // Person person = context.getBean(Person.class);
    ListPersons bean = context.getBean(ListPersons.class);
    List<Person> list = bean.list;
    for (Person person : list) {
      System.out.println(person.toString());
    }

  }
}
