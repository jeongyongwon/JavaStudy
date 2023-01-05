package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
//        basicThread();
        ExecuterFun();
    }

    public static void ExecuterFun() {

        //명시적으로 shutdown을 하지 않으면 다 작업을 기다림
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.submit(() -> {
//            System.out.println("Threand  :  " + Thread.currentThread().getName());
//        });
//        executorService.shutdown(); // 현재 작업한 작업은 하고 끝냄 => shutdownNow()는 그냥 바로 끝냄

        //Thread를 2개 주고 5개 실행
        //Blocking Queue에 넣어두기 때문에 ㅅ애각보다 빨리 처리됨됨
        ExecutorService executorServiceTP = Executors.newFixedThreadPool(2);
        executorServiceTP.submit(getRunnable("Hello"));
        executorServiceTP.submit(getRunnable("Keesun"));
        executorServiceTP.submit(getRunnable("The"));
        executorServiceTP.submit(getRunnable("Java"));
        executorServiceTP.submit(getRunnable("Thread"));
        executorServiceTP.shutdown();

        //schedule을 조정해서 일정 시간 뒤에 실행시킬 수도 있다
        //Fork-Join Framwork가 따로 있음
        ScheduledExecutorService executorServiceSC = Executors.newSingleThreadScheduledExecutor();
        executorServiceSC.schedule(getRunnable("hello"), 3, TimeUnit.SECONDS);
        executorServiceSC.scheduleAtFixedRate(getRunnable("hello"), 1,2, TimeUnit.SECONDS); //1초 있다 2초간격으로 실행
        executorServiceSC.shutdown();


    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
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

    static class MyThread extends Thread {

        @Override
        public void run() {
            //해보면 쓰레드 순서 보장못함
            System.out.println("Thread :  " + Thread.currentThread().getName());
        }

    }
}
