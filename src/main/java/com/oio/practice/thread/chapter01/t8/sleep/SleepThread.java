package com.oio.practice.thread.chapter01.t8.sleep;

/**
 * @author Liqc
 * @date 2019/8/22 13:06
 */
public class SleepThread {
    public static void main(String[] args) {
        MySleepThread mySleepThread = new MySleepThread();
        System.out.println("begin = " + System.currentTimeMillis());
        mySleepThread.start();
        System.out.println("end = " + System.currentTimeMillis());
        /*
        begin = 1566450558728
        end = 1566450558728
        run threadName: Thread-0 begin
        run threadName: Thread-0 end
         */
    }
}

class MySleepThread extends Thread {

    @Override
    public void run() {
        System.out.println("run threadName: " + currentThread().getName() + " begin");
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
        }
        System.out.println("run threadName: " + currentThread().getName() + " end");
    }
}
