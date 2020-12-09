package org.nta.lessons.lesson1;

public class ConverterOfTemperature {
  public static void main(String[] args) {
    ConverterOfTemperature converter = new ConverterOfTemperature();
    System.out.println(converter.convertToFahrenheit(23));
    System.out.println(converter.convertToRankine(45));
    System.out.println(converter.convertToKelvin(67));
    System.out.println(converter.convertToReaumur(67));
  }

  public double convertToFahrenheit(double celsiusTemperature) {
    return (celsiusTemperature * 9 / 5) + 32;
  }

  public double convertToRankine(double celsiusTemperature) {
    return ((celsiusTemperature) + 273.15) * 9 / 5;
  }

  public double convertToKelvin(double celsiusTemperature) {
    return celsiusTemperature + 273.15;
  }

  public double convertToReaumur(double celsiusTemperature) {
    return celsiusTemperature * 0.8;
  }
}
