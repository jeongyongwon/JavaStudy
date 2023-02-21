package generic;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {

        //이렇게 타입을 자유롭게 지정할 수 있다.
        GenericBox<String> genericBox = new GenericBox<>();
        genericBox.add("kim");
        String str = genericBox.get();
        System.out.println(str);

        GenericBox<Integer> genericBox1 = new GenericBox<>();
        genericBox1.add(new Integer(1));
        Integer intVal = genericBox1.get();
        System.out.println(intVal);

    }
}
