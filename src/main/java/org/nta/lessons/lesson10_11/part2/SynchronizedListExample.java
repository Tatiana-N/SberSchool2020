package org.nta.lessons.lesson10_11.part2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SynchronizedListExample {

    public static void main(String[] args) throws InterruptedException {
        List async = new LinkedList(Arrays.asList("test1","test2","test3","test4","test5","test6","test7","test8","test9","test10"));
        List sync = Collections.synchronizedList(async);

        Runnable r = () -> {
            for(int i=0; i<3;i++){
                if(async.size()>0){
                    async.remove(0);
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("List "+ async);
    }
}
