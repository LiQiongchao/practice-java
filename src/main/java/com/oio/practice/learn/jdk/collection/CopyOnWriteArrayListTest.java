package com.oio.practice.learn.jdk.collection;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 测试CopyOnWriteArrayList
 * @Author: LiQiongchao
 * @Date: 2020/4/25 20:11
 */
public class CopyOnWriteArrayListTest {

    public static void main(String[] args) {
        CopyOnWriteArrayList list = new CopyOnWriteArrayList();
        list.add("one");
        list.add("three");
        list.add("four");
        list.add(1, "two");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    /*
    one
    two
    three
    four
     */
}
