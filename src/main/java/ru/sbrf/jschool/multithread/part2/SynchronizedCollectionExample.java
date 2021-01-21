package ru.sbrf.jschool.multithread.part2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SynchronizedCollectionExample {

    public static void main(String[] args) throws InterruptedException {

        Collection collection = Collections.synchronizedCollection(new ArrayList());

        Runnable addData = ()->{
            for (int i=0;i<10;i++){
                collection.add(i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread t1 = new Thread(addData);
        Thread t2 = new Thread(addData);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(collection.size());
    }
}
