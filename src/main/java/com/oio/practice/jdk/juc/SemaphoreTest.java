package com.oio.practice.jdk.juc;

import java.util.concurrent.Semaphore;

/**
 * 信号量 Semaphore 测试
 * 同时只能有3个人去访问服务器
 *
 * @Author: LiQiongchao
 * @Date: 2020/4/27 12:55
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            final int no = i;
            new Thread(() -> {
               try {
                   System.out.println("用户 " + no + " 连接上了");
                   Thread.sleep(300L);
                   // 获取执行许可，没有许可就阻塞
                   semaphore.acquire();
                   System.out.println("用户 " + no + " 开始访问后台");
                   // 模仿访问后台服务
                   Thread.sleep(1000L);
                   semaphore.release();
                   System.out.println("用户 " + no + " 访问结束");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
            }).start();
        }
        System.out.println("Main thread end!");
    }
    /* 同时只会有3个去访问后台资源
    ...
    用户 6 访问结束
    用户 9 开始访问后台
    用户 0 访问结束
    用户 7 访问结束
    用户 9 访问结束
     */
}
