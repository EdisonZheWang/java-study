package com.everest.nio.origin;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.Instant;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @Date: 12/19/19
 * @author: <a href="mailto:v-edwang@expedia.com">Edwang</a>
 */
public class TimeServer {

    private static final int PORT = 10086;
    private static final Executor executor = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        ServerSocketChannel servChannel;
        try {
            servChannel = ServerSocketChannel.open();
            servChannel.socket().bind(new InetSocketAddress(InetAddress.getByName("localhost"), PORT));
            servChannel.configureBlocking(false);
            Selector selector = Selector.open();
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey next = it.next();
                    it.remove();
                    executor.execute(new ReactorTask(selector, next));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class ReactorTask implements Runnable {

        private SelectionKey key;

        private Selector selector;

        public ReactorTask(Selector selector, SelectionKey key) {
            this.selector = selector;
            this.key = key;
        }

        @Override
        public void run() {
            try {
                if (key.isValid()) {
                    if (key.isAcceptable()) {
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        SocketChannel sc = ssc.accept();
                        sc.configureBlocking(false);
                        sc.register(selector, SelectionKey.OP_READ);
                    }
                    if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int readBytes = sc.read(readBuffer);
                        if (readBytes > 0) {
                            readBuffer.flip();
                            byte[] bytes = new byte[readBuffer.remaining()];
                            readBuffer.get(bytes);
                            String string = new String(bytes, "UTF-8");
                            System.out.println(string);
                            String time = Instant.now().toString();
                            doWrite(sc, time);
                        } else if (readBytes < 0) {
                            key.cancel();
                            sc.close();
                        } else
                            ;

                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void doWrite(SocketChannel channel, String response) {
            try {
                if (response != null && response.trim().length() > 0) {
                    byte[] bytes = response.getBytes("UTF-8");
                    ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
                    writeBuffer.put(bytes);
                    writeBuffer.flip();

                    channel.write(writeBuffer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
