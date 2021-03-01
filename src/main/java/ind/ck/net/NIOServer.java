package ind.ck.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class NIOServer {


    public static void main(String[] args) throws IOException, InterruptedException {

//        nioWithoutSelector();
        nioWithSelector();

    }
    /*发送数据缓冲区*/

    private static void nioWithSelector() throws IOException, InterruptedException {
        ByteBuffer receivebuffer = ByteBuffer.allocate(1024);

//        List<SocketChannel> socketChannels = new CopyOnWriteArrayList<>();
        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 1
        while (true) {
//            read(socketChannels);
            Iterator<SelectionKey> iterator  = selector.keys().iterator();
            while (iterator.hasNext()) {
                SelectionKey sc = iterator.next();
                if (sc.isAcceptable()) {
                    // 妥妥拿到服务端自己
                    ServerSocketChannel ssc = (ServerSocketChannel) sc.channel();
                    // 还是得accept
                    SocketChannel client = ssc.accept();
                    client.configureBlocking(false);
                    System.out.println("accept new client :" + client.getRemoteAddress());
                    client.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(256));

                } else if (sc.isReadable()) {
                    SocketChannel client = (SocketChannel) sc.channel();
                    receivebuffer.clear();
                    int ct = client.read(receivebuffer);
                    if (ct > 0 ) {
                        receivebuffer.flip();
                        System.out.println(new String(receivebuffer.array()));
                    }
                    // 连接部分
                    //            SocketChannel accept = serverSocketChannel.accept();
                    //            if (accept == null) {
                    //                Thread.sleep(500);
                    //            } else {
                    //                accept.configureBlocking(false);
                    //                socketChannels.add(accept);
                    //                System.out.println("客户端链接：" + accept.getRemoteAddress());
                    //            }
                }
            }




        }
    }

    private static void nioWithoutSelector() throws IOException, InterruptedException {
        List<SocketChannel> socketChannels = new CopyOnWriteArrayList<>();


        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);
        // 1
        while (true) {
            read(socketChannels);
            // 连接部分
            SocketChannel accept = serverSocketChannel.accept();
            if (accept == null) {
                Thread.sleep(500);
            } else {
                accept.configureBlocking(false);
                socketChannels.add(accept);
                System.out.println("客户端链接：" + accept.getRemoteAddress());
            }


        }
    }

    private static void read(List<SocketChannel> socketChannels) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        for (SocketChannel socketChannel : socketChannels) {
            if (socketChannel.isConnected()) {
                int read = socketChannel.read(byteBuffer);
                if (read > 0) {
                    byteBuffer.flip();
                    System.out.println(new String(byteBuffer.array()));
                    byteBuffer.clear();
                }
            } else {
                System.out.println("removed");
                socketChannels.remove(socketChannel);
            }


        }
    }


}
