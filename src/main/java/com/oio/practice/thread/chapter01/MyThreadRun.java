package com.oio.practice.thread.chapter01;

/**
 * @author Liqc
 * @date 2019/8/2 12:47
 */
public class MyThreadRun {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread());
        thread.start();
        MyThread1 t = new MyThread1();
        t.start();
        System.out.println("main run over.");
    }
}
class MyThread implements Runnable {
    @Override
    public void run() {
        System.out.println("实现 Runnable 接口启动线程。");
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("继承Thread方式启动线程。");
    }
}


