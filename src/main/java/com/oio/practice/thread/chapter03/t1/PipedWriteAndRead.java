package com.oio.practice.thread.chapter03.t1;

import java.io.*;

/**
 * 测试线程间字符流通信
 * @author Liqc
 * @date 2020/4/2 13:44
 */
public class PipedWriteAndRead {
    public static void main(String[] args) {
        try {
            PipedWriter writer = new PipedWriter();
            PipedReader reader = new PipedReader(writer);
            // 关联管道的输入与输出, 或者在构造器里关联
//            writer.connect(reader);
            new Thread(() -> new ReadData().read(reader)).start();
            Thread.sleep(2000L);
            new Thread(() -> new WriteData().write(writer)).start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

