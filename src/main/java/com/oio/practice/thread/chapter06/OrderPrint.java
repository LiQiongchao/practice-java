package com.oio.practice.thread.chapter06;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 3个线程顺序打印
 * 有问题，noticeAll后一直不释放锁，会导致wait的线程一直无法唤醒去继续执行。
 * @author Liqc
 * @date 2020/4/20 16:53
 */
public class OrderPrint {

    public static void main(String[] args) {
        OrderPrint orderPrint = new OrderPrint();
        Service a = new Service("A", 1);
        Service b = new Service("B", 2);
        Service c = new Service("C", 0);
        a.start();
        b.start();
        c.start();
    }

}

@NoArgsConstructor
class Service  extends Thread{

    // 锁
    private Object lock = new Object();

    // 打印的信息
    String showString;
    // 用以计算哪个线程打印
    int showNumPosition;

    // 控制打印几个循环
    int printCount = 0;

    static volatile int addNumber = 1;

    public Service(String showString, int showNumPosition) {
        this.showString = showString;
        this.showNumPosition = showNumPosition;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
                while (true) {
                    if (addNumber % 3 == showNumPosition) {
                        System.out.println("ThreadName=" + Thread.currentThread().getName() + ", runCount=" + addNumber + ", " + showString);
                        lock.notifyAll();
                        addNumber++;
                        printCount++;
                        if (printCount == 3) {
                            break;
                        }
                    } else {
                        lock.wait();
                    }

                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
