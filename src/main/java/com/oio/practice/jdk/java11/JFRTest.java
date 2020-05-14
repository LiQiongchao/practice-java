package com.oio.practice.jdk.java11;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: LiQiongchao
 * @Date: 2020/5/14 20:17
 */
public class JFRTest {
    public static void main(String[] args) {
        int count = 0;
        List<GarbageJfr> list = new ArrayList<>();
        while (true) {
            list.add(new GarbageJfr());
            if (count++ % 500 == 0) {
                list.clear();
            } try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class GarbageJfr {
    @Override
    protected void finalize() throws Throwable {
        System.out.println(this + " is collection.");
    }
}
