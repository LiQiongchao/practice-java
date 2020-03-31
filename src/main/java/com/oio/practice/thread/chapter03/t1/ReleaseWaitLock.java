package com.oio.practice.thread.chapter03.t1;

/**
 * wait()方法执行这行代码后会进入阻塞队列，同时释放锁。
 * @author Liqc
 * @date 2020/3/25 13:00
 */
public class ReleaseWaitLock {

    public static void main(String[] args) {
        Object lock = new Object();
        new Thread(() -> new WaitService().testMethod(lock)).start();
        new Thread(() -> new WaitService().testMethod(lock)).start();
        /*
        method start
        method start
         */
    }

}

class WaitService {

    public void testMethod(Object lock) {
        try {
            synchronized (lock) {
                System.out.println("method start");
                lock.wait();
                // 使用sleep又变成同步锁了。
//                Thread.sleep(500);
                System.out.println("method end");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
