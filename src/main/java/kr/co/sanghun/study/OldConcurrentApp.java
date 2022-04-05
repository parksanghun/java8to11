package kr.co.sanghun.study;

public class OldConcurrentApp {
    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println("Hello: " + Thread.currentThread().getName());

        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("Thread: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("exit!");
                    return;
                }
            }
        });
        thread.start();
        System.out.println("Hello: " + Thread.currentThread().getName());
        Thread.sleep(3000L);
        thread.interrupt();

        Thread thread2 = new Thread(() -> {
            System.out.println("Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        });
        thread2.start();

        System.out.println("Hello: " + Thread.currentThread().getName());
        thread.join(); // 쓰레드가 끝날때까지 기다린다.
        System.out.println(thread2 + " is finished");
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread: " + Thread.currentThread().getName());
        }
    }
}
