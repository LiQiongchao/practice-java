package com.oio.practice.thread.chapter01.t12.interrupt;

import com.oio.practice.thread.chapter01.t11.stop.PrintThread;
import org.springframework.context.annotation.Primary;

/**
 * @author Liqc
 * @date 2019/9/18 13:19
 */
public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        PrintThread printThread = new PrintThread();
        printThread.start();
        Thread.sleep(200);
        printThread.interrupt();
        System.out.println("是否停止线程1？" + PrintThread.interrupted());
        System.out.println("是否停止线程2？" + PrintThread.interrupted());
        // 两个都是false
    }
}

class InterruptedTest {
    public static void main(String[] args) {
        /*
        interrupted()是static修饰的方法，底层调用的是isInterrupted()
         */
        Thread.currentThread().interrupt();
        System.out.println("是否停止线程1？" + Thread.interrupted());
        System.out.println("是否停止线程2？" + Thread.interrupted());
        System.out.println("end");
        /*
        是否停止线程1？true
        是否停止线程2？false
        end

        第一次打印true，并清除当前标记
         */
    }
}

class IsInterruptedTest {
    public static void main(String[] args) throws InterruptedException {
        PrintThread printThread = new PrintThread();
        printThread.start();
        Thread.sleep(200);
        printThread.interrupt();
        System.out.println("是否停止线程1？" + printThread.isInterrupted());
        System.out.println("是否停止线程2？" + printThread.isInterrupted());
        /*
         i = 38006
        是否停止线程1？true
        i = 38007
        ……
        i = 38025
        是否停止线程2？true
         */
    }
}


