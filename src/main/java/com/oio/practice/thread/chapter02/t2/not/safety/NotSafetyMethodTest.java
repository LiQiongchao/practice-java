package com.oio.practice.thread.chapter02.t2.not.safety;

import lombok.Data;

/**
 * 非线程安全问题测试
 *      共享私有属性就会出现并发问题。
 * @author Liqc
 * @date 2019/11/29 17:30
 */
public class NotSafetyMethodTest {
    public static void main(String[] args) {
        HasPublicNum hasSelfPrivateNum = new HasPublicNum();
        Thread threadA = new Thread(() -> {
            hasSelfPrivateNum.addName("a");
        });
        Thread threadB = new Thread(() -> {
            hasSelfPrivateNum.addName("b");
        });
        threadA.start();
        threadB.start();
    }
    /* 共享私有属性就会出现并发问题。
    a set over!
    b set over!
    b num=200
    a num=200
     */
}

@Data
class HasPublicNum {
    int i = 0;
    public void addName(String username) {
        try {
            if ("a".equals(username)) {
                i = 100;
                System.out.println("a set over!");
                Thread.sleep(2000);
            } else {
                i = 200;
                System.out.println("b set over!");
            }
            System.out.println(username + " num=" + i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
