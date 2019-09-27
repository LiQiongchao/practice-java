package com.oio.practice.thread.chapter01.t13.exception.stop;

import com.oio.practice.thread.chapter01.t11.stop.PrintThread;

/**
 * @author Liqc
 * @date 2019/9/26 13:23
 */
public class ExceptionStopThread {
    public static void main(String[] args) {
        try {
            ExpStopThread printThread = new ExpStopThread();
            printThread.start();
            Thread.sleep(200);
            printThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
        /*
        i = 37431
        i = 37432
        线程停止，我退出了。
        此代码还会执行。。。
        end
         */
    }
}

class ExpStopThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 1; i <= 50000; i++) {
            System.out.println("i = " + i);
            if (Thread.interrupted()) {
                System.out.println("线程停止，我退出了。");
                break;
            }
        }
        System.out.println("此代码还会执行。。。");
    }
}