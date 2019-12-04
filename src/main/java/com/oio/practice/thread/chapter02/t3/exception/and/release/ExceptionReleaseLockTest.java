package com.oio.practice.thread.chapter02.t3.exception.and.release;

/**
 * 在同步方法中出现异常后会释放锁，然后其它线程获取对象锁正常执行
 * @author Liqc
 * @date 2019/12/4 13:53
 */
public class ExceptionReleaseLockTest {
    public static void main(String[] args) {
        try {
            ExceptionService service = new ExceptionService();
            Thread threadA = new Thread(() -> service.exceptionMethod());
            Thread threadB = new Thread(() -> service.exceptionMethod());
            threadA.setName("a");
            threadA.start();
            Thread.sleep(500);
            threadB.setName("b");
            threadB.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    ThreadName=a run beginTime=1575439244257
    ThreadName=a run beginTime=1575439244444
    Exception in thread "a" java.lang.NumberFormatException: For input string: "a"
        at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
        at java.lang.Integer.parseInt(Integer.java:580)
        at java.lang.Integer.parseInt(Integer.java:615)
        at com.oio.practice.thread.chapter02.t3.exception.and.release.ExceptionService.exceptionMethod(ExceptionReleaseLockTest.java:33)
        at com.oio.practice.thread.chapter02.t3.exception.and.release.ExceptionReleaseLockTest.lambda$main$0(ExceptionReleaseLockTest.java:12)
        at java.lang.Thread.run(Thread.java:745)
    Thread B run beginTime=1575439244757
     */
}
class ExceptionService {
    synchronized public void exceptionMethod() {
        if ("a".equals(Thread.currentThread().getName())) {
            System.out.println("ThreadName=" + Thread.currentThread().getName() + " run beginTime="
                    + System.currentTimeMillis());
            while (true) {
                if (("" + Math.random()).substring(0, 8).equals("0.123456")) {
                    System.out.println("ThreadName=" + Thread.currentThread().getName() + " run beginTime="
                            + System.currentTimeMillis());
                    Integer.parseInt("a");
                }
            }
        } else {
            System.out.println("Thread B run beginTime="
                    + System.currentTimeMillis());
        }
    }
}