package org.nta.lessons.lesson17.spring.ConfigAvto;

import org.nta.lessons.lesson5.hwterminal.BankAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.nta.lessons.lesson5.hwterminal")
public class ConfigAvto {
  @Bean
  public BankAccount bankAccount() {
    return new BankAccount("Tom", 3000, 1234);
  }
}