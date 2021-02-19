package org.nta.lessons.lesson5.hwterminal.api;

public interface Terminal {
  double checkAccount();

  void putMoney(double money);

  void withdrawMoney(double money);
}
