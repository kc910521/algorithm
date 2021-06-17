package ind.ck.conc;

import java.util.concurrent.CountDownLatch;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午4:37 21-6-10
 **/
public class DoubleCheckTest {



    public static void main(String[] args) {


        while (true) {
            int m = 20;
            CountDownLatch countDownLatch = new CountDownLatch(m);

            for (int i = 0; i < m; i++) {
                new Thread(() -> {
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DoubleCheck.getInstance().carrier.toString();
                    countDownLatch.countDown();
                }).start();
            }

            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
//            System.out.print("1-");
            DoubleCheck.getInstance().clear();
        }



    }


}
