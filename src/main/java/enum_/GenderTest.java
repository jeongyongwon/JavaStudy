package enum_;

import java.util.Arrays;
import java.util.EnumMap;

public class GenderTest {
    public static void main(String[] args) {
        Gender gender = Gender.MALE;
        System.out.println("gender = " + gender);
        gender.print();

        EnumMap enumMap = new EnumMap(Day.class); //Enum을 key로 가지도록 하는 클래스
        enumMap.put(Day.MON,"월요일");
        enumMap.put(Day.TUE,"화요일");
        enumMap.put(Day.WEN,"수요일");

        System.out.println("enumMap  ===>    " + enumMap.get(Day.MON));

    }
}

