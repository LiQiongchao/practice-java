package com.oio.practice.thread.chapter03.t3;

/**
 * ThreadLocal使用测试
 * @author Liqc
 * @date 2020/4/4 13:59
 */
public class ThreadLocalTest {
    private static ThreadLocal threadLocal = new ThreadLocal();
    public static void main(String[] args) {
        try {
            Thread ta = new Thread(() -> {
                try {
                    for (int i = 0; i < 100; i++) {
                        threadLocal.set("threadA " + (i + 1));
                        System.out.println(threadLocal.get());
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            Thread tb = new Thread(() -> {
                try {
                    for (int i = 0; i < 100; i++) {
                        threadLocal.set("threadB " + (i + 1));
                        System.out.println(threadLocal.get());
                        Thread.sleep(200);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            ta.start();
            tb.start();
            for (int i = 0; i < 100; i++) {
                threadLocal.set("main " + (i + 1));
                System.out.println(threadLocal.get());
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
        main 1
        threadA 1
        threadB 1
        threadA 2
        threadB 2
        main 2
        threadB 3
         */
    }
}
