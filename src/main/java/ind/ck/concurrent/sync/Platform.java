package ind.ck.concurrent.sync;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author caikun
 * @Description //TODO $END
 * @Date 下午3:34 21-2-5
 **/
public class Platform {

    public final static List<String> line = new CopyOnWriteArrayList<>();

    public static volatile byte[] produceGo = new byte[0];
    public static volatile byte[] consumeGo = new byte[0];


    private static Thread producer1 = new Thread(new Producer("p1"));
    private static Thread producer2 = new Thread(new Producer("p2"));
    private static Thread consumer = new Thread(new Consumer("c1"));


    public static void main(String[] args) {

        consumer.start();
        producer1.start();
        producer2.start();

    }


}
