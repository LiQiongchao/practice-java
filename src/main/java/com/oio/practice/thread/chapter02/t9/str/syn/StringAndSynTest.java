package com.oio.practice.thread.chapter02.t9.str.syn;

/**
 * 用String类的值作为锁，因为有常量池的存在，会导致后面的线程永远拿不到锁。
 * 解决：
 *      - 使用new String(); 不会使用常量池中的字符串。
 *      - 使用其它对象实例，不缓存对象。
 * @author Liqc
 * @date 2019/12/30 13:22
 */
public class StringAndSynTest {

    public static void main(String[] args) {
//        strTest();
        new Thread(() -> StringAndSynService.print("AA")).start();
        new Thread(() -> StringAndSynService.print("AA")).start();
        /*
        Thread-0
        Thread-0
        Thread-0
        Thread-0
        ……
         */
    }

    public static void strTest() {
        String a = "123";
        String b = "123";
        String c = new String("123");
        // 由于字体串常量池，所以结果为: true
        System.out.println(a == b);
        // false
        System.out.println(c == b);
    }

}

class StringAndSynService {
    public static void print(String str) {
        try {
            synchronized (str) {
                while (true) {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
