package org.nta.lessons.lesson9;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams<T> {
    private final Stream<T> stream;

    public Streams(Stream<T> stream) {
        this.stream = stream;
    }

    public static <T> Streams<T> of(Collection<T> collection) {
        return new Streams<>(collection.stream());
    }

    public Streams<T> filter(Predicate<? super T> predicate) {
        return new Streams<>(stream.filter(predicate));
    }

    public List<T> toList() {
        return stream.collect(Collectors.toList());
    }


    public <R> Streams<R> transform(Function<? super T, ? extends R> function) {
        return new Streams<>(stream.map(function));
    }

    public <R, V> Map<R, V> toMap(Function<? super T, ? extends R> keyFunction, Function<? super T, ? extends V> valueFunction) {
        return stream.collect(Collectors.toMap(keyFunction, valueFunction));
    }
}
