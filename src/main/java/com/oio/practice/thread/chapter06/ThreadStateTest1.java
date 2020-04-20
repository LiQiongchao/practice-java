package com.oio.practice.thread.chapter06;

/**
 * 测试线程状态 NEW, RUNNABLE, TERMINATED
 * @author Liqc
 * @date 2020/4/20 13:33
 */
public class ThreadStateTest1 {
    public static void main(String[] args) {
        try {
            ThreadStateTest1Service service = new ThreadStateTest1Service();
            System.out.println("main方法中的状态1：" + service.getState());
            Thread.sleep(1000);
            service.start();
            Thread.sleep(1000);
            System.out.println("main方法中的状态2：" + service.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    构造器方法中的状态：RUNNABLE
    main方法中的状态1：NEW
    run方法中的状态：RUNNABLE
    main方法中的状态2：TERMINATED
     */

}

class ThreadStateTest1Service extends Thread {

    public ThreadStateTest1Service() {
        System.out.println("构造器方法中的状态：" + Thread.currentThread().getState());
    }

    @Override
    public void run() {
        System.out.println("run方法中的状态：" + Thread.currentThread().getState());
    }
}
