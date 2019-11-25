package com.oio.practice.thread.chapter01.t14.user.stop.method;

/**
 * 使用stop方法停止线程
 * @author Liqc
 * @date 2019/11/25 13:06
 */
public class UserStopMethodTest {

    public static void main(String[] args) {
        try {
            ViolenceStop violenceStop = new ViolenceStop();
            violenceStop.start();
            Thread.sleep(8000);
            violenceStop.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    i = 1
    i = 2
    i = 3
    i = 4
    i = 5
    i = 6
    i = 7
    i = 8
     */

}

class ViolenceStop extends Thread {
    private int i = 0;
    @Override
    public void run() {
        try {
            while (true)  {
                System.out.println("i = " + ++i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
