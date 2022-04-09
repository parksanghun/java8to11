package kr.co.sanghun.study;

import java.util.concurrent.*;

/**
 * 비동기 프로그래밍을 가능케하는 인터페이스
 */
public class CompletableFutureApp {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        completableFuture();
        runAsync();
        supplyAsyncThenApply();
        supplyAsyncThenAccept();
        supplyAsyncThenRun();
        supplyAsyncThenByPool();
    }

    private static void completableFuture() throws InterruptedException, ExecutionException {
        CompletableFuture<Object> future = new CompletableFuture<>();
        future.complete("sanghun");
        System.out.println(future.get());

        CompletableFuture<String> future1 = CompletableFuture.completedFuture("sanghun");
        System.out.println(future1.get());
    }

    // Return 타입이 없는 경우
    private static void runAsync() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
        });
        System.out.println(runAsync.get());
    }

    // Return 타입이 있는 경우 & Callback도 Return이 있는 경우 thenApply(Function)
    private static void supplyAsyncThenApply() throws InterruptedException, ExecutionException {
        CompletableFuture<String> supplyAsyncThenApply = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenApply((s) -> {
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(supplyAsyncThenApply.get());
    }

    // Return 타입이 있는 경우 & Callback은 결과값은 받지만 Return이 없는 경우 thenAccept(Consumer)
    private static void supplyAsyncThenAccept() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> supplyAsyncThenAccept = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenAccept((s) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });
        System.out.println(supplyAsyncThenAccept.get());
    }

    // Return 타입이 있는 경우 & Callback은 결과값도 받지 않고 Return이 없는 경우 thenAccept(Consumer) -> 수행만 하면 될 경우
    private static void supplyAsyncThenRun() throws InterruptedException, ExecutionException {
        CompletableFuture<Void> supplyAsyncThenRun = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println(supplyAsyncThenRun.get());
    }

    // Pool을 직접 선언하여 넘길 경우
    private static void supplyAsyncThenByPool() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        CompletableFuture<Void> supplyAsyncThenRunByPool = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }, executorService).thenRun(() -> {
            System.out.println(Thread.currentThread().getName());
        });
        System.out.println(supplyAsyncThenRunByPool.get());
    }


    static void oldVersion() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        // JAVA 5
        Future<String> future = executorService.submit(() -> "hello");

        future.get();
    }
}
