package ind.ck.concurrent.sync;

import java.util.UUID;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午3:33 21-2-5
 **/
public class Producer implements Runnable {

    private String name;

    public Producer(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (Platform.consumeGo) {
                if (Platform.line.size() >= 0) {
                    Platform.consumeGo.notify();
                }
            }
            synchronized (Platform.produceGo) {
                if (Platform.line.size() < 2) {
                    pm();
                } else {
                    System.out.println("不用" + name + "生产了，生产者休息吧！");
                    try {
                        Platform.produceGo.wait();
                        System.out.println("生产者被唤醒了！");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }


        }



    }

    private void pm() {
        for (int i = 0; i < 1; i ++) {
            String s = UUID.randomUUID().toString().replace("-", "");
            System.out.println(name + "生产了：" + s);
            Platform.line.add(s);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
