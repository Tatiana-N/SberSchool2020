package org.nta.lessons.lesson5.hwterminal;

import lombok.Getter;
import lombok.Setter;
import org.nta.lessons.lesson5.hwterminal.api.Terminal;
import org.nta.lessons.lesson5.hwterminal.api.TerminalServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Getter
@Setter
public class TerminalImpl implements Terminal {
  @Autowired
  private TerminalServer server;

  public TerminalImpl(TerminalServer server) {
    this.server = server;
  }

  public TerminalImpl() {
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
