package ru.sbrf.jschool.multithread.part1;

public class RunnableExample implements Runnable
{
    @Override
    public void run() {
        for(int i=0; i<10;i++){
            System.out.println(String.format("%d - thread %s is running", i, Thread.currentThread().getName()));
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(new RunnableExample());
        t.setDaemon(true);
        t.start();
        Thread.sleep(5000);
        System.out.printf("Main thread finished");
    }
}
