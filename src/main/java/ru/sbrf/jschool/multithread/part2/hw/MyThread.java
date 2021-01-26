package ru.sbrf.jschool.multithread.part2.hw;


import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThread extends Thread {
    final Queue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();
    volatile boolean running = true;

    public void doThis(Runnable runs) {
        tasks.add(runs);
    }

    public void run() {
        while (running) {
            Runnable runs = tasks.poll();
            try {
                runs.run();
                // System.out.println(Thread.currentThread().getName() + " do hard work");
                Thread.sleep(2000);
            } catch (Throwable t) {

            }
        }
    }

    public void stopRunning() {
        while (!tasks.isEmpty()) {
        }
        running = false;
    }
}


