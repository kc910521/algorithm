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

    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        List<MyThread> mts = new LinkedList<MyThread>();
        reentrantLock.lock();
        mts.add(new MyThread(myLock));
        mts.add(new MyThread(myLock));
        mts.add(new MyThread(myLock));
        mts.add(new MyThread(myLock));
        for (MyThread mm : mts) {
            mm.start();
        }
        //wrong
        reentrantLock.unlock();
    }

}
