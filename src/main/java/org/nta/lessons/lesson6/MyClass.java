package org.nta.lessons.lesson6;

public class MyClass {
  private String SecondName = "Новиков";
  private int id;
  public int getAge() {
    return id;
  }
  public void setAge(Number age) {
    this.id = (int)age;
  }
  private void printData(){
    System.out.println(id + SecondName);
  }
}
