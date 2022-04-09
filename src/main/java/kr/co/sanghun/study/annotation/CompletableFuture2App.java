package kr.co.sanghun.study.annotation;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class CompletableFuture2App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        compoundedFutureThenCompose();
        compoundedFutureThenCombine();
        compoundedFutureAllOfThenAccept();
        compoundedFutureAllofThenAndDoFoo();
        futureExceptionally();
        futureHandle();
    }

    private static void futureHandle() throws InterruptedException, ExecutionException {
        boolean throwError = true;
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> {
            if (ex != null) {
                System.out.println(ex);
                return "ERROR1";
            }
            return result;
        });

        System.out.println(hello.get());
    }

    private static void futureExceptionally() throws InterruptedException, ExecutionException {
        boolean throwError = true;
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            if (throwError) {
                throw new IllegalArgumentException();
            }

            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> {
            System.out.println(ex);
            return "Error!";
        });

        System.out.println(hello.get());
    }

    private static void compoundedFutureAllofThenAndDoFoo() throws InterruptedException, ExecutionException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });


        List<CompletableFuture<String>> futures = Arrays.asList(hello, world);
        CompletableFuture<String>[] futuresArray = futures.toArray(new CompletableFuture[futures.size()]);
        CompletableFuture<List<String>> future = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> Arrays.stream(futuresArray)
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()));
        future.get().forEach(System.out::println);

        // 먼저 끝나는 작업을 처리
        CompletableFuture<Void> fu = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
        fu.get();
    }

    private static void compoundedFutureAllOfThenAccept() throws InterruptedException, ExecutionException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<Integer> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return 100;
        });

        CompletableFuture<Void> future = CompletableFuture.allOf(hello, world)
                .thenAccept(System.out::println);
        System.out.println(future.get());
    }

    private static void compoundedFutureThenCombine() throws InterruptedException, ExecutionException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        CompletableFuture<String> future = hello.thenCombine(world, (h, w) -> h + "" + w);
        System.out.println(future.get());
    }

    // 하나를 수행하고 그 값을 받아 다음 처리를 할때
    private static void compoundedFutureThenCompose() throws InterruptedException, ExecutionException {
        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "Hello";
        });

        // hello 이후 작업 추가
        CompletableFuture<String> future = hello.thenCompose(CompletableFuture2App::getWorld);
        System.out.println(future.get());
    }

    private static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return message + "World";
        });
    }
}
