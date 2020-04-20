package com.oio.practice.thread.chapter06.t1.state;

/**
 * 测试线程状态 TIME_WAITING
 * @author Liqc
 * @date 2020/4/20 13:33
 */
public class ThreadStateWaitingTest {

    public static void main(String[] args) {
        try {
            Service service = new ThreadStateWaitingTest().new Service();
            Thread thread = new Thread(() -> service.server());
            thread.start();

            Thread.sleep(500);
            System.out.println("main方法中的状态：" + thread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    begin sleep
    main方法中的状态：WAITING
     */


    class Service {

        synchronized public void server() {
            try {
                System.out.println("begin sleep");
                wait();
                System.out.println("  end sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

