package reflection;

import reflection.domain.Book;
import reflection.domain.Book2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        reflection2();
    }

    //클래스정보확인
    public static void reflection1() throws ClassNotFoundException {
//        System.out.println("hello");

        Class<Book> bookClass = Book.class; //클래스를 로딩만 해도 인스턴스가 만들어짐

//        Class<? extends Book> aClass = book.getClass();
//        Class<?> aClass1 = Class.forName("me.yongwon.test"); // 이런식으로 클래스도 찾을 수 있다

        //모든 필드 출력
        Book book = new Book();
        Arrays.stream(bookClass.getDeclaredFields()).forEach(f -> {
            try {
                f.setAccessible(true); //원래는 접근이 불가능했던걸 접근 가능하게 한다
                System.out.println("get  ===>  "  + f.get(book));
            }  catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);
        Arrays.stream(bookClass.getAnnotations()).forEach(System.out::println);


    }

    public static void reflection2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Class<?> bookClass = Class.forName("reflection.domain.Book2");
        Constructor<?> constructor = bookClass.getConstructor(String.class);
        Book2 book2 = (Book2) constructor.newInstance("myBook");
        System.out.println(book2);

        Field a = Book2.class.getDeclaredField("A"); //클래스 자체에서 가져와도 있음 static이라
        a.set(null, "AAAAAA");
        System.out.println(a.get(null)); //값이 바뀜

        Field b = Book2.class.getDeclaredField("B"); //
        b.setAccessible(true); // private도 접근 가능하도록
        System.out.println(b.get(book2)); // 인스턴스를 집어넣어서 가져올 수 있으나 private다

        //method도 가져올 수 있따
        Method c = Book2.class.getDeclaredMethod("sum", int.class, int.class);
        int invoke = (int) c.invoke(book2,1,2);
        System.out.println(invoke);
    }

}
