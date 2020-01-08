package com.oio.practice.thread.chapter02.t11.volatil;

import lombok.Data;

/**
 * 可以通过异步线程解决
 * @author Liqc
 * @date 2020/1/8 13:07
 */
public class VolatilePreTest {
    public static void main(String[] args) throws InterruptedException {
        PrintString printString = new PrintString();
        Thread thread = new Thread(printString);
        thread.start();
        Thread.sleep(2000);
        System.out.println("我要停止它！stopThread=" + Thread.currentThread().getName());
        printString.setConnectPrint(false);
    }
    /*
    run printString method threadName:Thread-0
    run printString method threadName:Thread-0
    我要停止它！stopThread=main
     */
}
@Data
class PrintString implements Runnable {

    private boolean isConnectPrint = true;

    public void printString() {
        try {
            while (true == isConnectPrint) {
                System.out.println("run printString method threadName:" + Thread.currentThread().getName());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        printString();
    }
}
