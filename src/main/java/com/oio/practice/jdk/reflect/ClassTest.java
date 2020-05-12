package com.oio.practice.jdk.reflect;

/**
 * @author Liqc
 * @date 2019/4/18 9:15
 */
public class ClassTest {

    static {
        System.out.println("loading ClassTest");
    }

    public static void print(String src) {
        System.out.println(src);
    }

    public static void main(String[] args) {
        new Gum();
        print("after first Gum");
        new Gum();
        print("after second Gum");
        /*
            loading ClassTest
            loading gum!
            after first Gum
            after second Gum
        * */
    }



}

class Gum {
    static {
        System.out.println("loading gum!");
    }
}