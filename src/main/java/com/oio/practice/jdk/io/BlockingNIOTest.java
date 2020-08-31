package com.oio.practice.jdk.io;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、使用 NIO 完成网络通信的三个核心：
 * 1. 通道（Channel）：负责连接
 *
 * 	   java.nio.channels.Channel 接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 * 2. 缓冲区（Buffer）：负责数据的存取
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 *
 * @Author: LiQiongchao
 * @Date: 2020/6/17 22:50
 */
public class BlockingNIOTest {

    /**
     * 客服端
     */
    @Test
    public void client() throws IOException {
        // 1- 获取通道，建立连接
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9090));

        // 2- 建立要读取文件的连接
        FileChannel fileChannel = FileChannel.open(Paths.get("D:\\WorkSpaces\\practise-projects\\practice-java\\src\\main\\resources\\1.jpg"), StandardOpenOption.READ);

        // 3- 缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 4- 读取文件并发送到服务端
        if (fileChannel.read(buffer) != -1) {
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }
        // 5- 关闭通道
        fileChannel.close();
        socketChannel.close();
    }

    /**
     * 服务端
     */
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

        // 7- 关闭流
        socketChannel.close();;
        fileChannel.close();
        serverSocketChannel.close();
    }

}
