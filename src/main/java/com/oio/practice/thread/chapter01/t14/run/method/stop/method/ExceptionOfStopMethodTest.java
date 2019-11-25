package com.oio.practice.thread.chapter01.t14.run.method.stop.method;

/**
 * 查看stop()抛出的java.lang.ThreadDeath异常，通常不用显示捕捉
 * @author Liqc
 * @date 2019/11/25 13:13
 */
public class ExceptionOfStopMethodTest {
    public static void main(String[] args) {
        ExceptionOfStopMethod exceptionOfStopMethod = new ExceptionOfStopMethod();
        exceptionOfStopMethod.start();
    }
    /*
    stop()后，进行catch
    java.lang.ThreadDeath
        at java.lang.Thread.stop(Thread.java:850)
        at com.oio.practice.thread.chapter01.t14.run.method.stop.method.ExceptionOfStopMethod.run(ExceptionOfStopMethodTest.java:20)
     */

}

class ExceptionOfStopMethod extends Thread {
    @Override
    public void run() {
        try {
            this.stop();
        } catch (ThreadDeath e) {
            System.out.println("stop()后，进行catch");
            e.printStackTrace();
        }
    }
}