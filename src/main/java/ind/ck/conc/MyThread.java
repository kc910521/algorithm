package ind.ck.conc;

import java.util.concurrent.TimeUnit;

/**
 * Created by KCSTATION on 2020/3/22.
 */
public class MyThread extends Thread {

    private MyLock myLock = null;

    public MyThread(MyLock myLock) {
        this.myLock = myLock;
    }

    public void run() {

        try {
            myLock.lock();
            System.out.println("BIZ===== " + Thread.currentThread().getName());
            TimeUnit.MILLISECONDS.sleep(3000L);
            System.out.println("BIZ out ====" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.myLock.unlock();
        }
    }
}
