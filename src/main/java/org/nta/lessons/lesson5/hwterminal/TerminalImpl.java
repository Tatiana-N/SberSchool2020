package org.nta.lessons.lesson5.hwterminal;


public class TerminalImpl implements Terminal {
  private final TerminalServer server;

  public TerminalImpl(TerminalServer server) {
    this.server = server;
  }

  @Override
  public double checkAccount() {
    return server.getBalance();
  }

  @Override
  public void putMoney(double money) {
    server.putMoney(money);
  }

  @Override
  public void withdrawMoney(double money) {
    server.getMoney(money);
  }
}
