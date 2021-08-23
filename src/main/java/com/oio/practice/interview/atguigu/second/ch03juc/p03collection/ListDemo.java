package com.oio.practice.interview.atguigu.second.ch03juc.p03collection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ArrayList演示线程不安全问题
 * @author Liqc
 * @date 2020/5/18 13:22
 */
public class ListDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 30; i++) {
          new Thread(() -> {
              list.add(UUID.randomUUID().toString().substring(0, 8));
              System.out.println(list);
          }).start();
        }
    }

}
