package ru.sbrf.jschool.multithread.part1;

public class JoinThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(String.format("%d iteration in thread %s", i, Thread.currentThread().getName()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread t2 = new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(String.format("%d iteration in thread %s", i, Thread.currentThread().getName()));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
        t.join();
        System.out.println("Thread t1 finished");
        t2.join();
        System.out.println("Main thread finished");
    }
}
