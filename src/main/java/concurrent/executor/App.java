package concurrent.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
        ExecuterFun();
    }

    public static void ExecuterFun() {


        //Thread를 2개 주고 5개 실행
        //Blocking Queue에 넣어두기 때문에 생각보다 빨리 처리됨
        //명시적으로 shutdown을 하지 않으면 다 작업을 기다림 => 그러니까 shutdown을 잘해야겠다
        ExecutorService executorServiceTP = Executors.newFixedThreadPool(2);
        executorServiceTP.submit(getRunnable("Hello"));
        executorServiceTP.submit(getRunnable("Keesun"));
        executorServiceTP.submit(getRunnable("The"));
        executorServiceTP.submit(getRunnable("Java"));
        executorServiceTP.submit(getRunnable("Thread"));
        executorServiceTP.shutdown(); // 현재 작업한 작업은 하고 끝냄 => shutdownNow()는 그냥 바로 끝냄

        //schedule을 조정해서 일정 시간 뒤에 실행시킬 수도 있다
        ScheduledExecutorService executorServiceSC = Executors.newSingleThreadScheduledExecutor();
        executorServiceSC.schedule(getRunnable("hello"), 3, TimeUnit.SECONDS);
        executorServiceSC.scheduleAtFixedRate(getRunnable("hello"), 1,2, TimeUnit.SECONDS); //1초 있다 2초간격으로 실행
        executorServiceSC.shutdown();


    }

    private static Runnable getRunnable(String message) {
        return () -> System.out.println(message + Thread.currentThread().getName());
    }

}
