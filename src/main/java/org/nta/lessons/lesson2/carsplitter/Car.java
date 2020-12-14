package org.nta.lessons.lesson2.carsplitter;

public class Car {
  private String model;
  private String type;

  public String getModel() {
    return model;
  }

  public String getType() {
    return type;
  }

  public Car(String model, String type) {
    this.model = model;
    this.type = type;
  }

  @Override
  public String toString() {
    return type + " " + model;
  }
}
