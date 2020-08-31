package com.oio.practice.jdk.io;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 非阻塞IO测试
 *
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
 * @Date: 2020/6/20 12:53
 */
public class NonBlockingNIOTest {

    /**
     * 启动多个客户端就是一个聊天室的场景
     * @throws IOException
     */
    @Test
    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9091));
        socketChannel.configureBlocking(false);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.next();
            buffer.put((new Date() + " : " + str).getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();
        }

        socketChannel.close();
    }

     @Test
    public void server() throws IOException {
         // 1.获取通道
         ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
         // 2.切换为非阻塞模式, accept()为阻塞获取
         serverSocketChannel.configureBlocking(false);
         // 3.绑定端口
         serverSocketChannel.bind(new InetSocketAddress(9091));
         // 4.获取选择器
         Selector selector = Selector.open();
         // 5.将通道注册到选择器上，并且指定“监听接收事件”
         serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
         // 6.轮询式的获取选择器上已经 “准备就绪” 的事件
         while (selector.select() > 0) {
             // 7.获取当前选择器中所有注册的 “选择键（已就绪的监听事件）”
             Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
             while (iterator.hasNext()) {
                 // 8.获取准备“就绪”的事件
                 SelectionKey selectionKey = iterator.next();
                 // 9.判断具体是什么事件准备就绪
                 if (selectionKey.isAcceptable()) {
                     // 10.若“接收就绪”，获取客户端连接
                     SocketChannel socketChannel = serverSocketChannel.accept();
                     // 11.切换非阻塞模式
                     socketChannel.configureBlocking(false);
                     // 12.将该通道注册到选择器上
                     socketChannel.register(selector, SelectionKey.OP_READ);
                 } else if (selectionKey.isReadable()) {
                     // 13.获取当前选择器上“读取就绪”状态的通道
                     SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                     // 14.读取数据
                     ByteBuffer buffer = ByteBuffer.allocate(1024);
                     int len = 0;
                     // 这里要取值大于0
                     while ((len = socketChannel.read(buffer)) > 0) {
                         buffer.flip();
                         System.out.println(new String(buffer.array(), 0, len));
                         buffer.clear();
                     }
                 }
                 // 15.取消选择键 SelectionKey
                 iterator.remove();
             }
         }
     }

}
