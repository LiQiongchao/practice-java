package com.oio.practice.thread.chapter03.t2;

/**
 * join方法使用示例
 * @author Liqc
 * @date 2020/4/3 13:38
 */
public class JoinTest {

    public static void main(String[] args) {
        try {
            System.out.println("main 线程执行完了。。 time: " + System.currentTimeMillis());
            Thread thread = new Thread(() -> {
                try {
                    int val = (int) (Math.random() * 10000);
                    System.out.println(val);
                    Thread.sleep(val);
                    System.out.println("子线程执行完了。。 time: " + System.currentTimeMillis());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            thread.join();
            // 如果等待超过1000ms则不再等待子线程
//            thread.join(1000L);
            System.out.println("等子线程执行完后我才会执行。 time:" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    3720
    子线程执行完了。。
    等子线程执行完后我才会执行。
     */

}
