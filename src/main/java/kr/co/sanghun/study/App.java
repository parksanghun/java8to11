package kr.co.sanghun.study;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        RunSomething something = () -> {
            System.out.println("Hello");
            System.out.println("Lambda");
        };

        Function<Integer, String> intToString = (i) -> "number";

        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");

        // parallelStream으로 병렬 처리한다고 무조건 빠른 것은 아니다. 방대한 데이터 처리시에 빠르다. -> 정확한 속도는 측정 후 확인
        List<String> collect = names.parallelStream().map((s) -> {
            System.out.println(s + " " + Thread.currentThread().getName());
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);

        names.parallelStream().forEach(System.out::println);

        // 스트림이 처리하는 데이터 소스를 변경하지 않는다.
        Stream<String> stringStream = names.stream().map(String::toUpperCase);
        names.forEach(System.out::println);


        names.sort(String::compareToIgnoreCase);
        names.forEach(System.out::println);

        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        names.sort(compareToIgnoreCase.reversed());
        names.forEach(System.out::println);


        names.removeIf(s -> s.equals("c"));
        names.forEach(System.out::println);

        long a = names.stream().map(String::toUpperCase)
                .filter(s -> s.startsWith("a"))
                .count();
        System.out.println(a);

        names.forEach(System.out::println);

        Spliterator<String> spliterator = names.spliterator();
        Spliterator<String> spliterator1 = spliterator.trySplit();
        while (spliterator.tryAdvance(System.out::println));
        System.out.println("==============");
        while (spliterator1.tryAdvance(System.out::println));
    }
}
