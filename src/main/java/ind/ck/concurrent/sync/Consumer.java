package ind.ck.concurrent.sync;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午3:32 21-2-5
 **/
public class Consumer implements Runnable {


    private String name;

    public Consumer(String name) {
        this.name = name;
    }
    @Override
    public void run() {

        while (true) {
            synchronized (Platform.produceGo) {
                if (Platform.line.size() <= 2) {
                    Platform.produceGo.notify();
                }
            }

            synchronized (Platform.consumeGo) {
                if (Platform.line.size() == 0) {
                    try {
                        System.out.println("消费光了");
                        Platform.consumeGo.wait();
                        System.out.println("消费者 " + name + " 被唤醒了！");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    String remove = Platform.line.remove(0);
                    System.out.println(name + " 消费一个：" + remove);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }


        }

    }
}
