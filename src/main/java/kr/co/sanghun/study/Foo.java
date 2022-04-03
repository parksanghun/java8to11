package kr.co.sanghun.study;

import java.util.function.*;

public class Foo {

    public static void main(String[] args) {
        BinaryOperator<Integer> sum = Integer::sum;

        System.out.println(sum.apply(1, 2));

        // 익명 내부 클래스 anonymous inner class
        RunSomething runSomething = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };

        RunSomething runSomething2 = () -> System.out.println("Hello");
        runSomething2.doIt();

        RunSomething runSomething3 = () -> {
            System.out.println("Hello");
            System.out.println("Hello");
        };

        Function<Integer, Integer> multiply2 = (i) -> i * 2;

        UnaryOperator<Integer> plus10 = (i) -> i + 10;

        plus10.compose(multiply2);
        System.out.println(plus10.andThen(multiply2).apply(2));

        // 타입을 받아서 아무값도 리턴하지 X
        Consumer<Integer> printT = (i) -> System.out.println(i);

        // 값을 그대로 리턴
        Supplier<Integer> get10 = () -> 10;
        System.out.println(get10);

        // true or false return
        Predicate<String> startsWithSangHun = (s) -> s.startsWith("SangHun");
        Predicate<Integer> isOdd = (i) -> i % 2 == 0;
    }

    private void run() {
        final int baseNumber = 10;

        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 10;
                System.out.println(baseNumber);
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(baseNumber);
            }
        };

        // 람다
        IntConsumer printInt = (i) -> {
            System.out.println(i + baseNumber);
        };

        printInt.accept(10);
    }
}
