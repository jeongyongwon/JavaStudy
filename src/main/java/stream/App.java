package stream;

import optional.domain.OnlineClass;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
//        stream1();
        stream2();
    }

    public static void stream1() {
        List<String> names = new ArrayList<>();

        names.add("tyog1");
        names.add("yongwon1");
        names.add("tsodod1");
        names.add("gigigg1");

        //중개 오퍼레이터 Stream 타입을 반환한다
        Stream<String> stringStream = names.stream().map(String::toUpperCase);

        List<String> collectList = names.stream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collectList.forEach(System.out::println);

        System.out.println("===============");

        names.forEach(System.out::println); //종료 오퍼레이터 (Stream type을 반환하지 않는다

        //병렬 처리를 씀
        //무조건 빠르다는건 아님
        //context switching 고려하자
        //진짜 데이터 많아질 때 효율적일 `가능성`이 높다
        List<String> collectList2 = names.parallelStream().map((s) -> {
            System.out.println(s);
            return s.toUpperCase();
        }).collect(Collectors.toList());
        collectList2.forEach(System.out::println);
    }

    public static void stream2() {
        List<OnlineClass> springClasses = new ArrayList<>();

        springClasses.add(new OnlineClass(1,"Spring boot", true));
        springClasses.add(new OnlineClass(2,"Spring data jpa", true));
        springClasses.add(new OnlineClass(3,"Spring mvc", false));
        springClasses.add(new OnlineClass(4,"Spring core", false));
        springClasses.add(new OnlineClass(5,"rest api development", false));

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6,"The Java, Test", true));
        javaClasses.add(new OnlineClass(7,"The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8,"The Java, 8 to 11", false));

        List<List<OnlineClass>> yongwonEvents = new ArrayList<>();
        yongwonEvents.add(springClasses);
        yongwonEvents.add(javaClasses);

        System.out.println("spring으로 시작하는 수업");
        //TODO
        springClasses.stream()
                .filter(x -> x.getTitle().startsWith("spring"))
                .forEach(x -> System.out.println(x.getId()));

        System.out.println("close 되지 않는 수업");
        //TODO
        springClasses.stream()
                        .filter(x -> !x.isClosed())
                        .forEach(x -> System.out.println(x.getId()));

        springClasses.stream()
                .filter(Predicate.not(OnlineClass::isClosed)) //method reference
                .forEach(x -> System.out.println(x.getId()));

        System.out.println("수업 이름만 모아서 스트림 만들기");
        //TODO
        springClasses.stream()
                .map(OnlineClass::getTitle)
                .forEach(s -> System.out.println(s));

        //flatten 시킬 것 => 두 리스트를 다 뽑아내는거
        System.out.println("두 수업 목록에 들어 있는 모든 수업 아이디 출력");
        //TODO
        yongwonEvents.stream()
                        .flatMap(Collection::stream)
                        .forEach(x -> System.out.println(x.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        //TODO
        Stream.iterate(10, i -> i + 1)
                        .skip(10) //처음 10개 skip
                        .limit(10) // 10개만
                        .forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        //TODO
        boolean test = javaClasses.stream().anyMatch(x -> x.getTitle().contains("test"));
        System.out.println(test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 제목만 모아서 List로 만들기기");
       //TODO
        springClasses.stream().filter(x -> x.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());
        springClasses.forEach(System.out::println);

        springClasses.stream()
                .map(OnlineClass::getTitle)
                .filter(x -> x.contains("spring")) //순서가 바뀌면 getter method 후 string으로 반환되기 때문에
                .collect(Collectors.toList());
        springClasses.forEach(System.out::println);


    }
}
