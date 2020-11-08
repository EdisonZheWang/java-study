package com.everest.nio.origin;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Date: 12/19/19
 * @author: <a href="mailto:v-edwang@expedia.com">Edwang</a>
 */
public class TimeClient {

    private static final String ENDPOINT = "127.0.0.1";
    private static final int PORT = 10086;


    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            boolean connected = socketChannel.connect(new InetSocketAddress(ENDPOINT, PORT));
            Selector selector = Selector.open();
            doConnect(socketChannel, connected, selector);
            while (true) {
                if (selector == null) {
                    return;
                }
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey next = it.next();
                    it.remove();
                    try {
                        handleInput(selector, next);
                    } catch (Exception e) {
                        if (next != null) {
                            next.cancel();
                            if (next.channel() != null) {
                                next.channel().close();
                            }
                        }
                    }
                }
                try {
                    selector.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleInput(Selector selector, SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isConnectable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                if (sc.finishConnect()) {
                    sc.register(selector, SelectionKey.OP_READ);
                    doWrite(sc);
                }
            }
        }
    }

    private static void doConnect(SocketChannel socketChannel, boolean connected, Selector selector)
            throws IOException {
        if (connected) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            doWrite(socketChannel);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }
    }

    private static void doWrite(SocketChannel socketChannel) throws IOException {
        byte[] bytes = "Query Time Of Now".getBytes("UTF-8");
        ByteBuffer writeBuf = ByteBuffer.allocate(bytes.length);
        writeBuf.put(bytes);
        writeBuf.flip();
        socketChannel.write(writeBuf);
        if (!writeBuf.hasRemaining()) {
            System.out.println("Order Send");
        }
    }
}
