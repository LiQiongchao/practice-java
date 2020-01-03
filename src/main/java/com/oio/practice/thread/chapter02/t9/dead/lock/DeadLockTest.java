package com.oio.practice.thread.chapter02.t9.dead.lock;

/**
 * 死锁演示
 * 死锁检测：
 *      - jdk自带jps查看应用的pid
 *      - 使用jdk自带jstack -l pid 检测是否有死锁现象。
 * @author Liqc
 * @date 2019/12/31 13:05
 */
public class DeadLockTest {

    public static void main(String[] args) {
        try {
            DeadThread t1 = new DeadThread();
            t1.setFlag("a");
            new Thread(t1).start();
            Thread.sleep(100);
            t1.setFlag("b");
            new Thread(t1).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
        两个线程一起在等待对象释放锁
        username=a
        username=b

         */
    }
}

class DeadThread implements Runnable {

    public String username;
    public Object lock1 = new Object();
    public Object lock2 = new Object();
    public void setFlag(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        if ("a".equals(username)) {
            synchronized (lock1) {
                try {
                    System.out.println("username=" + username);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("按lock1 -> lock2代码顺序执行了");
                }
            }
        }
        if ("b".equals(username)) {
            synchronized (lock2) {
                try {
                    System.out.println("username=" + username);
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("按lock2 -> lock1代码顺序执行了");
                }
            }
        }
    }

}
