package com.oio.practice.thread.chapter06.t3.exception;

/**
 * 线程的异常处理
 * @Author: LiQiongchao
 * @Date: 2020/4/23 13:17
 */
public class ThreadExceptionTest {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {int a =1/0;});
        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("线程：" + t.getName() + ", 异常了。");
                System.out.println(e.getMessage());
            }
        });
        t.start();
        Thread t2 = new Thread(() -> {int a =1/0;});
        t2.start();
    }

    /*
    线程：Thread-0, 异常了。
    / by zero
    Exception in thread "Thread-1" java.lang.ArithmeticException: / by zero
        at com.oio.practice.thread.chapter06.t3.exception.ThreadExceptionTest.lambda$main$1(ThreadExceptionTest.java:20)
        at java.lang.Thread.run(Thread.java:745)
     */
}
