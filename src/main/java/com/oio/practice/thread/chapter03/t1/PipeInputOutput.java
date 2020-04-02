package com.oio.practice.thread.chapter03.t1;

import java.io.*;

/**
 * 测试使用管道在线程之间通信
 * @author Liqc
 * @date 2020/4/2 13:20
 */
public class PipeInputOutput {

    public static void main(String[] args) {
        try {
            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();
            // 关联管道的输入与输出, 或者在构造器里关联
//            inputStream.connect(outputStream);
            outputStream.connect(inputStream);
            new Thread(() -> new ReadData().read(inputStream)).start();
            Thread.sleep(2000L);
            new Thread(() -> new WriteData().write(outputStream)).start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class WriteData {
    public void write(PipedOutputStream outputStream) {
        try {
            System.out.println("write: ");
            for (int i = 0; i < 300; i++) {
                String outData = (i + 1) + ", ";
                outputStream.write(outData.getBytes());
                System.out.print(outData);
            }
            System.out.println();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(PipedWriter writer) {
        try {
            System.out.println("write: ");
            for (int i = 0; i < 300; i++) {
                String outData = (i + 1) + ", ";
                writer.write(outData);
                System.out.print(outData);
            }
            System.out.println();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadData {
    public void read(PipedInputStream inputStream) {
        try {
            System.out.println("read: ");
            byte[] bytes = new byte[20];
            int readLength = inputStream.read(bytes);
            while (readLength != -1) {
                String readMsg = new String(bytes, 0, readLength);
                System.out.print(readMsg);
                readLength = inputStream.read(bytes);
            }
            System.out.println();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void read(PipedReader reader) {
        try {
            System.out.println("read: ");
            char[] chars = new char[20];
            int readLength = reader.read(chars);
            while (readLength != -1) {
                String readMsg = new String(chars, 0, readLength);
                System.out.print(readMsg);
                readLength = reader.read(chars);
            }
            System.out.println();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
