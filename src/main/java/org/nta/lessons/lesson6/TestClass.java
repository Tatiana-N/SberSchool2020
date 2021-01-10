package org.nta.lessons.lesson6;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class TestClass {
  MyClass myClassExt = new MyClassExt("Tom");
  MyClass myClassExt2 = new MyClassExt("name");

  /**
   * Задача 2:
   * Вывести на консоль все методы класса, включая все родительские методы
   * (включая приватные)
   */
  // выводит только public методы класса и предков:
  // Method [] methods = myClassExt.getClass().getMethods();
  // for (Method method : methods) {
  //    method.setAccessible(true);
  //    System.out.println(method);
  // }
  @Test
  public void printAllMethods() {
    System.out.println("------------------Задача 2-----------------------");
    Class<? extends MyClass> aClass = myClassExt.getClass();
    while (aClass != null) {
      Arrays.stream(aClass.getDeclaredMethods()).forEach(System.out::println);
      aClass = (Class<? extends MyClass>) aClass.getSuperclass();
    }
  }

  /**
   * //Задача 3:
   * //Вывести все геттеры класса
   */
  @Test
  public void getGetters() {
    System.out.println("-----------------------------------------");

    System.out.println("--------------------Задача 3---------------------");
    Arrays.stream(myClassExt.getClass().getDeclaredMethods()).filter(t -> t.getName().startsWith("get")).forEach(System.out::println);
    System.out.println("-----------------------------------------");
  }

  /**
   * Задача 4:
   * Проверить что все String константы имеют значение = их имени
   * public static final String MONDAY = "MONDAY";
   */
  @Test
  public void checkConstant() throws IllegalAccessException {

    System.out.println("----------------------Задача 4-------------------");
    Field[] declaredFields = myClassExt.getClass().getDeclaredFields();
    for (Field declaredField : declaredFields) {

      if (declaredField.getType().getSimpleName().equals(String.class.getSimpleName())
        && Modifier.isFinal(declaredField.getType().getModifiers())) {

        declaredField.setAccessible(true);
        String name = declaredField.getName();
        String value = (String) declaredField.get(myClassExt);
        if (!name.equals(value)) {
          System.out.println("есть несовпадающие значения : " + name + " и " + value);
        }

      }
    }

    System.out.println("-----------------------------------------");
  }

  @Test
  public void checkOpportunities() throws NoSuchFieldException, IllegalAccessException {
    myClassExt.setAge(7);
    int age = myClassExt.getAge();
    Field nameField = myClassExt.getClass().getDeclaredField("name");
    nameField.setAccessible(true);
    String name = (String) nameField.get(myClassExt);
    System.out.println(age + " " + name);
    //Class clazz =  Class.forName("org.nta.lessons.lesson6.MyClass");
    Field field = null;
    field = myClassExt.getClass().getDeclaredField("name");
    Arrays.stream(myClassExt.getClass().getFields()).forEach(System.out::println);
    field.setAccessible(true);
    field.set(myClassExt, (String) "New name");
    field.setAccessible(true);
    name = (String) field.get(myClassExt);
    System.out.println(age + " " + name);
  }
}
