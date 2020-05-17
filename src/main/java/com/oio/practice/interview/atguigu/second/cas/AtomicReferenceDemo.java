package com.oio.practice.interview.atguigu.second.cas;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 *  AtomicReference测试
 *  里面可以包装任何一个对象成为一个原子类。
 * @Author: LiQiongchao
 * @Date: 2020/5/17 22:51
 */
public class AtomicReferenceDemo {

    public static void main(String[] args) {
        User z3 = new User("z3", (short) 30);
        User l4 = new User("l4", (short) 30);
        AtomicReference<User> reference = new AtomicReference<>(z3);
        boolean b = reference.compareAndSet(z3, l4);
        System.out.println(b);
        System.out.println(reference.get());
    }

}

@Data
@AllArgsConstructor
class User {

    private String name;
    private Short age;

}
