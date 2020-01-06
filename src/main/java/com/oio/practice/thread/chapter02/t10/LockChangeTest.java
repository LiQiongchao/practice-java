package com.oio.practice.thread.chapter02.t10;

/**
 * 锁对象的改变, 当锁对象没有改变时会阻塞其它线程，当改变后则不会阻塞其它线程
 * @author Liqc
 * @date 2020/1/6 13:28
 */
public class LockChangeTest {
    public static void main(String[] args) {
        try {
            LockChangeService service = new LockChangeService();
            Thread a = new Thread(() -> service.testMethod(), "A");
            Thread b = new Thread(() -> service.testMethod(), "B");
//            Thread.sleep(50);
            a.start();
            b.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    Thread.sleep(50);
    A begin 1578288870363
    B begin 1578288870413
    A end 1578288871364
    B end 1578288871415

    注释掉 Thread.sleep(50);
    A begin 1578289006030
    A end 1578289007031
    B begin 1578289007031
    B end 1578289008032
     */
}
class LockChangeService {
    private String lock = "123";
    public void testMethod() {
        try {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " begin " + System.currentTimeMillis());
                lock = "456";
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " end " + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
