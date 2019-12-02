package com.oio.practice.thread.chapter02.t1.safety;

import lombok.Data;

/**
 * 方法内的私有属性是线程安全的，不用考虑并发问题。
 * @author Liqc
 * @date 2019/11/29 17:18
 */
public class SafetyMethodTest {
    public static void main(String[] args) {
        HasSelfPrivateNum hasSelfPrivateNum = new HasSelfPrivateNum();
        Thread threadA = new Thread(() -> {
            hasSelfPrivateNum.addName("a");
        });
        Thread threadB = new Thread(() -> {
            hasSelfPrivateNum.addName("b");
        });
        threadA.start();
        threadB.start();
    }
    /* 私有属性不会出现并发问题。
    a set over!
    b set over!
    b num=200
    a num=100
     */
}

@Data
class HasSelfPrivateNum {
    // 会有线程安全问题
//    int num = 0;
    public void addName(String username) {
        try {
            // 不会有线程安全问题
            int num = 0;
            if ("a".equals(username)) {
                num = 100;
                System.out.println("a set over!");
                Thread.sleep(2000);
            } else {
                num = 200;
                System.out.println("b set over!");
            }
            System.out.println(username + " num=" + num);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}