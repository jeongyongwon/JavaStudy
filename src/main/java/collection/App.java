package collection;

import java.util.*;

public class App {
    public static void main(String[] args) {
        //습관적으로 앞에는 인터페이스 타입을 사용하고 뒤에는 class type을 사용하는 것이 좋다
        //결국 이것도 DI 관점에서 싶게 결합도를 낮추기 위함이다.
        Collection<String> collection = new ArrayList<>();
        collection.add("a");
        collection.add("b");
        collection.add("c");
        collection.add("d");

        //Collection 객체에서 분기를 돌기 위에서는 의존하고 있는
        //인터페이스는 Iterator를 활용한다.
        Iterator<String> iter = collection.iterator();

        while (iter.hasNext()) {
            String str = iter.next();
            System.out.println("str = " + str);
        }
    }
}
