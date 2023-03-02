package concurrent.callable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class App {
    public static void main(String[] args) {
//        CallableFun();
        CallableFun2();
    }

    public static void CallableFun()  {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        try {
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
        } catch (Exception eex) {
            System.out.println(eex);
        }

    }

    public static void CallableFun2() {
        try {
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
        } catch (Exception e) {
            System.out.println("e ===>  " + e);
        }

    }
}
