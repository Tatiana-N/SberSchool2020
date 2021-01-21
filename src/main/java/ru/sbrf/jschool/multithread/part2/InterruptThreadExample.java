package ru.sbrf.jschool.multithread.part2;

import java.util.concurrent.TimeUnit;

public class InterruptThreadExample {

    public static void main(String[] args) throws InterruptedException {
        MyRunnable runnable = new MyRunnable();
        Thread t = new Thread(runnable);
        t.start();

        Thread.sleep(2000);
        t.interrupt();
        System.out.println("main finished");
    }
}

class MyRunnable implements Runnable {
    private volatile boolean endFlag = false;

    public void end() {
        endFlag = true;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
