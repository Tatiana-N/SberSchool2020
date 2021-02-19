package org.nta.lessons.lesson5.hwterminal.api;

public interface TerminalServer {
    boolean tryConnecting();

    void initializationOfAccount();

    double getBalance();

    boolean putMoney(double money);

    boolean getMoney(double money);
}
