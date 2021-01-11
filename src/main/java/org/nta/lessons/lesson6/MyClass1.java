package org.nta.lessons.lesson6;

public class MyClass1 {
  private String SecondName = "Новиков";
  private int id;
  public int getAge() {
    return id;
  }
  public void setAge(int age) {
    this.id = age;
  }
  private void prINTData(){
    System.out.println(id + SecondName);
  }
}
