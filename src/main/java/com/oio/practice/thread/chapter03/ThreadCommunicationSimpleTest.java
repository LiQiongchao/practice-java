package com.oio.practice.thread.chapter03;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程通信的简单例子
 *      一个线程累计5个元素时，通知另一个线程继续执行。注：两个线程要使用同一把锁。
 * @author Liqc
 * @date 2020/1/10 13:06
 */
public class ThreadCommunicationSimpleTest {
    public static void main(String[] args) throws InterruptedException {
        mainTest(new String("123"));
        /*
        get in mainTest. time:1578633587374
        添加了1个元素！
        添加了2个元素！
        添加了3个元素！
        添加了4个元素！
        end notify. time:1578633591431
        添加了5个元素！
        添加了6个元素！
        添加了7个元素！
        添加了8个元素！
        添加了9个元素！
        添加了10个元素！
        get out mainTest. time:1578633597433
         */
    }

    public static void mainTest(Object lock) throws InterruptedException {
        new Thread(() -> {
            try {
                synchronized (lock) {
                    if (SimpleCommServer.getSize() != 5) {
                        System.out.println("get in mainTest. time:" + System.currentTimeMillis());
                        lock.wait();
                        System.out.println("get out mainTest. time:" + System.currentTimeMillis());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(50);
        new Thread(() -> {
            try {
                synchronized (lock) {
                    for (int i = 0; i<10;i++) {
                        SimpleCommServer.add();
                        if (SimpleCommServer.getSize() == 5) {
                            lock.notify();
                            System.out.println("end notify. time:" + System.currentTimeMillis());
                        }
                        System.out.println("添加了" + (i+1) + "个元素！");
                        Thread.sleep(1000);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}

@Data
class SimpleCommServer {
    private static List list = new ArrayList();

    public static void add () {
        list.add("hello");
    }
    public static int getSize() {
        return list.size();
    }
}

