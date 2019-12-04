package com.oio.practice.thread.chapter02.t2.synchronize;

/**
 * synchronized 加方法锁与不加锁对比，方法加上synchronized后，线程要排队进入
 * @author Liqc
 * @date 2019/12/4 12:19
 */
public class LockMethodTest {
    public static void main(String[] args) {
        ViewObject viewObject = new ViewObject();
        Thread thread = new Thread(() -> {
            viewObject.printName();
        });
        ViewObject viewObject1 = new ViewObject();
        Thread thread1 = new Thread(() -> {
            viewObject.printName();
        });
        thread.setName("A");
        thread.start();
        thread1.setName("B");
        thread1.start();
    }
    /*
    printName()不加synchronized的结果, 两个线程同时进行
        begin printName threadName=A
        begin printName threadName=B
        A end
        B end
    printName() 加synchronized， 两个线程排队进行
        begin printName threadName=A
        A end
        begin printName threadName=B
        B end
     */
}

class ViewObject extends Thread {
    synchronized public void printName() {
        try {
            System.out.println("begin printName threadName=" + Thread.currentThread().getName());
            sleep(2000);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
