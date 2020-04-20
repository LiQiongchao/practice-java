package com.oio.practice.thread.chapter06;

/**
 * 测试线程状态 BLOCKED
 * @author Liqc
 * @date 2020/4/20 13:33
 */
public class ThreadStateBlockTest {
    public static void main(String[] args) {
        try {
            ThreadStateBlockService service = new ThreadStateBlockService();
            new Thread(() -> service.service()).start();
            Thread thread = new Thread(() -> service.service());
            thread.start();
            Thread.sleep(500);
            System.out.println("main方法中的状态：" + thread.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    Thread-0begin sleep
    main方法中的状态：BLOCKED
    Thread-0  end sleep
    Thread-1begin sleep
    Thread-1  end sleep
     */

}

class ThreadStateBlockService {

    synchronized public void service() {
        try {
            System.out.println(Thread.currentThread().getName() +"begin sleep");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() +"  end sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
