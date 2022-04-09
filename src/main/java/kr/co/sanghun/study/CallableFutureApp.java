package kr.co.sanghun.study;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableFutureApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(4000L);
            return "Java";
        };

        Callable<String> sanghun = () -> {
            Thread.sleep(2000L);
            return "SangHun";
        };

        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello, java, sanghun));
        for (Future<String> f : futures) {
            f.get();
        }

        Future<String> helloFuture = executorService.submit(hello);
        System.out.println(helloFuture.isDone());
        System.out.println("Started!");

        helloFuture.cancel(false);
        helloFuture.get(); // 블록킹 콜

        System.out.println(helloFuture.isDone());
        System.out.println("End!!");
        executorService.shutdown();
    }
}
