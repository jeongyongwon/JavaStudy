package optional;

import domain.OnlineClass;
import domain.Progress;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
//        optional1();
        optional2();
    }

    public static void optional1() {

        List<OnlineClass> springClasses = new ArrayList<>();

        springClasses.add(new OnlineClass(1,"Spring boot", true));
        springClasses.add(new OnlineClass(2,"Spring data jpa", true));
        springClasses.add(new OnlineClass(3,"Spring mvc", false));
        springClasses.add(new OnlineClass(4,"Spring core", false));
        springClasses.add(new OnlineClass(5,"rest api development", false));
        //Optional.of(10) 이러한 primitive type도 좋지않다 (성능 이슈  -> boxing, unboxing을 하기때문에)
        //OptionalInt(10) 가 있으니 이러걸 쓰자


        //reference 클래스 null이 있으니까
        OnlineClass spring_boot = new OnlineClass(1, "Spring boot", true);
//        Duration studyDuration = spring_boot.getStudyDuration().getStudyDuration();
        Optional<Progress> progress = spring_boot.getProgress();
        if(progress == null) {
            System.out.println("null임 "); //인간의 실수가 있을 수 있다
        }

    }

    public static void optional2() {
        List<OnlineClass> springClasses = new ArrayList<>();

        springClasses.add(new OnlineClass(1,"Spring boot", true));
        springClasses.add(new OnlineClass(2,"Spring data jpa", true));

        //있을 수도 없을수도 있으니까
        Optional<OnlineClass> optional = springClasses.stream()
                .filter(x -> x.getTitle().startsWith("Spring"))
                .findFirst();

        boolean present = optional.isPresent();
        boolean present2 = optional.isEmpty();

        //만약 optional 안에 없을 경우  NosuchElementException이 뜬다 => RuntimeException
        //이렇게 if문으로 기존처럼 걸러줄 수도 있고
//        if (optional.isPresent()) {
//            OnlineClass onlineClass = optional.get();
//            System.out.println(onlineClass); //
//        }

        //단순히 작업을 할땐 ifPresent로 단순히 꺼낼 수 있고
        optional.ifPresent(x -> {
            System.out.println(x.getTitle());
        });

        //아예 변수 선언을 위해 orElse를 쓸 수 있다.
        OnlineClass onlineClass = optional.orElse(new OnlineClass(10,  "new class", false));

        //없을 때 에러를 던짐
//        OnlineClass onlineClass2 = optional.orElseThrow(() -> {
//            return new IllegalArgumentException();
//        });

        //에러를 class reference로 던질 수 있다
//        OnlineClass onlineClass3 = optional.orElseThrow(IllegalStateException::new);

        //class reference로 filter도 할 수 있다.
        Optional<OnlineClass> onlineClass4 = optional.filter(OnlineClass::isClosed);

        //이렇게 꺼낼 수도 있다.
        Optional<Integer> onlineClassId = optional.map(OnlineClass::getId);
        System.out.println("==?" + onlineClassId.get());

        //Optional에 Optional이 잇는 구조면 flatMap을 쓰자
        Optional<Progress> progressFlatMap = optional.flatMap(OnlineClass::getProgress);
    }

}
