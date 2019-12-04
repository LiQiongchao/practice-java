package com.oio.practice.thread.chapter02.t3.multi.lock;

/**
 * 可重入锁：一个线程获取到对象锁后，是可以再次申请到内部锁，这就是在同步方法内可以调用其它同步方法的原因。
 * 当不可重入锁时就会造成死锁。
 * @author Liqc
 * @date 2019/12/4 13:30
 */
public class MultiLockTest {
    public static void main(String[] args) {
        new Thread(()->new Service().serviceA()).start();
    }
}
class Service {
    public void serviceA() {
        System.out.println("serviceA");
        serviceB();
    }

    private void serviceB() {
        System.out.println("serviceB");
        serviceC();
    }

    private void serviceC() {
        System.out.println("serviceC");
    }
    /*
    serviceA
    serviceB
    serviceC
     */
}