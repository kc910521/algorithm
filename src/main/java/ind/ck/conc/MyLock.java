package ind.ck.conc;

import sun.misc.Unsafe;
import sun.misc.VM;
import sun.reflect.Reflection;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by KCSTATION on 2020/3/22.
 */
public class MyLock {

    /**
     * 0 unlock; 1 locked;
     **/
    private volatile AtomicInteger state = new AtomicInteger(0);

    private volatile Queue<Thread> q = new ConcurrentLinkedQueue<Thread>();

    public Thread lock() throws InterruptedException {
        System.out.println("lock1" + ":" + Thread.currentThread().getName());
        if (!state.compareAndSet(0, 1)) {
            // failed in lock
            TimeUnit.MILLISECONDS.sleep(10L);
            System.out.println("lock2" + ":" + Thread.currentThread().getName());
            q.offer(Thread.currentThread());
            // wait
            LockSupport.park();
            while (!state.compareAndSet(0, 1)) {
                // 重新争抢锁
                continue;
            }
            System.out.println("lock3" + ":" + Thread.currentThread().getName());
            return q.poll();
        }
        System.out.println("lock4" + ":" + Thread.currentThread().getName());
        return Thread.currentThread();
    }

    public void unlock() {
        System.out.println("unlock1" + ":" + Thread.currentThread().getName());
        if (!state.compareAndSet(1, 0)) {
            System.err.println("unlock unpark fail" + q.size());
//            throw new RuntimeException("unlock exception"  + ":" + Thread.currentThread().getName());
        } else {
            //不出队
            Thread element = q.peek();
            System.out.println("unlockDD" + element);
            if (element != null) {
                LockSupport.unpark(element);
            }
        }
    }

    public Queue<Thread> getQ() {
        return q;
    }
}
