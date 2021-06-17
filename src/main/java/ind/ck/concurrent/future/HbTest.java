package ind.ck.concurrent.future;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午7:26 21-6-1
 **/
public class HbTest {

    private static volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            // 永远出不来
            while (flag) {
            }
            System.out.println("我...出来了 " + flag);
        }).start();

        Thread.sleep(5000);
        flag = false;
    }
}
