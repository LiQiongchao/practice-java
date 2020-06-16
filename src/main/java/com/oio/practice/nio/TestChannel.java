package com.oio.practice.nio;

import org.junit.Test;

import javax.xml.stream.events.Characters;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.*;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;
import java.util.SortedMap;

/**
 * 一、通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 *
 * 二、通道的主要实现类
 * 	java.nio.channels.Channel 接口：
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 *
 * 三、获取通道
 * 1. Java 针对支持通道的类提供了 getChannel() 方法
 * 		本地 IO：
 * 		FileInputStream/FileOutputStream
 * 		RandomAccessFile
 *
 * 		网络IO：
 * 		Socket
 * 		ServerSocket
 * 		DatagramSocket
 *
 * 2. 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 * 3. 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 *
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 *
 * 五、分散(Scatter)与聚集(Gather)
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *
 * 六、字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组  -> 字符串
 *
 *
 * @Author: LiQiongchao
 * @Date: 2020/6/16 21:45
 */
public class TestChannel {


    @Test
    public void charsetTest() throws CharacterCodingException {
        Charset charset = Charset.forName("GBK");
        // 获取字符编码器
        CharsetEncoder encoder = charset.newEncoder();
        // 获取字符解码器
        CharsetDecoder decoder = charset.newDecoder();

        CharBuffer buffer = CharBuffer.allocate(1024);
        buffer.put("哈哈，哈哈");
        buffer.flip();

        // 编码
        ByteBuffer bBuf = encoder.encode(buffer);

        for (int i = 0; i < 10; i++) {
            // 取之前要调用 flip() 方法
            System.out.println(bBuf.get());
        }

        // 解码， flip()要在decode之前调用
        bBuf.flip();
        CharBuffer decode = decoder.decode(bBuf);
        System.out.println(decode.toString());

        System.out.println("--------------------------------------------------------------");

        // 如果使用其它编码解码就会出现乱码
        Charset charset1 = StandardCharsets.UTF_8;
        bBuf.flip();
        CharBuffer decode1 = charset1.decode(bBuf);
        System.out.println(decode1.toString());

    }

    /**
     * 打印所有的字符集
     */
    @Test
    public void allAvailableCharsets() {
        SortedMap<String, Charset> map = Charset.availableCharsets();
        map.forEach((k,v) -> System.out.printf("%s = %s\n", k, v));
    }


    /**
     * 分散与聚集
     */
    @Test
    public void scattingAndGather() throws IOException {
        Instant start = Instant.now();
        String path = "D:\\WorkSpaces\\practise-projects\\practice-java\\src\\main\\resources\\";
        RandomAccessFile accessFile = new RandomAccessFile(path + "read", "rw");

        // 获取通道
        FileChannel channel = accessFile.getChannel();

        // 分配指定大小的缓冲区
        ByteBuffer buf1 = ByteBuffer.allocate(3);
        ByteBuffer buf2 = ByteBuffer.allocate(1024);

        // 分散读取
        ByteBuffer[] bufs = {buf1, buf2};
        channel.read(bufs);

        for (ByteBuffer buf : bufs) {
            buf.flip();
        }

        System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
        System.out.println("--------------------------------------------------------------");
        System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

        // 聚集写入
        RandomAccessFile accessFile1 = new RandomAccessFile(path + "2.txt", "rw");
        FileChannel channel1 = accessFile1.getChannel();
        channel1.write(bufs);
    }


    /**
     * 通道之间的数据传输（直接缓冲区）
     */
    @Test
    public void useChannelAndDirect() throws IOException {
        Instant start = Instant.now();
        String path = "D:\\WorkSpaces\\practise-projects\\practice-java\\src\\main\\resources\\";
        FileChannel inChannel = FileChannel.open(Paths.get(path, "1.mp4"), StandardOpenOption.READ);
        // StandardOpenOption.CREATE_NEW 不存在创建，存在就报错。StandardOpenOption.CREATE 存在不存在都会去创建
        FileChannel outChannel = FileChannel.open(Paths.get(path, "2.mp4"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

//        inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());

        inChannel.close();
        outChannel.close();

        System.out.printf("复制花费时间： %d ms", Duration.between(start, Instant.now()).toMillis());
        // 复制花费时间： 3259 ms
    }

    /**
     * 使用直接缓冲区完成文件的复制（内存映射文件）比非直接缓冲区效率要高
     */
    @Test
    public void useMemoryMap() throws IOException {
        Instant start = Instant.now();
        FileChannel inChannel = FileChannel.open(Paths.get("D:\\WorkSpaces\\practise-projects\\practice-java\\src\\main\\resources\\1.mp4"), StandardOpenOption.READ);
        // StandardOpenOption.CREATE_NEW 不存在创建，存在就报错。StandardOpenOption.CREATE 存在不存在都会去创建
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\WorkSpaces\\practise-projects\\practice-java\\src\\main\\resources\\2.mp4"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

        // 内存映射文件
        // 如果文件过大，会报java.lang.IllegalArgumentException: Size exceeds Integer.MAX_VALUE，需要优化
        MappedByteBuffer inMapBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMapBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 直接对缓冲区进行数据的读写操作
        byte[] bytes = new byte[inMapBuffer.limit()];
        inMapBuffer.get(bytes);
        outMapBuffer.put(bytes);

        inChannel.close();
        outChannel.close();
        System.out.printf("复制花费时间： %d ms", Duration.between(start, Instant.now()).toMillis());

    }

    /**
     * 利用通道完成文件的复制（非直接缓冲区）
     */
    @Test
    public void notDirectBufferTest() {
        Instant start = Instant.now();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            fis = new FileInputStream(new File("D:\\WorkSpaces\\practise-projects\\practice-java\\src\\main\\resources\\1.mp4"));
            fos = new FileOutputStream("D:\\WorkSpaces\\practise-projects\\practice-java\\src\\main\\resources\\2.mp4");

            // 获取通道
            inChannel = fis.getChannel();
            outChannel = fos.getChannel();

            // 分配指定大小的缓冲区
            ByteBuffer buf = ByteBuffer.allocate(1024);
            // 将通道中的数据存入缓冲区中
            while (inChannel.read(buf) != -1) {
                // 切换到记取数据模式
                buf.flip();
                // 将缓冲区中的数据写入通道中
                outChannel.write(buf);
                // 清空缓冲区
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inChannel != null) {
                try {
                    inChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outChannel != null) {
                try {
                    outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.printf("复制花费时间：%d ms", Duration.between(start, Instant.now()).toMillis());
        // 复制花费时间：19948 ms
    }

}
