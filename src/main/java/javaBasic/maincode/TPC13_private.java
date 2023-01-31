package javaBasic.maincode;

import javaBasic.data.BookDTO;

public class TPC13_private {
    public static void main(String[] args) {
        // 책->class(BookDTO)->객체->인스턴스
        String title="스프링";
        int price=25000;
        String company="제이펍";
        int page=890;

        BookDTO dto; // dto(Object: 객체)
        dto=new BookDTO(title, price, company, page); // private로 constructor를 선언하면 생성이 되지 않는다

        bookPrint(dto);

    }
    public static void bookPrint(BookDTO  dto) {
        System.out.print(dto.title+"\t");
        System.out.print(dto.price+"\t");
        System.out.print(dto.company+"\t");
        System.out.print(dto.page+"\t");
    }
}
