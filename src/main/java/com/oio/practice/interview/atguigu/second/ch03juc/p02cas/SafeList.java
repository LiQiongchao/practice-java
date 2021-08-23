package com.oio.practice.interview.atguigu.second.ch03juc.p02cas;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayList
 *  size: 9999, use time: 1442ms 在多线程下不安全
 * Vector
 *  size: 10000, use time: 1472ms 在多线程下安全，性能下降，使用的是synchronized方法
 * synchronizedList(List<T> list)
 *  size: 10000, use time: 1454ms JDK提供的线程安全包装方法，使用的是synchronized代码块包装
 * CopyOnWriteArrayList
 *  size: 10000, use time: 1458ms, 多线程下安全，在写的时候使用了lock锁，效率高于同步锁
 *
 * @Author: LiQiongchao
 * @Date: 2020/5/19 21:51
 */
public class SafeList {

    public static void main(String[] args) {
        Instant start = Instant.now();
        // 1- 使用ArrayList会有安全问题
//        List<String> list = new ArrayList<>();
        // 2- 使用安全集合类Vector类
//        List<String> list = new Vector<>();
        // 3- 使用Collections.
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        // 4- 使用CopyOnWriteArrayList
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i <10000; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
            }, i + "").start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("size: " + list.size() + ", use time: " + Duration.between(start, Instant.now()).toMillis());
        System.out.println(list);
    }

}
