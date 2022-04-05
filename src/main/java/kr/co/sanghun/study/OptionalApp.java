package kr.co.sanghun.study;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalApp {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        OnlineClass spring_boot = new OnlineClass(1, "spring boot", true);
        Optional<OnlineClass> optional = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        // 일치 하던 하지 않던 createNewClass 함수가 실행된다.
        OnlineClass onlineClass = optional.orElse(createNewClass());
        System.out.println(onlineClass.getTitle());

        OnlineClass onlineClass1 = optional.orElseGet(OptionalApp::createNewClass);

        OnlineClass onlineClass2 = optional.orElseThrow();
        OnlineClass onlineClass3 = optional.orElseThrow(IllegalArgumentException::new);

        Optional<OnlineClass> onlineClass4 = optional.filter(oc -> !oc.isClosed());
        System.out.println(onlineClass4.isEmpty());

        Optional<Optional<Progress>> progress = optional.map(OnlineClass::getProgress);
        Optional<Progress> progress1 = progress.orElse(Optional.empty());

        Optional<Progress> progress2 = optional.flatMap(OnlineClass::getProgress);

    }

    private static OnlineClass createNewClass() {
        System.out.println("creating new online class");
        return new OnlineClass(10, "New class", false);
    }
}
