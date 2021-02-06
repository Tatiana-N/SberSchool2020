package org.nta.lessons.lesson8;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;

public class CachedInvocationHandler implements InvocationHandler, Serializable {
  private final Object delegate;
  private static Map<Object, Object> cacheMap;


  public CachedInvocationHandler(Object delegate) {
    this.delegate = delegate;
    cacheMap = new HashMap<>();
  }

  public static Map<Object, Object> getCacheMap() {
    return cacheMap;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    String annotatedParameters = getAnnotatedParameters(method, args);
    if (!method.isAnnotationPresent(Cache.class)) {
      return method.invoke(delegate, args);
    }
    // извлечение параметров аннотации
    String fileName = method.getAnnotation(Cache.class).name();
    long limit = method.getAnnotation(Cache.class).limit();
    CacheType cacheType = method.getAnnotation(Cache.class).cacheType();
    boolean zip = method.getAnnotation(Cache.class).zip();
    if (cacheType.equals(CacheType.FILE)) {
      cacheMap = LoadData.load(fileName + ".zip", zip); // если сохраняем в zip или файл
    }
    if (!cacheMap.containsKey(annotatedParameters)) {
      Object invoke = method.invoke(delegate, args);
      cacheMap.put(annotatedParameters, invoke);
      if (limit > 0) { //если лимит не задан или задан не верно, храним всё
        cacheMap = cacheMap.entrySet().stream()
          .unordered()
          .limit(limit)
          .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
      }
      if (cacheType.equals(CacheType.FILE)) {
        synchronized (this) {
          SaveData.save(cacheMap, fileName, zip); // если сохраняем в zip или файл
        }
      }
      return invoke;
    } else {
      System.out.println("Значение из кэш");
      return cacheMap.get(annotatedParameters);
    }
  }

  private static String getAnnotatedParameters(Method method, Object[] args) {
    List<String> annotatedParameters;
    annotatedParameters = new ArrayList<>();
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
