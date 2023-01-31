package javaBasic.data;

public class BookDTO {
    public static String title;
    public static int price;
    public static String company;
    public static int page;
    // 디폴트 생성자 메서드(생략)
    public BookDTO() {
        // 객체를 생성하는 작업한다.(기계어코드)
    }
    public BookDTO(String title, int price, String company, int page) {
        this.title = title;
        this.price = price;
        this.company = company;
        this.page = page;
    }

}
