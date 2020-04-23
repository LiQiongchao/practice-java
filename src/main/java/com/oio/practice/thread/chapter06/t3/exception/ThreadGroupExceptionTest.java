package com.oio.practice.thread.chapter06.t3.exception;

/**
 * 线程组异常的处理
 * @Author: LiQiongchao
 * @Date: 2020/4/23 13:39
 */
public class ThreadGroupExceptionTest {

    public static void main(String[] args) {
        MyThreadGroup group = new MyThreadGroup("哈哈");
        GroupExcService[] serArr = new GroupExcService[10];
        for (int i = 0; i < serArr.length; i++) {
            serArr[i] = new GroupExcService(group, "线程" + (i+1), "1");
            serArr[i].start();
        }
        new GroupExcService(group, "线程报错", "q").start();
    }
/*
死循环中：线程1
Exception in thread "线程报错" 死循环中：线程1
死循环中：线程1
死循环中：线程1
死循环中：线程1
死循环中：线程5
死循环中：线程5
死循环中：线程1
死循环中：线程1
死循环中：线程1
死循环中：线程1
死循环中：线程1
死循环中：线程1
死循环中：线程10
java.lang.NumberFormatException: For input string: "q"
死循环中：线程10
死循环中：线程10
死循环中：线程10
	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
	at java.lang.Integer.parseInt(Integer.java:580)死循环中：线程10

死循环中：线程10
	at java.lang.Integer.parseInt(Integer.java:615)
死循环中：线程10
	at com.oio.practice.thread.chapter06.t3.exception.GroupExcService.run(ThreadGroupExceptionTest.java:32)
死循环中：线程10
死循环中：线程2
死循环中：线程10
 */
}

class GroupExcService extends Thread {
    private String num;

    public GroupExcService(ThreadGroup group, String name, String num) {
        super(group, name);
        this.num = num;
    }

    @Override
    public void run() {
        int intNum = Integer.parseInt(num);
        while (!this.isInterrupted()) {
            System.out.println("死循环中：" + Thread.currentThread().getName());
        }
    }
}

class MyThreadGroup extends ThreadGroup {

    public MyThreadGroup(String name) {
        super(name);
    }

    public MyThreadGroup(ThreadGroup parent, String name) {
        super(parent, name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        super.uncaughtException(t, e);
        // t为出现异常的线程对象，在run内部不能catch语句，否则异常无法抛出。
        this.interrupt();
    }
}
