package ind.ck.conc;

import sun.misc.Unsafe;
import sun.misc.VM;
import sun.reflect.Reflection;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by KCSTATION on 2020/3/22.
 */
public class MyLock {

    /**
     * 0 unlock; 1 locked;
     **/
    private volatile AtomicInteger state = new AtomicInteger(0);

    private volatile Queue<Thread> waitQueue = new ConcurrentLinkedQueue<Thread>();

    private volatile AtomicReference<Thread> currentThread = new AtomicReference<>();

    private void toWait() {
        waitQueue.offer(Thread.currentThread());
        // wait
        LockSupport.park();
    }

    public void lock() throws InterruptedException {
//        System.out.println("lock1" + ":" + Thread.currentThread().getName());
        while (true) {
            if (state.compareAndSet(0, 1)) {
                currentThread.set(Thread.currentThread());
                // lock success
                return;
            } else {
                // 未能获取锁
                toWait();
                System.out.println(Thread.currentThread().getName() + " wake ");
                // 重新争抢锁
            }
        }
    }

    public void unlock() {
//        System.out.println("unlock1" + ":" + Thread.currentThread().getName());
        if (!state.compareAndSet(1, 0)) {
            throw new RuntimeException("unlock exception"  + ":" + Thread.currentThread().getName());
        } else {
            // unlock success
            Thread element = waitQueue.poll();
            System.out.println("unlockDD" + element);
            if (element != null) {
                currentThread.set(null);
                LockSupport.unpark(element);
            }
        }

    }

    public Queue<Thread> getQ() {
        return waitQueue;
    }
}
