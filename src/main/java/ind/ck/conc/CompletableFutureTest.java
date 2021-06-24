package ind.ck.conc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Author caikun
 * 特点	thenApply	thenAccept	thenRun
 * 入参	有	有	无
 * 返回值	有	无	无
 *
 *
 *
 * @Description //TODO $END
 * @Date 下午1:56 21-6-23
 **/
public class CompletableFutureTest {

//    private CompletableFuture<Long> completableFuture = CompletableFuture.completedFuture()
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            AVAILABLE_PROCESSORS,  //核心线程数
            3 * AVAILABLE_PROCESSORS,  //最大线程数
            3, TimeUnit.SECONDS,  //keepAliveTime
            new ArrayBlockingQueue<>(20));  //阻塞队列

    public static void main(String[] args) {

//        List<CompletableFuture<String>> futureList = new ArrayList<>();
//        CompletableFuture.supplyAsync(() -> {
//
//        });
//
//        CompletableFuture.supplyAsync(() -> 1)
////                then Apply结果的传递
//                .thenApply(i -> i + 1).thenApply(i -> i * i)
//                .thenAcceptAsync(System.out::println)
////                .thenRun(() -> {
////                    try {
////                        Thread.sleep(3000L);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
////                    System.out.println("*");
////                    System.out.println("我不关心");
////                })
//                .whenComplete((r, e) -> {
//                    System.out.println("=====");
//                    System.out.println(r);
//                    System.out.println(e);
//                });
        CompletableFuture<Integer> integerCompletableFuture = null;
        for (int i = 10; i > 0; i --) {
            final int a = i;
            integerCompletableFuture = integerCompletableFuture.supplyAsync(() -> {
                try {
                    Thread.sleep(1000L);
                    System.out.println(Thread.currentThread().getName() + "," + a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return a;
            });
        }
        CompletableFuture<Integer> integerCompletableFuture1 = integerCompletableFuture.thenApply(m -> {
            System.out.println("sas:" + (m * 17));
            return m * 17;
        });
        for (int i = 10; i > 0; i --) {
            final int a = i;
            integerCompletableFuture1 = integerCompletableFuture1.supplyAsync(() -> {
                try {
                    Thread.sleep(1000L);
                    System.out.println(Thread.currentThread().getName() + "," + a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return a;
            });
        }

        integerCompletableFuture1.whenComplete((a, b) -> {
            System.out.println("happy------------------------");
            System.out.println(a);
            System.out.println(b);
        });
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
