package com.oio.practice.interview.base;

/**
 * 值传递与址传递的测试
 * 引用型的都是传址调用，除了包装类和String类在变更时会重新指一个新的对象地址。
 * @Author: LiQiongchao
 * @Date: 2020/5/19 22:39
 */
public class ValueTransferDemo {

    public static void main(String[] args) {
        int i = 1;
        String str = "hello";
        Integer num = 200;
        int[] arr = {1, 2, 3, 4, 5};
        MyData my = new MyData();
        change(i, str, num, arr, my);
        System.out.println("i = " + i);
        System.out.println("str = " + str);
        System.out.println("arr[0] = " + arr[0]);
        System.out.println("num = " + num);
        System.out.println("my.a = " + my.a);
        /*
        i = 1
        str = hello
        arr[0] = 2
        num = 200
        my.a = 11
         */
    }

    private static void change(int i, String str, Integer num, int[] arr, MyData my) {
        i += 1;
        str += "world";
        num += 1;
        arr[0] += 1;
        my.a += 1;
    }

}

class MyData {
    int a = 10;
}
