package com.oio.practice.thread.chapter04.t1;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试Lock的相关方法
 * getHoldCount(): 查询当前线程保持此锁定的个数，也就是调用lock()方法的次数。
 * getQueueLength(): 查询当前正在等待获取锁的线程数。（不包含sleep和wait的线程）
 * getWaitQueueLength(Condition condition): 根据一个Condition查询正在等待的线程数。注：需要在锁内执行，否则会异常。
 *
 * @author Liqc
 * @date 2020/4/13 13:38
 */
public class LockMethodTest1 {

    public static void main(String[] args) {
        try {
            LockMethodTestService service = new LockMethodTestService();
            // 测试getHoldCount
            //        service.serviceMethod1();
            /* 测试结果
            serviceMethod1 getHoldCount=1
            serviceMethod2 getHoldCount=2
             */

            // getQueueLength测试
//            getQueueLengthTest(service);

            // 测试getWaitQueueLength(Condition condition)
            getWaitQueueLengthTest(service);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void getQueueLengthTest(LockMethodTestService service) {
        try {
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

    static void getWaitQueueLengthTest(LockMethodTestService service) {
        try {
            Runnable runnable = () -> {
                service.waitService();
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
            service.notifyService();
//            System.out.println("有" + service.getLock().getWaitQueueLength(service.getCondition()) + "个线程正在等待condition！");
            /*
            有5个线程正在等待condition
            signal()后有4个线程正在等待condition
             */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

@Data
class LockMethodTestService {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

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

    public void waitService() {
        try {
            lock.lock();
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void notifyService() {
        try {
            lock.lock();
            System.out.println("有" + lock.getWaitQueueLength(condition) + "个线程正在等待condition");
            condition.signal();
            System.out.println("signal()后有" + lock.getWaitQueueLength(condition) + "个线程正在等待condition");
        } finally {
            lock.unlock();
        }
    }

}
