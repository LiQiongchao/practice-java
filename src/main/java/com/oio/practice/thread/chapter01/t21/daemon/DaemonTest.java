package com.oio.practice.thread.chapter01.t21.daemon;

/**
 * 守护线程测试
 *      守护线程会在进程中没有非守护线程时，自动销毁，最常见的就是JVM。特性就是“陪伴”的含义。
 * @author Liqc
 * @date 2019/11/29 16:35
 */
public class DaemonTest {

    public static void main(String[] args) {
        try {
            Thread daemonThread = new Thread(()->{
                try {
                    int i = 0;
                    while (true) {
                        System.out.println("i = " + ++i);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            daemonThread.setDaemon(true);
            daemonThread.start();
            Thread.sleep(5000);
            System.out.println("main线程结果后，daemonThread就不会打印，也会停止。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
        i = 1
        i = 2
        i = 3
        i = 4
        i = 5
        main线程结果后，daemonThread就不会打印，也会停止。
         */
    }

}
