package com.oio.practice.thread.chapter01.t7;

/**
 * Thread.currentThread.isAlive() 与 this.isAlive的区别
 * @author Liqc
 * @date 2019/8/21 14:02
 */
public class ThreadIsAlive2 {

    public static void main(String[] args) {
        AliveThread2 aliveThread = new AliveThread2();
        Thread thread = new Thread(aliveThread);
        System.out.println("main thread begin == " + aliveThread.isAlive());
        thread.setName("A");
        thread.start();
        System.out.println("main thread end == " + aliveThread.isAlive());
        /*
        AliveThread2 begin ---
        Thread.currentThread().getName() --- main
        Thread.currentThread().isAlive() --- true
        this.getName() --- Thread-0
        this.isAlive() --- false
        AliveThread2 end ---
        main thread begin == false
        main thread end == false
        run begin ----
        Thread.currentThread().getName() --- A
        Thread.currentThread().isAlive() --- true
        this.getName() --- Thread-0
        this.isAlive() --- false
        run end ----
         */
    }

}

class AliveThread2 extends Thread {

    public AliveThread2() {
        System.out.println("AliveThread2 begin --- ");
        System.out.println("Thread.currentThread().getName() --- " + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive() --- " + Thread.currentThread().isAlive());
        System.out.println("this.getName() --- " + this.getName());
        System.out.println("this.isAlive() --- " + this.isAlive());
        System.out.println("AliveThread2 end --- ");
    }

    @Override
    public void run() {
        System.out.println("run begin ----");
        System.out.println("Thread.currentThread().getName() --- " + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive() --- " + Thread.currentThread().isAlive());
        System.out.println("this.getName() --- " + this.getName());
        System.out.println("this.isAlive() --- " + this.isAlive());
        System.out.println("run end ----");
    }
}
