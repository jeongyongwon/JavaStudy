package exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Exception1 {
    public static void main(String[] args) {
        ExceptionObject1 exobj = new ExceptionObject1();
        int value = exobj.divide(10, 0);
        System.out.println("value = " + value);
    }
}

class ExceptionObject1 {
    public int divide(int i, int k) throws ArithmeticException{
        int value = 0;
        try {
            value = i / k;
        } catch (ArithmeticException ex) {
            System.out.println("문자로 나눌 수 없음");
            System.out.println(ex.toString());
        }
        return value;
    }
}

class Exception4 {
    void fileUp() {
        try {
            FileInputStream fis = new FileInputStream("Exception.java");
        } catch (FileNotFoundException ex) {
            System.out.println("파일을 찾을 수가 없어요");
        }
    }
}