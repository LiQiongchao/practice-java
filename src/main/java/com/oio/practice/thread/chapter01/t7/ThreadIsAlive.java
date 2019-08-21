package com.oio.practice.thread.chapter01.t7;

/**
 * isAlive()
 * 判断当前线程是否是存活状态
 * @author Liqc
 * @date 2019/8/21 13:14
 */
public class ThreadIsAlive {

    public static void main(String[] args) throws InterruptedException {
        AliveThread aliveThread = new AliveThread();
        System.out.println("begin == " + aliveThread.isAlive());
        aliveThread.start();
        System.out.println("end == " + aliveThread.isAlive());
        Thread.sleep(10);
        // 到此，线程已经执行完了
        System.out.println("final == " + aliveThread.isAlive());
        /*
        begin == false
        end == true
        run is true
        final == false
         */

    }

}

class AliveThread extends Thread {
    @Override
    public void run() {
        System.out.println("run is " + isAlive());
    }
}
