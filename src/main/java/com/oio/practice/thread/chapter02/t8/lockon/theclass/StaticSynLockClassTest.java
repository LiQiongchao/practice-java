package com.oio.practice.thread.chapter02.t8.lockon.theclass;

import org.apache.catalina.core.StandardServer;

/**
 * 静态方法加同步锁 - 类(class)锁
 * 静态方法的锁相当于synchronized(Class),如：synchronized(StaticSynService.class) 锁的是所有的对象的实例。
 * 非静态方法的锁相当于synchronized(this), 锁的是单个实例对象。
 * @author Liqc
 * @date 2019/12/30 13:05
 */
public class StaticSynLockClassTest {
    public static void main(String[] args) {
        StaticSynService service = new StaticSynService();
        Thread threadA = new Thread(() -> StaticSynService.printA());
        Thread threadB = new Thread(() -> StaticSynService.printB());
        Thread threadC = new Thread(() -> service.printC());
        threadA.setName("A");
        threadA.start();
        threadB.setName("B");
        threadB.start();
        threadC.setName("c");
        threadC.start();
    }
    /*
    time=1577682782800 run ThreadName=A in printA
    time=1577682782800 run ThreadName=c in printC
    time=1577682782800 run ThreadName=c out printC
    time=1577682784801 run ThreadName=A out printA
    time=1577682784801 run ThreadName=B in printB
    time=1577682784801 run ThreadName=B out printB
     */
}
class StaticSynService {

    synchronized public static void printA() {
        try {
            System.out.println("time=" + System.currentTimeMillis() + " run ThreadName="
                    + Thread.currentThread().getName() + " in printA");
            Thread.sleep(2000);
            System.out.println("time=" + System.currentTimeMillis() + " run ThreadName="
                    + Thread.currentThread().getName() + " out printA");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public static void printB() {
        try {
            System.out.println("time=" + System.currentTimeMillis() + " run ThreadName="
                    + Thread.currentThread().getName() + " in printB");
            System.out.println("time=" + System.currentTimeMillis() + " run ThreadName="
                    + Thread.currentThread().getName() + " out printB");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    synchronized public void printC() {
        try {
            System.out.println("time=" + System.currentTimeMillis() + " run ThreadName="
                    + Thread.currentThread().getName() + " in printC");
            System.out.println("time=" + System.currentTimeMillis() + " run ThreadName="
                    + Thread.currentThread().getName() + " out printC");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
