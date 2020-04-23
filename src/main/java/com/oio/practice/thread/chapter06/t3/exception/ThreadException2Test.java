package com.oio.practice.thread.chapter06.t3.exception;

/**
 * 线程的异常处理
 * 为整个线程对象设置异常处理。
 * @Author: LiQiongchao
 * @Date: 2020/4/23 13:17
 */
public class ThreadException2Test {

    public static void main(String[] args) {
        ThreadExcService.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程：" + t.getName() + ", 异常了。");
                System.out.println(e.getMessage());
            }
        });

        ThreadExcService service = new ThreadExcService();
        service.start();
        ThreadExcService service2 = new ThreadExcService();
        service2.start();
    }
    /*
    线程：Thread-0, 异常了。
    线程：Thread-1, 异常了。
    / by zero
    / by zero
     */
}

class ThreadExcService extends Thread {
    @Override
    public void run() {
        System.out.println(1/0);
    }

}
