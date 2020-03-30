package com.oio.practice.thread.chapter01.t17.yield;

import java.time.Duration;
import java.time.Instant;

/**
 * 测试yield()让步CPU资源
 * @author Liqc
 * @date 2019/11/27 19:03
 */
public class YieldTest {
    public static void main(String[] args) {
        YieldThread thread = new YieldThread();
        thread.start();
    }
    /*
    // Thread.yield()
    使用：19ms, sum:1283106753

    Thread.yield()
    使用：15082ms, sum:1283106753
     */
}

class YieldThread extends Thread {
    @Override
    public void run() {
        Instant start = Instant.now();
        int sum = 1;
        int count = 0;
        for (int i = 0; i < 50000000; i++) {
            count += 1; // 50000000
            Thread.yield();
            sum += i;
        }
        Instant end = Instant.now();
        System.out.println("使用：" + (Duration.between(start, end).toMillis()) + "ms, sum:" + sum);
    }
}
