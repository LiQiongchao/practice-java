package com.oio.practice.interview.base;

/**
 * Java 静态变量，代码块，父子类等的加载顺序
 * @author Liqc
 * @date 2020/5/8 13:57
 */
public class LoadOrder extends LoadOrderParent {

    private int i = test();

    private static int j = method();

    static {
        System.out.print("(6)");
    }

    public LoadOrder() {
        System.out.print("(7)");
    }

    {
        System.out.print("(8)");
    }

    public int test() {
        System.out.print("(9)");
        return 1;
    }

    public static int method() {
        System.out.print("(10)");
        return 1;
    }

    public static void main(String[] args) {
        LoadOrder order = new LoadOrder();
        System.out.println();
        LoadOrder order2 = new LoadOrder();
        /*
        (5)(1)(10)(6)(9)(3)(2)(9)(8)(7)
        (9)(3)(2)(9)(8)(7)
         */
    }
}

class LoadOrderParent {

    private int i = test();

    private static int j = method();

    static {
        System.out.print("(1)");
    }

    public LoadOrderParent() {
        System.out.print("(2)");
    }

    {
        System.out.print("(3)");
    }

    public int test() {
        System.out.print("(4)");
        return 1;
    }

    public static int method() {
        System.out.print("(5)");
        return 1;
    }
}
