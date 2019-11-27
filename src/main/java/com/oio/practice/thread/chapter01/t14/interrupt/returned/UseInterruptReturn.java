package com.oio.practice.thread.chapter01.t14.interrupt.returned;

import java.time.Instant;

/**
 * 使用interrupt()与return停止线程
 * @author Liqc
 * @date 2019/11/27 13:02
 */
public class UseInterruptReturn {
    public static void main(String[] args) {
        try {
            InterruptReturnRun run = new InterruptReturnRun();
            run.start();
            Thread.sleep(500);
            run.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    ……
    timer = 2019-11-27T05:06:23.878Z
    timer = 2019-11-27T05:06:23.879Z
    the thread is stop.
     */
}

class InterruptReturnRun extends Thread {
    @Override
    public void run() {
        while (true) {
            if (this.isInterrupted()) {
                System.out.println("the thread is stop.");
                return;
            }
            System.out.println("timer = " + Instant.now());
        }
    }
}
