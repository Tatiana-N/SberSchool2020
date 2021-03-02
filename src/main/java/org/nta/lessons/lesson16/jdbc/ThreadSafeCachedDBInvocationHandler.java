package org.nta.lessons.lesson16.jdbc;

import org.nta.lessons.lesson8.CacheType;
import org.nta.lessons.lesson8.SaveData;
import org.nta.lessons.lesson8.This;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class ThreadSafeCachedDBInvocationHandler implements InvocationHandler, Serializable {
  private final Object delegate;
  private static Map<Object, Object> cacheMap;
  private final Lock lock = new ReentrantLock();

  public static Map<Object, Object> getCacheMap() {
    return cacheMap;
  }

  public ThreadSafeCachedDBInvocationHandler(Object delegate) {
    this.delegate = delegate;
    cacheMap = new HashMap<>();


  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
    // исправить
    CacheType cacheType = null;
    long limit = 0;
    boolean zip = false;
    String fileName = "";
    // исправить
    String annotatedParameters = getAnnotatedParameters(method, args);
    if (!method.isAnnotationPresent(Cacheable.class)) {
      return method.invoke(delegate, args);
    }
    // извлечение параметров аннотации
    Class db = method.getAnnotation(Cacheable.class).db();

    lock.lock();
    try {
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
    } finally {
      lock.unlock();
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
