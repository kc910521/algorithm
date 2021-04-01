package ind.ck.juc;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 上午10:05 21-2-26
 **/
public class JucThreadTest {


    private final ReentrantLock lock = new ReentrantLock();

    Condition notfull = lock.newCondition();

    Condition notEmpty = lock.newCondition();


    public void a() {


    }

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            threadLocal.set(911L);
            System.out.println(1);
        });
        t1.start();
        t1.join();
        Thread t2 = new Thread(() -> {
            threadLocal.set(119L);
            System.out.println(2);

        });

        t2.start();

        t2.join();
//        System.out.println((long) threadLocal.get());

    }
}
