package ru.sbrf.jschool.multithread.part1;

public class PriorityExample {
    public static void main(String[] args) {
        for(int i=0; i<=4;i++){
            Thread t = new Thread(()->{
                for(int j=0; j<3; j++) {
                    System.out.println(String.format("#%d thread %s with priority %d", j, Thread.currentThread().getName(), Thread.currentThread().getPriority()));
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.setPriority(i%2==0? Thread.MIN_PRIORITY: Thread.MAX_PRIORITY);
            t.start();
        }
    }
}
