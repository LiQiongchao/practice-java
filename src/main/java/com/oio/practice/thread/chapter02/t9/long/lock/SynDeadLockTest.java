package com.oio.practice.thread.chapter02.t9.dead.lock;

/**
 * 同步方法资源占用解决
 * @author Liqc
 * @date 2019/12/31 12:55
 */
public class SynDeadLockTest {

    public static void main(String[] args) {
//        SynLongLockServiceA service = new SynLongLockServiceA();
        SynLongLockServiceB service = new SynLongLockServiceB();
        new Thread(() -> service.methodA()).start();
        new Thread(() -> service.methodB()).start();
        /**
         * serviceA 一直等待
         * methodA begin
         *
         * 解决：使用2把锁
         * methodA begin
         * methodB begin
         * methodB end
         */
    }

}

class SynLongLockServiceA {

    synchronized public void methodA() {
        System.out.println("methodA begin");
        boolean isRun = true;
        while (isRun) {}
        System.out.println("methodA end");
    }

    synchronized public void methodB() {
        System.out.println("methodB begin");
        System.out.println("methodB end");
    }
}
class SynLongLockServiceB {
    Object object = new Object();
    public void methodA() {
        synchronized (object) {
            System.out.println("methodA begin");
            boolean isRun = true;
            while (isRun) {}
            System.out.println("methodA end");
        }
    }
    Object object1 = new Object();
    public void methodB() {
        synchronized (object1) {
            System.out.println("methodB begin");
            System.out.println("methodB end");
        }
    }
}

