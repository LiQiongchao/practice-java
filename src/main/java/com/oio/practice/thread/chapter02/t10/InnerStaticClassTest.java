package com.oio.practice.thread.chapter02.t10;

import lombok.Data;

/**
 * 类与内部静态类的默认锁不是同一个锁，T1,T3会相互阻塞，T2不受影响
 *
 * @author Liqc
 * @date 2020/1/6 13:05
 */
public class InnerStaticClassTest {

    public static void main(String[] args) {
        OutClass.InnerClassA innerClassA = new OutClass.InnerClassA();
        OutClass.InnerClassB innerClassB = new OutClass.InnerClassB();
        new Thread(() -> innerClassA.methodA(innerClassB), "T1").start();
        new Thread(() -> innerClassA.methodB(), "T2").start();
        new Thread(() -> innerClassB.methodA(), "T3").start();
    }
    /* T1,T3会同步进行，T2不受影响
    i=8
    j=8
    i=9
    j=9
    T1离开InnerClassA -> methodA方法
    T3进入InnerClassB -> methodA方法
    k=0
    T2离开InnerClassA -> methodB方法
    k=1
     */
}

@Data
class OutClass {

    static class InnerClassA {
        public void methodA(InnerClassB classB) {
            String name = Thread.currentThread().getName();
            synchronized (classB) {
                System.out.println(name + "进入InnerClassA -> methodA方法");
                for (int i = 0; i < 10; i++) {
                    System.out.println("i=" + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name + "离开InnerClassA -> methodA方法");
            }
        }
        public synchronized void methodB() {
            String name = Thread.currentThread().getName();
            System.out.println(name + "进入InnerClassA -> methodB方法");
            for (int i = 0; i < 10; i++) {
                System.out.println("j=" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + "离开InnerClassA -> methodB方法");
        }
    }

    static class InnerClassB {
        public synchronized void methodA() {
            String name = Thread.currentThread().getName();
            System.out.println(name + "进入InnerClassB -> methodA方法");
            for (int i = 0; i < 10; i++) {
                System.out.println("k=" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + "离开InnerClassB -> methodA方法");
        }
    }
}
