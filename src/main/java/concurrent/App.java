package concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        basicThread();
    }



    public static void basicThread() throws InterruptedException {
//        MyThread myThread = new MyThread();
//        myThread.start();

        //sleep를 하면 당연히 뒤에 있는 쓰레드로 넘어가게됨
//        Thread thread = new Thread(() -> {
//            try {
//                Thread.sleep(1000L);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Thread : " + Thread.currentThread().getName());
//        });
//        thread.start();

        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("Thread  :  " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    System.out.println("exit!");
                    return; //return를 하지 않으면 에러 발생하고 계속 실행됨
                }
            }
        });
        thread.start();


        System.out.println("hello : " + Thread.currentThread().getName());
//        Thread.sleep(3000L);
//        Thread.interrupted();
        thread.join(); //기다리는 쓰레드가 있으면 끝나고 다음 로직이 실행됨 => 사실상 쓰레드를 코딩으로 관리하기 힘듬
        System.out.println("finished : " + Thread.currentThread().getName());
    }

}
