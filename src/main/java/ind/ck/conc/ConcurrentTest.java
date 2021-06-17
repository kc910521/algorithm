package ind.ck.conc;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by KCSTATION on 2020/3/22.
 */
public class ConcurrentTest {


    private static MyLock myLock = new MyLock();

    public static void main(String[] args) throws InterruptedException {
        List<MyThread> mts = new LinkedList<MyThread>();
        mts.add(new MyThread("a1", myLock));
        mts.add(new MyThread("a2", myLock));
        mts.add(new MyThread("a3", myLock));
        mts.add(new MyThread("a4", myLock));
        mts.add(new MyThread("a5", myLock));
        mts.add(new MyThread("a6", myLock));

        for (MyThread mm : mts) {
            mm.start();
        }
        Thread.sleep(10000L);
        System.out.println("========================" + MyThread.code);
        //wrong
    }

}
