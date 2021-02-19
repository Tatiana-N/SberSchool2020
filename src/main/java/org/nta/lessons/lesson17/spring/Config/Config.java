package org.nta.lessons.lesson17.spring.Config;

import org.nta.lessons.lesson5.hwterminal.BankAccount;
import org.nta.lessons.lesson5.hwterminal.SimplePinValidator;
import org.nta.lessons.lesson5.hwterminal.SimpleTerminalServer;
import org.nta.lessons.lesson5.hwterminal.TerminalImpl;
import org.nta.lessons.lesson5.hwterminal.api.PinValidator;
import org.nta.lessons.lesson5.hwterminal.api.Terminal;
import org.nta.lessons.lesson5.hwterminal.api.TerminalServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
  @Bean
  public BankAccount bankAccount() {
    return new BankAccount("Tom", 3000, 1234);
  }

  @Bean
  public PinValidator simplePinValidator() {
    return new SimplePinValidator(bankAccount());
  }

  @Bean
  public Terminal terminal() {
    return new TerminalImpl(server());
  }

  @Bean
  public TerminalServer server() {
    return new SimpleTerminalServer(bankAccount());
  }
}
