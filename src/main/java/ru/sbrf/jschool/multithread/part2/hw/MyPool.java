package ru.sbrf.jschool.multithread.part2.hw;

import java.util.Queue;

public interface MyPool {

    void start();

    void execute(Queue<Runnable> queue);
}
