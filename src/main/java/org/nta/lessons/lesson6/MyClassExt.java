package org.nta.lessons.lesson6;

public class MyClassExt extends MyClass{
  private final String name;
  private  int age;
  public static final String MONDAY = "MONDAY";
  public MyClassExt(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }
  public void setAge(int age) {
    this.age = age;
  }
  private void printData(){
    System.out.println(age + name);
  }
}
