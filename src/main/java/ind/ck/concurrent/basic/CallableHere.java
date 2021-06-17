package ind.ck.concurrent.basic;

import ind.ck.concurrent.DummyCon;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午2:26 21-6-9
 **/
public class CallableHere {

    static class MyCall implements Callable<DummyCon> {

        @Override
        public DummyCon call() throws Exception {
            Thread.sleep(2100);
            return new DummyCon(UUID.randomUUID().toString());
        }
    }

    public static void main(String[] args) {
//        MyCall myCall = new CallableHere.MyCall();
//        FutureTask<DummyCon> futureTask = new FutureTask(myCall);
//        System.out.println("go");
//        new Thread(futureTask).start();
////        new
////        System.out.println("go");
//        try {
//            DummyCon dummyCon = futureTask.get(5000, TimeUnit.MILLISECONDS);
//            System.out.println(dummyCon);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
        ArrayBlockingQueue blockingDeque = new ArrayBlockingQueue<String>(1);
        final Thread thread = new Thread(() -> {
            try {
                blockingDeque.take();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        final Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(5000L);
                thread.interrupt();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        thread2.start();



    }



}
