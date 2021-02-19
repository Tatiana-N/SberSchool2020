package org.nta.lessons.lesson9;

public class Person implements Comparable<Person> {
   private int age;
   private String name;

    public Person() {
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        return String.format("%8s \t %3s", this.name, this.age);

    }

    @Override
    public int hashCode() {
        return this.getAge() + this.getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        Person p;
        if (obj instanceof Person) {
            p = (Person) obj;
        } else {
            throw new ClassCastException();
        }
        return this.getName().equals(p.getName()) && this.getAge() == (p.getAge());
    }

    @Override
    public int compareTo(Person o) {
        return this.getAge() - o.getAge();

    }
}
