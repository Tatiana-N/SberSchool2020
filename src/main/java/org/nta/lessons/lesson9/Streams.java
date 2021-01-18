package org.nta.lessons.lesson9;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams<T> {
  private Stream<T> stream;

  public void setStream(Stream<T> stream) {
    this.stream = stream;
  }

  public Streams(List<T> list) {
    setStream(list.stream());
  }

  public static <T> Streams<T> of(List<T> list) {
    return new Streams<>(list);
  }

  public Streams<T> filter(Predicate<T> predicate) {
    List<T> list = stream.collect(Collectors.toList());
    return new Streams<>(list.stream().filter(predicate).collect(Collectors.toList()));
  }

  public List<T> toList() {
    return stream.collect(Collectors.toList());
  }


  public <R> Streams<T> transform(Function<T, R> function) {
    List<T> list = stream.collect(Collectors.toList());
    return (Streams<T>) new Streams<>(list.stream().map(function).collect(Collectors.toList()));
  }

  public <R, T> Map<R, T> toMap(Function<T, R> keyFunction, Function<T, T> valueFunction) {
    List<T> list = (List<T>) stream.collect(Collectors.toList());

    return list.stream().collect(Collectors.toMap(keyFunction::apply, valueFunction::apply));
  }
}
