package com.oio.practice.thread.chapter03.t1;

import com.sun.beans.decoder.ValueObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 当消费者比较多时，要判断资源是否充足，否则会异常
 * @author Liqc
 * @date 2020/3/31 13:02
 */
public class WaitOld {

    static String lock ="";

    private static List list = new ArrayList(1);

    private static Runnable runnable = () -> {
        try {
            synchronized (lock) {
                // 判断如果待消费的资源不够就一直等待，要在同步锁内进行。用if判断会有问题，notifyAll()时会有一个IndexOutOfArr
//                if (list.size() == 0) {
                while (list.size() == 0) {
                    System.out.println("wait begin T-name:" + Thread.currentThread().getName());
                    lock.wait();
                    System.out.println("wait end T-name:" + Thread.currentThread().getName());
                }
                // 消费
                list.remove(0);
                System.out.println("list size = " + list.size());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    public static void main(String[] args) {
        new Thread( runnable, "subtract").start();
        new Thread( runnable, "subtract").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            synchronized (lock) {
                list.add("anyString...");
                lock.notifyAll();
            }}, "add").start();
    }
    /*
    wait begin T-name:subtract
    wait begin T-name:subtract
    wait end T-name:subtract
    list size = 0
    wait end T-name:subtract
    wait begin T-name:subtract
     */
}
