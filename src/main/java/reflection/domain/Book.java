package reflection.domain;

import reflection.MyAnnotation;

import javax.swing.plaf.PanelUI;

@MyAnnotation(value = "yongwon", name = "yo",number = 10)
public class Book {

    private static String B = "Book";

    private static final String C = "Book";
    private String a = "a";

    private String d = "d";

    protected String e = "e";

    public Book() {
    }

    public Book(String a, String b, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f() {
        System.out.println("F");
    }

    private void g() {
        System.out.println("g");
    }

    public int h() {
        return 100;
    }

}
