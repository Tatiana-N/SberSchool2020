package ru.sbrf.jschool.multithread.part2;


class Counter{
    static int count;
    public synchronized void incr(){

            //System.out.println(String.format("Thread %s worked", Thread.currentThread().getName()));
            count++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}

public class CounterExample {
    public static void main(String[] args)throws InterruptedException {

        Counter c = new Counter();
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                for(int i=0; i<5; i++){
                    c.incr();
                }

            }
        });

        Counter c2 = new Counter();
        Thread t2 = new Thread(new Runnable(){
            public void run(){
                for(int i=0; i<5; i++) {
                    c2.incr();

                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count = " + c.count);
    }
}