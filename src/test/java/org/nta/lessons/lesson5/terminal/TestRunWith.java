package org.nta.lessons.lesson5.terminal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.nta.lessons.lesson17.spring.TestTerminalWithSpring;

@RunWith(Suite.class)//запускает подходящие тесты
 //@RunWith(Categories.class) - подходящие тесты должны быть помечены категориями
@Suite.SuiteClasses({JunitTest.class, TestTerminalWithSpring.class})
public class TestRunWith {
}
