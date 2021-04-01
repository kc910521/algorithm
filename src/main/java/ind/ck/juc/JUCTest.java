package ind.ck.juc;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 上午11:06 21-2-24
 **/
public class JUCTest {

    static class People {
        String name = "A";
        int i = 10000;

        public People(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }



    public static void clq() {
        ConcurrentLinkedQueue clq = new ConcurrentLinkedQueue();
        clq.offer("A");
        clq.offer("B");
        System.out.println(clq);
        System.out.println(clq.poll());
        System.out.println(clq.poll());
    }

    public static void la() {
        LongAccumulator la = new LongAccumulator((x, y) -> {
            return x + y;
        }, 0);
        la.accumulate(1);
        long l = la.get();
        System.out.println(l);

    }

    public static void ld() {
        LongAdder longAdder = new LongAdder();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                longAdder.add(1L);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                longAdder.add(1L);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long sum = longAdder.sum();
        System.out.println(sum);

    }

    public static void abq() throws InterruptedException {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(5);
        new Thread(() -> {
            try {
                Thread.sleep(1000L);
                // 阻塞
                bq.put("a");
                // offer() 可以在规定时间内重试，超过规定时间返回false
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }).start();
        // 此处是可以阻塞住的
        System.out.println(bq.take());
        // poll() 在规定时间内重试，超过规定时间返回null

    }


    public static void abq2() throws InterruptedException {
        BlockingQueue<String> bq = new ArrayBlockingQueue<>(2);
        bq.offer("a");
        bq.offer("a1");

        bq.offer("a2", 1, TimeUnit.DAYS);

        // 此处是可以阻塞住的
        System.out.println(bq);
        // poll() 在规定时间内重试，超过规定时间返回null

    }

    public static void cdl() throws InterruptedException {
//        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
//        copyOnWriteArrayList.get(0);
        CountDownLatch cdl = new CountDownLatch(1);
        for (int i =0; i < 7;i ++) {
            new Thread(() -> {
                try {
                    cdl.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":GO");
            }).start();
        }
        System.out.println("do Work 1");
        Thread.sleep(500);
        System.out.println("do Work 2");
        Thread.sleep(500);
        // 类似发令枪
        cdl.countDown();
    }

    public static void cb() throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("do Work 1");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("do Work 2");
            }
        };
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, runnable);

        for (int i =0; i < 7;i ++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":GO");
                cyclicBarrier.reset();
            }).start();
        }
        Thread.sleep(3000L);
        for (int i =0; i < 7;i ++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":GO");
            }).start();
        }
//        new Thread(() -> {
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            cyclicBarrier.reset();
//        }).start();
    }

    public static void  sem() {
        // 同时只处理两个线程
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " here");
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + " quit");

                }
            }).start();
        }
    }



    public static void exchanger() {
        Exchanger<String> exchanger = new Exchanger();
        // A 得到 B，B得到A
        new Thread(() -> {
            try {
                String a = "IAM A";
                Thread.sleep(200L);
                String exchange = exchanger.exchange(a);
                System.out.println(a + " i get[" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                String a = "IAM B";
                Thread.sleep(2000L);
                String exchange = exchanger.exchange(a);
                System.out.println(a + " i get[" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void phase1() {
        Phaser phaser = new Phaser() {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("adv!" + phase + "----" + registeredParties);
                return super.onAdvance(phase, registeredParties);
            }
        };
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                // 注册phase参与者
                int register = phaser.register();
                System.out.println("register:" + Thread.currentThread().getName() + "," + register);
                for (int j = 0;!phaser.isTerminated() && j < 3; j ++) {
                    // arrive数量+1,本方法阻塞，不响应中断;
                    int i1 = phaser.arriveAndAwaitAdvance();
                    System.out.println("do work:" + Thread.currentThread().getName() + ",phase：" + i1);
                }
            }).start();
        }
//        try {
//            Thread.sleep(5000L);
////            phaser.arriveAndDeregister();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }

    public static void stampedAtomic() {
        AtomicStampedReference asr = new AtomicStampedReference("A", 1);
        asr.compareAndSet("A", "B", 1, 2);

    }

    public static void main(String[] args) throws InterruptedException {
        phase1();
//        exchanger();
//        sem();
//        cb();
//        cdl();
//        abq2();
//        clq();
//        ld();
//        // 初始值a，版本号1
//        AtomicStampedReference asr = new AtomicStampedReference(new People("B"), 1);
//
//
//
//
//        Thread t1 = new Thread(() -> {
//            try {
//                Thread.sleep(1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            // 得到版本号,若要防止ABA可以将版本号附加到数据内
//            int stamp = asr.getStamp();
//            People reference = (People) asr.getReference();
//            reference.setName("A");
//            boolean b = asr.compareAndSet(reference, reference, stamp, stamp + 1);
//            System.out.println("b->a:" + b);
//        });
//
//        Thread t2 = new Thread(() -> {
//            try {
//                Thread.sleep(3000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            // 得到版本号
//            int stamp = asr.getStamp();
//            boolean b = asr.compareAndSet("a", 'b', stamp, stamp + 1);
//            System.out.println("a->b:" + b);
//        });
//        t1.start();
//        t2.start();
//        t1.join();
//        t2.join();
//
//        System.out.println("final:" + asr.getReference() + "," + asr.getStamp());



    }


}
