package com.oio.practice.thread.chapter02.t5.syn.code.block;


/**
 * synchronized方法的弊端
 *      如果加在方法上会使线程在方法上排队，如果使用代码块加在代码块上，会提高系统的处理能力。
 * @author Liqc
 * @date 2019/12/5 13:07
 */
public class SynNotEnoughTest {
    public static void main(String[] args) {
        SynNotEnoughTask task = new SynNotEnoughTask();
        Thread threadA = new Thread(() -> {
            SynNotEnoughUtil.beginTime1 = System.currentTimeMillis();
            task.doLongTimeTask();
            SynNotEnoughUtil.endTime1 = System.currentTimeMillis();
        });
        Thread threadB = new Thread(() -> {
            SynNotEnoughUtil.beginTime2 = System.currentTimeMillis();
            task.doLongTimeTask();
            SynNotEnoughUtil.endTime2 = System.currentTimeMillis();
        });
        threadA.start();
        threadB.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long beginTime = SynNotEnoughUtil.beginTime1 < SynNotEnoughUtil.beginTime2? SynNotEnoughUtil.beginTime1:SynNotEnoughUtil.beginTime2;
        Long endTime = SynNotEnoughUtil.endTime1 > SynNotEnoughUtil.endTime2? SynNotEnoughUtil.endTime1:SynNotEnoughUtil.endTime2;
        System.out.println("使用时间：" + (endTime - beginTime));
    }
    /* 比同步方法，效率提高一倍
    begin task!
    begin task!
    长时间任务获取值1 threadName=Thread-0
    长时间任务获取值2 threadName=Thread-0
    end task
    长时间任务获取值1 threadName=Thread-1
    长时间任务获取值2 threadName=Thread-1
    end task
    使用时间：3001
     */
}
class SynNotEnoughTask {
    public String dataA;
    public String dataB;
    public void doLongTimeTask() {
        try {
            System.out.println("begin task!");
            Thread.sleep(3000);
            synchronized (this) {
                this.dataA = "长时间任务获取值1 threadName=" + Thread.currentThread().getName();
                this.dataB = "长时间任务获取值2 threadName=" + Thread.currentThread().getName();
                System.out.println(dataA);
                System.out.println(dataB);
            }
            System.out.println("end task");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class SynNotEnoughUtil {
    public static long beginTime1;
    public static long endTime1;
    public static long beginTime2;
    public static long endTime2;
}
