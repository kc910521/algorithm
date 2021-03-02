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
        System.out.println("------------");
        // 1
        while (true) {
//            read(socketChannels);
            int select = selector.select();
            System.out.println("select:" + select);
            Set<SelectionKey> selectionKeys  = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey sc = iterator.next();
                // 获取epoll rdlist复制到用户态，遍历，同时删除当前rdlist事件
                iterator.remove();
                if (sc.isAcceptable()) {
                    // 妥妥拿到服务端自己
                    ServerSocketChannel ssc = (ServerSocketChannel) sc.channel();
                    // 还是得accept
                    System.out.println("ssc+" +ssc);
                    SocketChannel client = ssc.accept();
//                    if (client == null) {
//                        continue;
//                    }
                    client.configureBlocking(false);
                    System.out.println("accept new client :" + client.getRemoteAddress());
                    client.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(256));

                } else if (sc.isReadable()) {
                    SocketChannel client = (SocketChannel) sc.channel();
                    receivebuffer.clear();
                    int ct = client.read(receivebuffer);
                    if (ct > 0 ) {
                        System.out.println("recv:" + new String(receivebuffer.array()));
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
