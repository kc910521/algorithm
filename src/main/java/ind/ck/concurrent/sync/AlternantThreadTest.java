package ind.ck.concurrent.sync;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @Author caikun
 * @Description 交替打印
 * @Date 下午3:14 21-6-23
 **/
public class AlternantThreadTest {


    private static int m = 100;

    private final static byte[] lock = new byte[0];

    private static volatile int ai = 0;

//    private static volatile Object go2 = false;

    public static void go1() throws InterruptedException {
        while (ai <= 100) {
            synchronized (lock) {
                while (ai % 2 == 0) {
                    lock.wait();
                }
                if (ai > 100) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + ":" + ai);
                ai ++;
                lock.notify();
            }
        }

//
//        Stream.iterate(1, integer -> integer + 1).limit(100).forEachOrdered( idx -> {
//            synchronized (lock) {
//                while (idx % 2 == 0) {
//                    try {
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println(idx);
//                lock.notify();
//            }
//
//        });
    }


    public static void go2() throws InterruptedException {

        while (ai <= 100) {
            synchronized (lock) {
                while (ai % 2 != 0) {
                    lock.wait();
                }
                if (ai > 100) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + ":" + ai);
                ai ++;
                lock.notify();
            }
        }

//
//        Stream.iterate(1, integer -> integer + 1).limit(100).forEachOrdered( idx -> {
//
//            synchronized (lock) {
//                while (idx % 2 != 0) {
//                    try {
//                        lock.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println(idx);
//                lock.notify();
//            }
//        });
    }

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                go1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                go2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
