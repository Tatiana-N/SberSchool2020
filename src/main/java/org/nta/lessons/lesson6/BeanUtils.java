package org.nta.lessons.lesson6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BeanUtils {
  /**
   * Scans object "from" for all getters. If object "to"
   * contains correspondent setter, it will invoke it
   * to set property value for "to" which equals to the property
   * of "from".
   * <p/>
   * The type in setter should be compatible to the value returned
   * by getter (if not, no invocation performed).
   * Compatible means that parameter type in setter should
   * be the same or be superclass of the return type of the getter.
   * <p/>
   * The method takes care only about public methods.
   *
   * @param to   Object which properties will be set.
   * @param from Object which properties will be used to get values.
   */
  public static void assign(Object to, Object from) {
    Class<?> classTo = to.getClass();
    Class<?> classFrom = from.getClass();

    Map<String, Class<?>> setters = Arrays.stream(classTo.getDeclaredMethods())
      .filter(t -> t.getParameterCount() == 1)  //на всякий случай проверяем что метод принимает только 1 аргумент
      .filter(t -> t.getName().startsWith("set"))
      .collect(Collectors.toMap(key -> key.getName(), value -> value.getParameterTypes()[0]));
    Map<String, Class<?>> getters = Arrays.stream(classFrom.getDeclaredMethods())
      .filter(t -> t.getParameterCount() == 0)  //на всякий случай проверяем что метод ничего не принимает
      .filter(t -> t.getName().startsWith("get"))
      .collect(Collectors.toMap(key -> key.getName(),
        value -> {
          try {
            return value.invoke(from).getClass();
          } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
          }
          return null;
        }));
    for (String s : getters.keySet()) {
      String nameSetter = "s" + s.substring(1);
      if (setters.containsKey(nameSetter) &&
        (setters.get(nameSetter).isAssignableFrom(getters.get(s)) || setters.get(nameSetter).getClass().getName().equals(getters.get(s).getClass().getName()))) {

        try {
          Object getObj = classFrom.getMethod(s).invoke(from); // получить объект из класса from
          Method methodSetObj = classTo.getMethod(nameSetter, setters.get(nameSetter)); // получить метод из класса to
          methodSetObj.invoke(to, getObj); // вызвать сеттер класса to с объектом, полученным из геттера класса from
        } catch (ClassCastException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
          e.printStackTrace();
        }
      }
    }

  }
}


