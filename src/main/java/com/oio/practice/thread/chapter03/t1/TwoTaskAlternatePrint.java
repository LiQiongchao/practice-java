package com.oio.practice.thread.chapter03.t1;

import java.time.Duration;
import java.time.Instant;

/**
 * 2个线程组交替执行两个任务
 * 感觉多个线程的优势无法发挥，大多数都是在等待，每次只有一个线程在执行。
 * @author Liqc
 * @date 2020/4/2 13:58
 */
public class TwoTaskAlternatePrint {
    public static void main(String[] args) {
        Instant start = Instant.now();
        DBTools dbTools = new DBTools();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> dbTools.backupA()).start();
            new Thread(() -> dbTools.backupB()).start();
        }
        System.out.println("use time: " + Duration.between(start, Instant.now()).toMillis());
    }
}

class DBTools {
    volatile private boolean prevIsA = false;
    synchronized public void backupA() {
        try {
            while (prevIsA) {
                wait();
            }
            System.out.println("task -------------------");
            prevIsA = true;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    synchronized public void backupB() {
        try {
            while (!prevIsA) {
                wait();
            }
            System.out.println("task +++++++++++++++++++");
            prevIsA = false;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
