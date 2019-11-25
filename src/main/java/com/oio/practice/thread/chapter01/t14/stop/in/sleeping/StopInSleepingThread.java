package com.oio.practice.thread.chapter01.t14.stop.in.sleeping;

/**
 * 测试线程在沉睡中消亡
 * 在进入睡眠后，执行interrupt()方法
 * @author Liqc
 * @date 2019/11/25 12:49
 */
public class StopInSleepingThread {

    public static void main(String[] args) {
        try {
            StopInSleeping stopInSleeping = new StopInSleeping();
            stopInSleeping.start();
            Thread.sleep(2000);
            stopInSleeping.interrupt();
        } catch (InterruptedException e) {
            System.out.println("main catch");
            e.printStackTrace();
        }
        System.out.println("main end");
        /*
        run begin
        main end
        线程在沉睡中被停止，进行catch! false
        java.lang.InterruptedException: sleep interrupted
            at java.lang.Thread.sleep(Native Method)
            at com.oio.practice.thread.chapter01.t14.stop.in.sleeping.StopInSleeping.run(StopInSleepingThread.java:31)
         */
    }

}

class StopInSleeping extends Thread {

    @Override
    public void run() {
        super.run();
        try {
            System.out.println("run begin");
            Thread.sleep(20000);
            System.out.println("run end");
        } catch (InterruptedException e) {
            System.out.println("线程在沉睡中被停止，进行catch! " + this.isInterrupted());
            e.printStackTrace();
        }
    }
}



