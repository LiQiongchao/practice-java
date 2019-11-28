package com.oio.practice.thread.chapter01.t19;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

/**
 * 测试线程优先级间的差距
 *  当两个线程的优先级设置成1和10时，执行结果的先后顺序非常明显。
 * 线程的优先级与main中启动顺序无关
 * @author Liqc
 * @date 2019/11/28 13:09
 */
public class ThreadPriorityShowTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread t1 = new Thread(() -> {
                Instant st = Instant.now();
                int res = 0;
                for (int j = 0; j < 50000; j++) {
                    Random random = new Random();
                    random.nextInt();
                    res += j;
                }
                System.out.println("-------------- thread1 use time = " + Duration.between(st, Instant.now()).toMillis());
            });
            t1.setPriority(1);
            t1.start();
            Thread t2 = new Thread(() -> {
                Instant st = Instant.now();
                int res = 0;
                for (int j = 0; j < 50000; j++) {
                    Random random = new Random();
                    random.nextInt();
                    res += j;
                }
                System.out.println("||||||||||||||| thread2 use time = " + Duration.between(st, Instant.now()).toMillis());
            });
            t2.setPriority(10);
            t2.start();
        }
    }
    /*
    执行完的先后顺序与main中启动顺序无关
    ||||||||||||||| thread2 use time = 42
    ||||||||||||||| thread2 use time = 41
    -------------- thread1 use time = 42
    ||||||||||||||| thread2 use time = 49
    ||||||||||||||| thread2 use time = 52
    -------------- thread1 use time = 59
    -------------- thread1 use time = 60
    -------------- thread1 use time = 64
    -------------- thread1 use time = 64
     */
}
