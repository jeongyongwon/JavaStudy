package arraysort;

import java.util.Arrays;

public class CompareToCustom {
    public static void main(String[] args) {

        Item[] items = new Item[5];
        items[0] = new Item("java", 5000);
        items[1] = new Item("파이썬", 4000);
        items[2] = new Item("C#", 4500);
        items[3] = new Item("자바스크립트", 6000);
        items[4] = new Item("Dart", 2000);

        // sort(Object[]) - Object는 모든 객체의 조상이니깐, 어땐 객체의 배열이든 올 수 있다.
        Arrays.sort(items, (x1, x2) -> {
            return x1.getName().compareTo(x2.getName());
        });

        for (Item item : items) {
            System.out.println(item.getName() + " __ " + item.getPrice());
        }
    }
}

class Item {

    // Comparable 객체의 배열을 정리할 때, 기준을 정의하고 싶을 때 Comparable Interface를 구현하면 된다
    //파라미터로 들어온 Object와 내 자신을 비교하는 메소드
    //정렬 로직에서 CompareTo Method를 사용하는 것으로 보인다.
    // 결국 특정 기준으로 비교하게 되서 양수, 0, 음수가 나온다
    // 근데 Comparator + lambda 표현식으로 간단하게 정의할 수 있다.
//    @Override
//    public int compareTo(Object o) {
//        Item d = (Item) o;
//        return this.name.compareTo(d.name);
//    }

    private String name;

    private int price;

    public String getName() {
        return name;
    }

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
