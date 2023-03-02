package functionalpackage.functionalinterface;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Foo {

    public static void main(String[] args) {

        //java 8부터 나오는 람다로 인터페이스 오버라이드하는 거 동일함
        RunSomething runSomething = () -> {
            System.out.println("Hello");
        };
        runSomething.doIt();
        RunSomething runSomething2 = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("Hello");
            }
        };

        //이런식으로 파라미터를 받고 처리도 가능하다 ==> pure 함수가 되도록 노력하자 (외부값을 참조하지 않도록)
//        RunSomething runSomething3 = (number) -> {
//            return number + 10;
//        };

        /*이렇게 자체적으로 Function 인터페이스를 상속받는걸 람다로 줄일 수 있고, apply는 인터페이스에서 함수로 실행하는 것이다
        함수끼리 조합도 가능하다
         <입력, 출력>
         */
        Function<Integer, Integer> plus10 = (i) -> {
           return i + 10;
        };
        System.out.println(plus10.apply(10));

        Function<Integer, Integer> multiply2 = (i) -> {
           return i * 2;
        };

        Function<Integer, Integer> mulAndPlus = plus10.compose(multiply2); // 사칙연산 순위 따름
        Function<Integer, Integer> mulAndPlus2 = plus10.andThen(multiply2); // 함수 순서대로 실행
        System.out.println(mulAndPlus.apply(100));

        //true / false 도 정할 수 있음
        // or , and 도 가능하고 return 타입이 동일해야 체이닝 가능
        Predicate<String> startsWithYongwon = (s) -> s.startsWith("yongwon");
        Predicate<String> isEven = (i) -> i.startsWith("yddongwon");
        System.out.println(startsWithYongwon.or(isEven));

        //꼭 Function은 wrapper 타입이 2개가 아님
        // UnaryOperator 은 Functional 인터페이스를 상속받음
        UnaryOperator<Integer> plus10_2 = (i) -> {
            return i + 10;
        };
        UnaryOperator<Integer> multiply2_2 = (i) -> {
           return i * 2;
        };
        System.out.println(plus10_2.andThen(multiply2_2).apply(100));

        //인자를 여러개 받을 때
        //이미 변수 선언부에 argument type을 선언하기 때문에 굳이 a,b 타입을 선언할 필요는 없음
        BinaryOperator<Integer> sum2 = (a, b) -> {
            return a + b;
        };

        Integer a = sum2.apply(10,15);
        System.out.println("a = " + a);


    }

}
