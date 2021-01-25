package ru.sbrf.jschool.multithread.part2.hw;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyPool {
    private List<Thread> threads;
    private int min;
    private int max;

    public MyPool(int min, int max) {
        this.threads = new ArrayList<>(max);
        this.min = min;
        this.max = max;

    }

    public void doWork(List<Runnable> runnableList) {
        int counttreads = 0;
        for (Runnable runnable : runnableList) {
            while (true) {
                if (counttreads < max) {
                    Thread thread = new Thread(runnable);
                    thread.setName("Thread - my - " + counttreads);
                    threads.add(thread);
                    thread.start();
                    counttreads++;
                    break;
                } else {
                    while (threads.stream().anyMatch(t -> t.getState().equals(Thread.State.TERMINATED))) {
                        List<Integer> collect = threads.stream()
                                .filter(t -> t.getState().equals(Thread.State.TERMINATED))
                                .map(t -> t.getName().substring(t.getName().indexOf("my - ") + 5)).map(Integer::parseInt)
                                .collect(Collectors.toList());
                        List<Thread> collect1 = collect.stream().map(t -> threads.get(t)).collect(Collectors.toList());
                        threads.removeAll(collect1);
                        counttreads -= collect1.size();
                        break;
                    }
                }
            }
        }
    }
}
