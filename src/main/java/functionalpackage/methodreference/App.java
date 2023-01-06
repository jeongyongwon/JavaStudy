package functionalinterface.methodreference;

import java.util.Arrays;
import java.util.function.UnaryOperator;

public class App {
    public static void main(String[] args) {
        UnaryOperator<String> hi = Greeting::hi; // :: 가 레퍼런스를 참조하겟다
        System.out.println("args = " + hi.apply("yongwon"));

        //이런식으로도 레퍼런스를 참조해서 정렬한다
        String[] names = {"kk", "whit", "sdsd"};
        Arrays.sort(names, String::compareToIgnoreCase);

    }
}
