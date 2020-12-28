package org.nta.lessons.lesson5;

public class lesson {
  public static void main(String[] args) {
    Long aLong = 1L;
  //  Integer integer = (int) aLong;
   // integer = (Integer) aLong;
    long l = 1L;
    int i = (int) l;

    try {
      throw new Exception();
    } catch (ArithmeticException e) {
      e.printStackTrace();
    } catch (RuntimeException r) {
    } catch (Exception t) {

    }
  }

}
