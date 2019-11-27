package com.oio.practice.thread.chapter01.t15.suspend;

import lombok.Data;

/**
 * 线程的暂停与恢复
 * @author Liqc
 * @date 2019/11/27 13:16
 */
public class SuspendResumeTest {

    public static void main(String[] args) {
        try {
            SuspendResumeRun run = new SuspendResumeRun();
            Thread suspendResumeRun = new Thread(run);
            suspendResumeRun.start();
            Thread.sleep(3000);
            // A段
            suspendResumeRun.suspend();
            System.out.println("A = " + System.currentTimeMillis() + ", i = " + run.getI());
            Thread.sleep(3000);
            System.out.println("A = " + System.currentTimeMillis() + ", i = " + run.getI());
            // B段
            suspendResumeRun.resume();
            Thread.sleep(3000);
            // A段
            suspendResumeRun.suspend();
            System.out.println("B = " + System.currentTimeMillis() + ", i = " + run.getI());
            Thread.sleep(3000);
            System.out.println("B = " + System.currentTimeMillis() + ", i = " + run.getI());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    A = 1574832339496, i = 1934026408
    A = 1574832342497, i = 1934026408
    B = 1574832345498, i = 3881729446
    B = 1574832348498, i = 3881729446
     */

}

@Data
class SuspendResumeRun implements Runnable {
    private long i = 0;

    @Override
    public void run() {
        while (true) {
            i++;
        }
    }
}
