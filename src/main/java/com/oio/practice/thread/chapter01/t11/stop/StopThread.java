package com.oio.practice.thread.chapter01.t11.stop;

import org.springframework.context.annotation.Primary;

/**
 * @author Liqc
 * @date 2019/9/18 13:19
 */
public class StopThread {
    public static void main(String[] args) throws InterruptedException {
        PrintThread printThread = new PrintThread();
        printThread.start();
        Thread.sleep(2000);
        printThread.interrupt();
    }
}



