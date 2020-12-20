package org.nta.lessons.lesson3.hw;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class TestCount {
  CountMap<Number> map = new CountMapClass<>();
  CountMapClass<String> countMap = new CountMapClass<>();

  @Test
  public void WeAddElements() {
    map.add(10);
    map.add(10);
    map.add(5);
    map.add(6);
    map.add(5);
    map.add(10);
    map.add(10.0);
    countMap.add("1");
    countMap.add("3");
    countMap.add("1");
    countMap.add("30");
    countMap.add("17");
    countMap.add("3");
    countMap.add("18");
    countMap.add("3");
    countMap.add("3");
    Assert.assertThat(Arrays.asList("1", "3", "1", "30", "17", "3", "18", "3", "3"),
      Is.is(countMap.getList()));
    Assert.assertThat(Arrays.asList(10, 10, 5, 6, 5, 10, 10.0),
      Is.is(((CountMapClass<Number>) map).getList()));
  }

  @Test
  public void WeGetCount() {
    WeAddElements();
    Assert.assertThat(map.getCount(5), Is.is(2));
    Assert.assertThat(map.getCount(6), Is.is(1));
    Assert.assertThat(map.getCount(10), Is.is(3));
  }

  @Test
  public void WeGetToMap() {
    WeAddElements();
    Map<String, Integer> test = new HashMap<>();
    test.put("3", 4);
    test.put("1", 2);
    test.put("30", 1);
    test.put("17", 1);
    test.put("18", 1);
    Assert.assertThat(countMap.toMap(), Is.is(test));
  }

  @Test
  public void WeRemoveOneElement() {
    countMap = new CountMapClass<>();
    WeAddElements();
    countMap.remove("1");
    Assert.assertThat(Arrays.asList("3", "1", "30", "17", "3", "18", "3", "3"),
      Is.is(countMap.getList()));
  }

  @Test
  public void getSize() {
    WeAddElements();
    Assert.assertThat(countMap.size(), Is.is(5));
    Assert.assertThat(map.size(), Is.is(4));
  }

  @Test
  public void WeAddAll() {
    WeAddElements();
    CountMapClass<String> source = new CountMapClass<>(Arrays.asList("new1", "new2", "3", "new3"));
    countMap.addAll(source);
    Assert.assertThat(Arrays.asList("1", "3", "1", "30", "17", "3", "18", "3", "3", "new1", "new2", "3", "new3"),
      Is.is(countMap.getList()));
  //  System.out.println(countMap.toMap());
  }

  @Test
  public void ToMapDestination() {
    WeAddElements();
    Map<String, Integer> test = new HashMap<>();
    countMap.toMap(test);
    Assert.assertThat(test, Is.is(Map.of("3", 4, "1", 2, "30", 1, "17", 1, "18", 1)));
  }
}

