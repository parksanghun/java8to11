package kr.co.sanghun.study;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        RunSomething something = () -> {
            System.out.println("Hello");
            System.out.println("Lambda");
        };
    }
}
