package javaBasic.maincode;

import javaBasic.data.BookVO;

public class TPC11_12_ClassConstruct {
    
        //생성자가 없을 때 하지마 좋지않다
//        BookVO b=new BookVO();
//        b.title="파이썬";
//        b.price=16000;
//        b.company="에이콘";
//        b.page=700;

        
        // 생성자 -> 생성+초기화-> 중복정의
        BookVO b1=new BookVO();
        BookVO b2=new BookVO();

        BookVO b3=new BookVO("Python", 18000, "대림", 920);

}
