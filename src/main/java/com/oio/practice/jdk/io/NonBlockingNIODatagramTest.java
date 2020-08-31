package com.oio.practice.jdk.io;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * DatagramChannel 收发UDP包测试
 *
 * @Author: LiQiongchao
 * @Date: 2020/6/20 21:11
 */
public class NonBlockingNIODatagramTest {

    @Test
    public void send() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.next();
            buffer.put((new Date() + " - " + str).getBytes());
            buffer.flip();
            datagramChannel.send(buffer, new InetSocketAddress("localhost", 9091));
            buffer.clear();
        }
        datagramChannel.close();
    }

    @Test
    public void receive() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        // 非阻塞
        datagramChannel.configureBlocking(false);
        datagramChannel.bind(new InetSocketAddress(9091));
        Selector selector = Selector.open();

        datagramChannel.register(selector, SelectionKey.OP_READ);

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isReadable()) {
                    ByteBuffer allocate = ByteBuffer.allocate(1024);

                    datagramChannel.receive(allocate);
                    allocate.flip();
                    System.out.println(new String(allocate.array(), 0, allocate.limit()));
                    allocate.clear();
                }
            }
            iterator.remove();
        }
    }

}
