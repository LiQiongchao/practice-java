package com.oio.practice.thread.chapter01.t11.stop;

public class PrintThread extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 1; i <= 50000; i++) {
            System.out.println("i = " + i);
        }
    }
}