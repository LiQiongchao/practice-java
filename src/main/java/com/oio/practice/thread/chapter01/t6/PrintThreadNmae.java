package com.oio.practice.thread.chapter01.t6;

import com.oio.practice.thread.chapter01.t6.MyThread;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Liqc
 * @date 2019/8/15 13:09
 */
public class PrintThreadNmae {
    public static void main(String[] args) {
//        System.out.println(Thread.currentThread().getName());
        // main
        MyThread myThread = new MyThread();
//        myThread.start();
        /*
        构造方法的thread name:main
        run方法的thread name:Thread-0
         */

        myThread.run();
        /*
        构造方法的thread name:main
        run方法的thread name:main
         */
    }
}

@Slf4j
class MyThread extends Thread {

    public MyThread() {
      log.info("构造方法的thread name:{}", Thread.currentThread().getName());
    }

    @Override
    public void run() {
        super.run();
        log.info("run方法的thread name:{}", Thread.currentThread().getName());

    }
}
