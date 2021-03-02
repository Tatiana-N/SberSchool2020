package org.nta.lessons.lesson9;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class TestWithSpring {
  public static void main(String[] args) {
    //ApplicationContext context = new GenericXmlApplicationContext("configForLesson9.xml");
    ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
   // Person person = context.getBean(Person.class);

    List<Person> persons = new ArrayList<>();
    while (persons.size() < 20){
      Person bean = context.getBean(Person.class);
      persons.add(bean);
    }
    persons.forEach(System.out::println);

  }
}
