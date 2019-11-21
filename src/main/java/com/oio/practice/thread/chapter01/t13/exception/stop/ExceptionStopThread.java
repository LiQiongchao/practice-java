package com.oio.practice.thread.chapter01.t13.exception.stop;

import com.oio.practice.thread.chapter01.t11.stop.PrintThread;

/**
 * @author Liqc
 * @date 2019/9/26 13:23
 */
public class ExceptionStopThread {
    public static void main(String[] args) {
        try {
//            ExpStopThread printThread = new ExpStopThread();
            ExpStopThread2 printThread = new ExpStopThread2();
            printThread.start();
            Thread.sleep(200);
            printThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end");
        /*
        ExpStopThread
        i = 37431
        i = 37432
        线程停止，我退出了。
        此代码还会执行。。。
        end
         */

        /*
        ExpStopThread2
        i = 18500
        i = 18501
        end
        线程停止，我退出了。
        进行catch中。。。
        java.lang.InterruptedException
            at com.oio.practice.thread.chapter01.t13.exception.stop.ExpStopThread2.run(ExceptionStopThread.java:59)
        * */
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

/**
 * 改进版，在收到线程停止的命令后，抛出异常，使程序不再往下执行。
 */
class ExpStopThread2 extends Thread {
    @Override
    public void run() {
        try {
            super.run();
            for (int i = 1; i <= 50000; i++) {
                System.out.println("i = " + i);
                if (Thread.interrupted()) {
                    System.out.println("线程停止，我退出了。");
                    throw new InterruptedException();
                }
            }
            System.out.println("此代码还会执行。。。");
        } catch (InterruptedException e) {
            System.out.println("进行catch中。。。");
            e.printStackTrace();
        }
    }
}