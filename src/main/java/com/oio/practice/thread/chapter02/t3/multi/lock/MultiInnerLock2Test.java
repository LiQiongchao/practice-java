package com.oio.practice.thread.chapter02.t3.multi.lock;

/**
 * 子类可以通过“可重入锁”调用父类的同步方法
 * @author Liqc
 * @date 2019/12/4 13:41
 */
public class MultiInnerLock2Test {
    public static void main(String[] args) {
        new Thread(() -> new SubOperation().subMethod()).start();
    }
    /*
    sub print i = 9
    main print i = 8
    sub print i = 7
    main print i = 6
    sub print i = 5
    main print i = 4
    sub print i = 3
    main print i = 2
    sub print i = 1
    main print i = 0
     */
}
class Operation {
    int i = 10;
    synchronized public void mainMethod() {
        try {
            i--;
            System.out.println("main print i = " + i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class SubOperation extends Operation {
    synchronized public void subMethod() {
        try {
            while (i > 0) {
                i--;
                System.out.println("sub print i = " + i);
                Thread.sleep(100);
                super.mainMethod();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}