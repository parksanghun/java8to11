package kr.co.sanghun.study;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorApp {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> System.out.println("Thread " + Thread.currentThread().getName()));
        executorService.shutdown(); // graceful -> 현재 진행중인 작업은 마치고 종료
        executorService.shutdownNow(); // 바로 종료

        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        executorService1.submit(getRunnable("Hello"));
        executorService1.submit(getRunnable("SangHun"));
        executorService1.submit(getRunnable("The"));
        executorService1.submit(getRunnable("Java"));
        executorService1.submit(getRunnable("Thread"));
        executorService1.shutdown();

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.schedule(getRunnable("Hello"), 3, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        ScheduledExecutorService scheduledExecutorService1 = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService1.scheduleAtFixedRate(getRunnable("Hello"), 1, 2, TimeUnit.SECONDS);
    }

    private static Runnable getRunnable(String message) {
        return () -> {
            System.out.println(message + Thread.currentThread().getName());
        };
    }
}
