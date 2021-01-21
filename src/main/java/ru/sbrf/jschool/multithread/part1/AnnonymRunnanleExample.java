package ru.sbrf.jschool.multithread.part1;

public class AnnonymRunnanleExample {
    static Object o = new Object();
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(()-> {
            while (true) {
                System.out.println(String.format("Thread %s run", Thread.currentThread().getName()));
                Thread.yield();

            }
        });
        Thread t2 =new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(String.format("Thread 2 %d iteration", i));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t2.start();

    }
}
