package concurrent.completable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureFun2();
    }

    public static void CompletableFutureFun2() throws ExecutionException, InterruptedException {

        CompletableFuture<String> hello = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        });

        //두 작업이 서로 이어서 실행하도록 조합
        CompletableFuture<String> world = hello.thenCompose(App::getWorld);
        System.out.println(world.get());


        //두 작업이 독립적으로 실행하도록
        CompletableFuture<String> world2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("world2" + Thread.currentThread().getName());
            return "world2";
        });
        CompletableFuture<String> future2 = hello.thenCombine(world2,(h,w) -> h + "__" + w);
        System.out.println(future2.get());

        //2개 이상으로 실행하여 모든 작업 결과에 콜백실행하는데
        //중간에 에러가 날 수도 있고 다양한 상황이 나옴
        List<CompletableFuture> futuresTemp = Arrays.asList(hello, world);
        CompletableFuture[] futuresArray = futuresTemp.toArray(new CompletableFuture[futuresTemp.size()]);

        CompletableFuture<List<Object>> results = CompletableFuture.allOf(futuresArray)
                .thenApply(v -> futuresTemp.stream()
                        .map(CompletableFuture::join) //모두 기다렸다
                        .collect(Collectors.toList()));

        results.get().forEach(System.out::println);

        //둘 중 하나라도 먼저 끝나거 호출
        CompletableFuture<Void> future3 = CompletableFuture.anyOf(hello, world).thenAccept(System.out::println);
        future3.get();

        // 예외처리를 던질 수도 있다
        boolean throwError = true;
        CompletableFuture<String> hello2 = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        }).exceptionally(ex -> {
            return "Error!";
        });
        System.out.println("exception 1 " + hello2.get());

        //한 번에 예외처리를 할 수도 있다
        CompletableFuture<String> hello3 = CompletableFuture.supplyAsync(() -> {
            if(throwError) {
                throw new IllegalArgumentException();
            }
            System.out.println("Hello" + Thread.currentThread().getName());
            return "Hello";
        }).handle((result, ex) -> {
            if( ex != null) {
                System.out.println(ex);
                return "ERROR";
            }
            return result;
        });
        System.out.println("exception 3 " + hello3.get());
    }
    public static CompletableFuture<String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("World = " +  Thread.currentThread().getName());
            return message + "World";
        });
    }

    public static void CompletableFutureFun1() throws ExecutionException, InterruptedException {
//        CompletableFuture<String> future = new CompletableFuture<>();
//        future.complete("yongwon");
//
//        System.out.println(future.get());

        // return type이 없는 경우
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("Hello Rldn  " + Thread.currentThread().getName());
        });
        future.get();

        // return type이 있는 경우
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello  ggg" + Thread.currentThread().getName());
            return "Hello kining";
        }).thenApply((s) -> { //비동기적으로 get 전에 call이 가능하다
//              System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println("future1 .get  ===>  " + future1.get());
        // return type이 없을 비동기
        CompletableFuture<Void> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello 1 " + Thread.currentThread().getName());
            return "Hello good";
        }).thenAccept((s) -> {
            System.out.println(Thread.currentThread().getName());
            System.out.println(s.toUpperCase());
        });

        // 생각해보면 threadPool을 만들지 않고 어떻게 비동기 처리가 되남
        /*
         * ForkJoinPool 때문임
         * 작업을 dequeue를 씀  ==> 자기 thread가 할일이 없으면 처리하는 방식
         * */

        //직접 ThreadPool 부여할 수도 있음
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CompletableFuture<Void> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello 2 " + Thread.currentThread().getName());
            return "Hello ggigi";
        }, executorService).thenAccept((s) -> {
            System.out.println("after 1  " + Thread.currentThread().getName());
            System.out.println("after 1  " +s.toUpperCase());
        });

        executorService.shutdown();

    }
}
