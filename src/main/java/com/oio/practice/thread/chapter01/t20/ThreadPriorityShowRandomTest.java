package com.oio.practice.thread.chapter01.t20;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

/**
 * 线程优先级具有随机性
 *  当线程优先级差距不大的时候，往往不能明显的体现出来执行的先后快慢。
 *  把两个线程的优先级改成5和6之后会发现，执行结果的顺序并不明显
 *  所以不能把优先级与运行结果顺序作为衡量的标准。他们的关系具有不确定性与随机性。
 * @author Liqc
 * @date 2019/11/28 13:09
 */
public class ThreadPriorityShowRandomTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread t1 = new Thread(() -> {
                Instant st = Instant.now();
                for (int j = 0; j < 1000; j++) {
                    Random random = new Random();
                    random.nextInt();
                }
                System.out.println("-------------- thread1 use time = " + Duration.between(st, Instant.now()).toMillis());
            });
            t1.setPriority(5);
            t1.start();
            Thread t2 = new Thread(() -> {
                Instant st = Instant.now();
                for (int j = 0; j < 1000; j++) {
                    Random random = new Random();
                    random.nextInt();
                }
                System.out.println("||||||||||||||| thread2 use time = " + Duration.between(st, Instant.now()).toMillis());
            });
            t2.setPriority(6);
            t2.start();
        }
    }
    /*
    ||||||||||||||| thread2 use time = 3
    ||||||||||||||| thread2 use time = 3
    ||||||||||||||| thread2 use time = 2
    -------------- thread1 use time = 3
    -------------- thread1 use time = 4
    -------------- thread1 use time = 3
    ||||||||||||||| thread2 use time = 3
    ||||||||||||||| thread2 use time = 2
    -------------- thread1 use time = 3
    -------------- thread1 use time = 2
     */
}
