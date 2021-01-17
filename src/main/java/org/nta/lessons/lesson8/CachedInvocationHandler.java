package org.nta.lessons.lesson8;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public class CachedInvocationHandler implements InvocationHandler, Serializable {
  private final Object delegate;
  private final int limit;


  public CachedInvocationHandler(Object delegate, int limit) {
    this.delegate = delegate;
    this.limit = limit;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
    Map<Object, Object> cacheMap = LoadData.load();
    String annotatedParameters = getAnnotatedParameters(method, args);
    if (!method.isAnnotationPresent(Cache.class)) {
      return method.invoke(delegate, args);
    }
    if (!cacheMap.containsKey(annotatedParameters)) {
      Object invoke = method.invoke(delegate, args);
      cacheMap.put(annotatedParameters, invoke);
      cacheMap = cacheMap.entrySet().stream()
        .unordered()
        .limit(limit)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));//
      SaveData.save(cacheMap);
      return invoke;
    } else {
      System.out.println("Значение из кэш");
      return cacheMap.get(annotatedParameters);
    }
  }

  private static String getAnnotatedParameters(Method method, Object[] args) {
    List<String> annotatedParameters;
    annotatedParameters = new ArrayList();
    Parameter[] parameters = method.getParameters();
    for (Parameter parameter : parameters) {
      if (parameter.isAnnotationPresent(This.class)) {
        String arg = parameter.getName().replaceAll("\\D", "");//номер параметра, чтобы взять его значение из args
        annotatedParameters.add(args[Integer.parseInt(arg)] + parameter.toString());
      }
    }
    return annotatedParameters.toString();
  }
}
