package ru.sbrf.jschool.multithread.part2;

public class WaitNotifyExample {

    private String message;

    public void doMessages(){
        try {
            synchronized (this){
                while (message==null){
                    wait(100);
                    System.out.println("unwait");
                }
                System.out.println("Получено сообщение:"+ message);
                message=null;
            }
        } catch (InterruptedException e) {
            return;
        }
    }

    public void sendMessage(String message){
        synchronized (this){
            this.message=message;
            notify();
        }
    }

    public static void main(String[] args) {
        WaitNotifyExample example = new WaitNotifyExample();

        Thread consumer = new Thread(()->{
            while (true){
                example.doMessages();
                System.out.println("Read");
            }

        });

        Thread produser = new Thread(()->{
            for(int i=0; i<5;i++){
                example.sendMessage(String.format("Message %d", i));

            }
        });

        consumer.start();
        produser.start();


    }
}
