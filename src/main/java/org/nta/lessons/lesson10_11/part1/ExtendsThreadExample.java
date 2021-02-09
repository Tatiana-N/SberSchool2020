package org.nta.lessons.lesson10_11.part1;

public class ExtendsThreadExample extends Thread {
    @Override
    public void run() {
        for(int i=0; i<10;i++){
            System.out.println(String.format("%d - thread %s is running", i, Thread.currentThread().getName()));
        }
    }

    public static void main(String[] args) {
        for(int i=0; i<10;i++) {
            ExtendsThreadExample t = new ExtendsThreadExample();
            t.start();
        }
    }
}
