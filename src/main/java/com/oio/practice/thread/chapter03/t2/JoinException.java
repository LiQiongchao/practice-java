package com.oio.practice.thread.chapter03.t2;

/**
 * 如果在等待的线程出现异常，则在等待的线程结束，子线程会继续运行。
 * @author Liqc
 * @date 2020/4/3 13:46
 */
public class JoinException {

    public static void main(String[] args) {
        try {
            Thread b = new Thread(() -> {
                try {
                    // 开启子线程a
                    Thread a = new Thread(() -> {
                        for (int i = 0; i < Integer.MAX_VALUE; i++) {
                            try {
                                System.out.println(Thread.currentThread().getName() + " - " + i);
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    a.start();
                    a.join();
                    System.out.println("b 线程结束了");
                } catch (InterruptedException e) {
                    System.out.println("b 线程异常了");
                }
            });
            b.start();
            Thread.sleep(2000L);
            b.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    Thread-1 - 0
    Thread-1 - 1
    b 线程异常了
    Thread-1 - 2
    Thread-1 - 3
    ……
     */
}
