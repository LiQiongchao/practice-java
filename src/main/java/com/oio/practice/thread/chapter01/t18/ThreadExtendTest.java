package com.oio.practice.thread.chapter01.t18;

/**
 * 线程优先级分为10级，1~10级依次提高
 * 测试线程优先级的继承性
 *  如果线程没有指定线程优先线，默认会使用调用者的线程优先级作为自己线程优先级。
 * @author Liqc
 * @date 2019/11/28 12:53
 */
public class ThreadExtendTest {
    public static void main(String[] args) {
        System.out.println("main thread begin priority = " + Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(6);
        System.out.println("main thread end priority = " + Thread.currentThread().getPriority());
        new Thread(()->{
            System.out.println("priorityThread1 "+ Thread.currentThread().getName() +" priority = " + Thread.currentThread().getPriority());
            new Thread(() -> {
                System.out.println("priorityThread2 "+ Thread.currentThread().getName() +" priority = " + Thread.currentThread().getPriority());
            }).start();
        }).start();
    }
    /*
    关闭注释
    main thread begin priority = 5
    main thread end priority = 5
    priorityThread1 priority = 5
    priorityThread2 priority = 5

    打开注释
    main thread begin priority = 5
    main thread end priority = 6
    priorityThread1 priority = 6
    priorityThread2 priority = 6
     */
}
