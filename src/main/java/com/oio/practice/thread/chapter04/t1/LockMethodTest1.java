package com.oio.practice.thread.chapter04.t1;

import lombok.Data;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试Lock的相关方法
 * getHoldCount(): 查询当前线程保持此锁定的个数，也就是调用lock()方法的次数。
 * getQueueLength(): 查询当前正在等待获取锁的线程数。（不包含sleep和wait的线程）
 * @author Liqc
 * @date 2020/4/13 13:38
 */
public class LockMethodTest1 {

    public static void main(String[] args) {
        try {
            LockMethodTestService service = new LockMethodTestService();
    //        service.serviceMethod1();
            /*
            serviceMethod1 getHoldCount=1
            serviceMethod2 getHoldCount=2
             */

            // getQueueLength测试
            Runnable runnable = () -> {
                service.service3();
            };
            int arrLength = 5;
            Thread[] threads = new Thread[arrLength];
            for (int i = 0; i < arrLength; i++) {
                threads[i] = new Thread(runnable);
            }
            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
            }
            Thread.sleep(200);
            System.out.println("有" + service.getLock().getQueueLength() + "个线程在等待获取锁！");
            /*
            ThreadName=Thread-0进入方法！
            有4个线程在等待获取锁！
             */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



}

@Data
class LockMethodTestService {
    private ReentrantLock lock = new ReentrantLock();

    public void serviceMethod1() {
        try {
            lock.lock();
            System.out.println("serviceMethod1 getHoldCount=" + lock.getHoldCount());
            serviceMethod2();
        } finally {
            lock.unlock();
        }
    }

    private void serviceMethod2() {
        try {
            lock.lock();
            System.out.println("serviceMethod2 getHoldCount=" + lock.getHoldCount());
        } finally {
            lock.unlock();
        }
    }

    public void service3() {
        try {
            lock.lock();
            System.out.println("ThreadName=" + Thread.currentThread().getName()+"进入方法！");
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
