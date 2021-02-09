package org.nta.lessons.lesson10_11.part1;

public class ExceptionHandlerExample {
    public static void main(String[] args) throws InterruptedException {
        try {
            Thread t = new Thread(() -> {
                throw new MyRuntimeException();
            });
            t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable throwable) {
                    if(throwable instanceof MyRuntimeException){
                        System.out.println(((MyRuntimeException) throwable).getCode());
                    }
                }
            });
            t.start();
            Thread.sleep(1000);
            System.out.println("main thread finished");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    static class MyRuntimeException extends RuntimeException {
        public String getCode(){
            return "MyException";
        }
    }



}
