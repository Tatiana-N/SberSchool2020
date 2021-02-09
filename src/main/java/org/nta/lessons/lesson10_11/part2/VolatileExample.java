package org.nta.lessons.lesson10_11.part2;

public class VolatileExample {

    public static void main(String[] args) {
        new VolatileExample().start();
    }

    private volatile boolean btExit = false;

    // запуск потоков
    public void start() {
        new Thread(gui).start();
        System.out.println("gui thread started");
        new Thread(game).start();
        System.out.println("game thread started");
    }

    Runnable gui = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ignored) {}
            btExit = true; // поток game не видит это изменение без volatile
            System.out.println("gui thread finished");
        }
    };

    // поток для игры
    Runnable game = new Runnable() {
        @Override
        public void run() {
            while (!btExit) {
                // без volatile этот цикл крутится бесконечно
            }
            System.out.println("game thread finished");
        }
    };
}
