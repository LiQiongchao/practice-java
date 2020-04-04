package com.oio.practice.thread.chapter03.t2;

/**
 * join(long)方法不占用线程锁，而sleep(long)方法会占用锁，因为join内部是用wait方法实现的。
 * @author Liqc
 * @date 2020/4/4 12:49
 */
public class JoinCompareSleep {

    public static void main(String[] args) throws InterruptedException {
        try {
            ThreadB tb = new ThreadB();
        /*Thread ta = new Thread(() -> {
            try {
                synchronized (tb) {
                    tb.start();
                    // sleep会占用当前的锁tb
                    Thread.sleep(6000L);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            *//* sleep(long)
            tb run begin timer=1585977094840
            tb run end timer=1585977099840
            print bService timer=1585977100841
             *//*
        });*/
            Thread ta = new Thread(() -> {
                try {
                    synchronized (tb) {
                        tb.start();
                        // sleep会占用当前的锁tb
                        tb.join(6000L);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            /* join(long)
            tb run begin timer=1585977391308
            print bService timer=1585977392308
            tb run end timer=1585977396308
             */
            });
            ta.start();
            Thread.sleep(1000L);
            Thread tc = new Thread(() -> {
                tb.bService();
            });
            tc.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class ThreadB extends Thread {
    @Override
    public void run() {
        {
            try {
                System.out.println("tb run begin timer=" + System.currentTimeMillis());
                Thread.sleep(5000);
                System.out.println("tb run end timer=" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    synchronized public void bService() {
        System.out.println("print bService timer=" + System.currentTimeMillis());
    }
}
