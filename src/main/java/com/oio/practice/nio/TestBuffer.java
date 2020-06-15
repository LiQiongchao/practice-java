package com.oio.practice.nio;

import io.netty.buffer.ByteBuf;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 一、缓冲区（Buffer）：在Java NIO中负责数据的存取。缓冲区就是数组。用于存储不同数据类型的数据。
 *
 * 根据数据类型的不同（boolean 除外），提供了相应的类型缓冲区。
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 上述缓冲区的管理方式几乎一致，通过 allocate() 获取缓冲区
 *
 * 二、缓冲区在存取数据的两个核心方法。
 * put() : 存入数据到缓冲区
 * get() : 获取缓冲区中的数据
 *
 * 三、缓冲区中的四个核心属性：
 * capacity: 容量，表示缓冲区最大存储的容量。一旦声明不能改变。
 * limit: 界限，表示缓冲区可以操作数据的大小。（limit 后数据不能进行读写）
 * position: 位置，表示缓冲区中正在操作数据的位置。
 *
 * mark : 标记，表示记录当前position 的位置。可以通过 reset() 方法恢复到 mark 的位置。
 *
 * 0 <= mark <= position <= limit <= capacity
 *
 * 四、直接缓冲区与非直接缓冲区：
 * 非直接缓冲区：通过 allocate() 方法分配缓冲区，将缓冲区建立在 JVM 的内存中
 * 直接缓冲区：通过 allocateDirect() 方法分配直接缓冲区，将缓冲区建立在物理内存中。可以提高效率。
 *
 * @Author: LiQiongchao
 * @Date: 2020/6/15 21:26
 */
public class TestBuffer {

    @Test
    public void test3() {
        // 分配直接缓冲区
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.printf("is direct memory = %b \n", buffer.isDirect());
    }

    @Test
    public void test2() {
        String str = "abcde";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());

        // 调用flip后，limit指的是 str.length() 的位置
        buffer.flip();

        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes, 0, 2);
        System.out.println(new String(bytes, 0, 2));
        System.out.printf("position=%s \n", buffer.position());

        // make() 标记当前的position
        buffer.mark();

        buffer.get(bytes, 2, 2);
        System.out.println(new String(bytes, 2, 2));
        System.out.println(buffer.position());

        // reset() 恢复到 make 标记的位置
        buffer.reset();
        System.out.printf("reset position=%s \n", buffer.position());

        // 是否还有未记取的数据
        if (buffer.hasRemaining()) {
            // 剩余数据的数量
            System.out.printf("remain data length = %s \n", buffer.remaining());
        }

    }

    @Test
    public void test1() {
        String str = "abcde";

        // 1.分配一个指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        System.out.println("------------------allocate()------------------");
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());

        // 2.利用 put() 存入数据到缓冲区中
        buffer.put(str.getBytes());

        System.out.println("------------------put()------------------");
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());


        // 3.切换读取数据的模式
        buffer.flip();

        System.out.println("------------------flip()------------------");
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());

        // 4.利用 get() 读取缓冲中的数据
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        System.out.println(new String(bytes));

        System.out.println("------------------get()------------------");
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());

        // 5.rewind() 可重复读
        buffer.rewind();

        System.out.println("------------------rewind()------------------");
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());

        // 6.clear() 清空缓冲区，但是缓冲区的数据还在，处于被遗忘状态，position, limit被重置。
        buffer.clear();

        System.out.println("------------------clear()------------------");
        System.out.println(buffer.capacity());
        System.out.println(buffer.limit());
        System.out.println(buffer.position());
        System.out.println((char) buffer.get());


    }

}
