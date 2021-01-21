package ru.sbrf.jschool.multithread.part2;

public class DeadlockExample {
    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s" + "  has bowed to me!%n", this.name, bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                            + " has bowed back to me!%n",
                    this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend anna =
                new Friend("Anna");
        final Friend ivan =
                new Friend("Ivan");
        new Thread(new Runnable() {
            @Override
            public void run() {
                //System.out.println("Thread 1");
                anna.bow(ivan);
                //System.out.println("Th: Ivan bowed to anna");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //System.out.println("Thread 2");
                ivan.bow(anna);
                //System.out.println("2.Ivan waiting anna bowed");
            }
        }).start();
    }
}