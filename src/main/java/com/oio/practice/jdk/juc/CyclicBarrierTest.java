package com.oio.practice.jdk.juc;

import lombok.AllArgsConstructor;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 测试
 * 场景：
 *  计算一个大的任务，然后分拆成3个任务去执行，第一次执行完后再依赖互相的结果，再一起往下执行第二次的运算。
 * @Author: LiQiongchao
 * @Date: 2020/4/27 13:28
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {
        //指定3个parties, 并指定barrierAction
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(3, ()-> System.out.println("所有子任务执行完了，开始执行主任务！，Thread: " + Thread.currentThread().getName()));
        BillTask worker1 = new BillTask("worker1", cyclicBarrier);
        BillTask worker2 = new BillTask("worker2", cyclicBarrier);
        BillTask worker3 = new BillTask("worker3", cyclicBarrier);
        worker1.start();worker2.start();worker3.start();
        System.out.println("Main thread end!");
        /*
        Main thread end!
        市区：worker1运算开始，Thread: Thread-0
        市区：worker2运算开始，Thread: Thread-1
        市区：worker3运算开始，Thread: Thread-2
        市区：worker1运算完成，等待中...，Thread: Thread-0
        市区：worker3运算完成，等待中...，Thread: Thread-2
        市区：worker2运算完成，等待中...，Thread: Thread-1
        所有子任务执行完了，开始执行主任务！，Thread: Thread-1
        全部都结束了，市区：worker2才开始后面的工作。
        全部都结束了，市区：worker3才开始后面的工作。
        全部都结束了，市区：worker1才开始后面的工作。
         */
    }

    @AllArgsConstructor
    static class BillTask extends Thread {
        private String billName;
        private CyclicBarrier barrier;

        @Override
        public void run() {
            try {
                System.out.println("市区：" + billName + "运算开始，Thread: " + Thread.currentThread().getName());
                // 模拟第一次运算
                Thread.sleep(1000L);
                System.out.println("市区：" + billName + "运算完成，等待中...，Thread: " + Thread.currentThread().getName());
                // 第二要依赖上面的运算结果，所以在等大家运算完后再一起往下执行。
                barrier.await();
                System.out.println("全部都结束了，市区：" + billName + "才开始后面的工作。");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
