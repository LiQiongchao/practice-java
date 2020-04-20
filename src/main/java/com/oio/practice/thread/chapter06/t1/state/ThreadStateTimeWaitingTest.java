package com.oio.practice.thread.chapter06.t1.state;

/**
 * 测试线程状态 TIME_WAITING
 * @author Liqc
 * @date 2020/4/20 13:33
 */
public class ThreadStateTimeWaitingTest {
    public static void main(String[] args) {
        try {
            ThreadStateTimeWaitingService service = new ThreadStateTimeWaitingService();
            service.start();
            Thread.sleep(500);
            System.out.println("main方法中的状态：" + service.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    begin sleep
    main方法中的状态：TIMED_WAITING
      end sleep
     */

}

class ThreadStateTimeWaitingService extends Thread {

    @Override
    public void run() {
        try {
            System.out.println("begin sleep");
            Thread.sleep(1000);
            System.out.println("  end sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
