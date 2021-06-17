package ind.ck.conc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by KCSTATION on 2020/3/22.
 */
public class MyThread extends Thread {

    public static int code = 0;

    private MyLock lock = null;

    public MyThread(String name, MyLock lock) {
        super(name);
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            while (code < 20) {
                Thread.sleep(300L);
                code ++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
