package ind.ck.concurrent;

import java.util.concurrent.*;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午4:18 21-6-2
 **/
public class ThreadPoolTest {


    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 3, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(3));
        System.out.println(Thread.currentThread().getName() + ":o:" + threadPoolExecutor.getActiveCount());
        for (int i = 0; i < 3; i ++) {
            threadPoolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ":m1:" + threadPoolExecutor.getActiveCount());
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(Thread.currentThread().getName() + ":o2:" + threadPoolExecutor.getActiveCount());
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":o3:" + threadPoolExecutor.getActiveCount());

        for (int i = 0; i < 3; i ++) {
            threadPoolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + ":m2:" + threadPoolExecutor.getActiveCount());
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ":o4:" + threadPoolExecutor.getActiveCount());
//        Future<?> future1 = threadPoolExecutor.submit(() -> {
//            try {
//                Thread.sleep(10000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        try {
//            Object o = future1.get();
//            System.out.println(o);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }
}
