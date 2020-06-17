package com.oio.practice.jdk.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @Author: LiQiongchao
 * @Date: 2020/6/17 23:12
 */
public class BlockingNIO2Test {

    @Test
    public void client() throws IOException {
        // 1- 获取通道，建立连接
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9090));
        // 2- 建立要读取文件的连接
        FileChannel fileChannel = FileChannel.open(Paths.get("src/main/resources/1.jpg"), StandardOpenOption.READ);
        // 3- 缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 4- 读取文件并发送到服务端
        while (fileChannel.read(buffer) != -1) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }
        // Shutdown the connection for writing without closing the channel.
        // 如果不关闭，会在读取反馈时，一直阻塞
        socketChannel.shutdownOutput();

        // 接收服务端的反馈
        int len = 0;
        while ((len = socketChannel.read(buffer)) != -1) {
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, len));
            buffer.clear();
        }
        // 5- 关闭通道
        fileChannel.close();
        socketChannel.close();
    }

    @Test
    public void server() throws IOException {
        // 1- 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 2- 绑定端口
        serverSocketChannel.bind(new InetSocketAddress(9090));
        // 3- 获取写文件的通道
        FileChannel fileChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        // 4- 建立缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        // 5- 获取客户端连接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();

        // 6- 读取客户端发送的文件
        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }

        // 发送反馈信息到客户端
        buffer.put("接收成功！！！123".getBytes());
        buffer.flip();
        socketChannel.write(buffer);

        // 7- 关闭流
        socketChannel.close();
        fileChannel.close();
        serverSocketChannel.close();
    }

}
