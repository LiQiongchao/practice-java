package com.oio.practice.thread.chapter02.t11.volatil;

import lombok.Data;

/**
 * @author Liqc
 * @date 2020/1/8 13:17
 */
public class PreVolatileStopTest {
    public static void main(String[] args) throws InterruptedException {
        StopPrint thread = new StopPrint();
        thread.start();
        Thread.sleep(10);
        thread.setRunning(false);
        System.out.println("已经赋值为停止");
    }
    /*
    start running!
    stop running

     */
}
@Data
class StopPrint extends Thread {

    private boolean isRunning = true;

    @Override
    public void run() {
        super.run();
        System.out.println("start running!");
        while (true == isRunning) {
            System.out.println("running!");
        }
        System.out.println("stop running, 线程被停止了");
    }
}
