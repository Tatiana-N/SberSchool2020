package org.nta.lessons.lesson9;

public class Person {
  int age;
  String name;

  public Person(int age, String name) {
    this.age = age;
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
   return String.format("%8s \t %3s" , this.name, this.age);

  }

  @Override
  public int hashCode() {
    return this.getAge()+this.getName().hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return this.getName().equals(((Person)obj).getName()) && this.getAge() == (((Person)obj).getAge());
  }
}
