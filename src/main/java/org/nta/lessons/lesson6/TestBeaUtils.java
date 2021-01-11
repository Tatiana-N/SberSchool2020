package org.nta.lessons.lesson6;

import org.junit.Assert;
import org.junit.Test;

public class TestBeaUtils {
  @Test
  public void isItWork () {
    MyClass myClass = new MyClass();
    myClass.setAge(6);

    MyClass1 myClass1 = new MyClass1();
    myClass1.setAge(12);
    BeanUtils.assign(myClass,myClass1);
    Assert.assertEquals(12,myClass.getAge());
    myClass.setAge(6);
    BeanUtils.assign(myClass1,myClass);
    Assert.assertEquals(6,myClass1.getAge());
  }
}
