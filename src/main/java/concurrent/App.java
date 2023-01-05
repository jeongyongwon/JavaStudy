package concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class App {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        basicThread();
//        ExecuterFun();
//        CallableFun();
        CallableFun2();
    }

    public static void CallableFun2() throws ExecutionException, InterruptedException {
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Callable<String> java = () -> {
            Thread.sleep(3000L);
            return "Java";
        };

        Callable<String> yongwon = () -> {
            Thread.sleep(1000L);
            return "Yongwon";
        };

        //동시에 실행할 수도 있다;
        //다 기다림 젤 늦은 시간을 기다리기 때문에 3초간 걸리는 듯
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(hello,java, yongwon));
        for (Future<String> future : futures) {
            System.out.println(future.get());
        }

        //결국 제일 먼저 끝나는 걸 가져온다
        //근데 single thread로 걸어놓으면 thread가 한개뿐이라 제일 위에 것이 실행되고
        //threadPool 4개 정도 생성해주면 1초 method가 끝날 것이다
        String s = executorService.invokeAny(Arrays.asList(hello,java,yongwon));
        System.out.println(s);

        executorService.shutdown();

    }

    public static void CallableFun() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        //return 값을 받을 수 있음
        Callable<String> hello = () -> {
            Thread.sleep(2000L);
            return "Hello";
        };

        Future<String> helloFuture = executorService.submit(hello);
        System.out.println(helloFuture.isDone()); //진행이면 false
        System.out.println("Started");

        // interrupt and stop thread
        // false는 기다림
        helloFuture.cancel(true);

        //blocking call get을 얻을 때까지 기다림
        //취소를 했으면 못 가져옴
        helloFuture.get();

        System.out.println(helloFuture.isDone()); // 끝났으면 true
        System.out.println("End !!");

        executorService.shutdown();

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
