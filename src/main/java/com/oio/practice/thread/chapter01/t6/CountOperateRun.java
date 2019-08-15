package com.oio.practice.thread.chapter01.t6;

/**
 * @author Liqc
 * @date 2019/8/15 13:20
 */
public class CountOperateRun {
    public static void main(String[] args) {
        CountOperate countOperate = new CountOperate();
        Thread thread = new Thread(countOperate);
        // 此处设置的是 thread对象的name，不是countOperate对象的name
        thread.setName("A");
        thread.start();
        /*
        count operate begin -------
        Thread.currentThread().getName() = main
        this.getName() = Thread-0
        count operate end -------
        run begin -------
        Thread.currentThread().getName() = A
        this.getName() = Thread-0
        run end -------
         */
    }

}

class CountOperate extends Thread {

    public CountOperate() {
        System.out.println("count operate begin -------");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("this.getName() = " + this.getName());
        System.out.println("count operate end -------");
    }

    @Override
    public void run() {
        super.run();
        System.out.println("run begin -------");
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("this.getName() = " + this.getName());
        System.out.println("run end -------");
    }
}
