package org.nta.lessons.lesson6.calc;


import java.math.BigDecimal;

public class CalculatorImpl implements Calculator {
  private BigDecimal firstNumber;
  private BigDecimal secondNumber;
  public BigDecimal result = new BigDecimal(1);
  BigDecimal fNumber;

  public void setFirstNumber(BigDecimal firstNumber) {
    this.firstNumber = firstNumber;

  }

  public void setSecondNumber(BigDecimal secondNumber) {
    this.secondNumber = secondNumber;
  }

  public void setResult(BigDecimal result) {
    this.result = result;
  }

  public BigDecimal count(BigDecimal firstNumber, BigDecimal secondNumber, String sign) {
    setFirstNumber(firstNumber);
    setSecondNumber(secondNumber);
    fNumber = firstNumber;
    switch (sign) {
      case ("+"):
        return countSum();
      case ("-"):
        return countDifference();
      case ("*"):
        return countMultiplication();
      case ("/"):
        return countDivision();
      case ("!"):
        return countFactorial(firstNumber);
      default:
        System.out.println("не правильно введен знак");
    }
    return BigDecimal.ZERO;
  }

  @Override
  public BigDecimal countFactorial(BigDecimal firstNumber) {
    if (firstNumber.compareTo(BigDecimal.ZERO) == 0) {
      BigDecimal res = result;
      setResult(BigDecimal.ONE);
      return res;
    }
//    try {
//      Thread.sleep(1);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    setResult(result.multiply(firstNumber));
    firstNumber = firstNumber.subtract(BigDecimal.ONE);
    return countFactorial(firstNumber);
  }

  private BigDecimal countSum() {
    return firstNumber.add(secondNumber);
  }

  private BigDecimal countDivision() {
    return firstNumber.divide(secondNumber, 20, BigDecimal.ROUND_CEILING);
  }

  private BigDecimal countMultiplication() {
    return firstNumber.multiply(secondNumber);
  }

  private BigDecimal countDifference() {
    return firstNumber.subtract(secondNumber);
  }
}
