package com.oio.practice.jdk.io;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Java NIO单向数据连接。
 *
 * @Author: LiQiongchao
 * @Date: 2020/6/21 0:24
 */
public class PipeTest {

    @Test
    public void pipeTest () throws IOException {
        // 获取通道
        Pipe pipe = Pipe.open();
        // 将缓冲区的数据写入通道
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Pipe.SinkChannel sinkChannel = pipe.sink();
        buffer.put((DateTimeFormatter.ofPattern("yyyy-MM-dd mm:ss:SSS").format(LocalDateTime.now()) + " - hello").getBytes());
        buffer.flip();
        sinkChannel.write(buffer);

        // 读取缓冲区中的数据
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        Pipe.SourceChannel sourceChannel = pipe.source();
        // 读取数据到 buffer2 中
        int len = sourceChannel.read(buffer2);
        System.out.println(new String(buffer2.array(), 0, len));

        sourceChannel.close();;
        sinkChannel.close();
    }

}
