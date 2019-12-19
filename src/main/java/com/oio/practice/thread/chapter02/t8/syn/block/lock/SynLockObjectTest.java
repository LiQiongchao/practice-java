package com.oio.practice.thread.chapter02.t8.syn.block.lock;

/**
 * synchronized(this)是锁当前对象
 * @author Liqc
 * @date 2019/12/16 13:14
 */
public class SynLockObjectTest {
    public static void main(String[] args) {
        try {
            ObjectLockTask task = new ObjectLockTask();
            new Thread(()->task.doLongTimeMethod()).start();
            Thread.sleep(100);
            new Thread(()->task.otherMethod()).start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
结果无序
    synchronized threadName=Thread[Thread-0,5,main] i=8098
    synchronized threadName=Thread[Thread-0,5,main] i=8099
    synchronized threadName=Thread[Thread-0,5,main] i=8100
    synchronized threadName=Thread[Thread-0,5,main] i=8101
    synchronized threadName=Thread[Thread-0,5,main] i=8102
    ----------------------------run------other method.
    synchronized threadName=Thread[Thread-0,5,main] i=8103
    synchronized threadName=Thread[Thread-0,5,main] i=8104

otherMethod方法修改为同步后，为有序
    synchronized threadName=Thread[Thread-0,5,main] i=9999
    synchronized threadName=Thread[Thread-0,5,main] i=10000
    ----------------------------run------other method.
     */
}
class ObjectLockTask {
//  public void otherMethod() {
    synchronized public void otherMethod() {
        System.out.println("----------------------------run------other method.");
    }

    synchronized public void doLongTimeMethod() {
        for (int i = 0; i < 10000; i++) {
            System.out.println("synchronized threadName=" + Thread.currentThread() + " i=" + (i + 1));
        }
    }
}
