package com.oio.practice.thread.chapter01;

/**
 * @author Liqc
 * @date 2019/8/2 12:47
 */
public class MyThreadRun {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyThread());
        thread.start();
        System.out.println("main run over.");
    }
}
class MyThread implements Runnable {

    @Override
    public void run() {
        System.out.println("thread fun");
    }
}



