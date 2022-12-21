package com.oio.practice.jdk.extend;

/**
 * @Author: Liqc
 * @Date: 2022/8/31 14:51
 */
public class ExtendTest {

    public static void main(String[] args) {
        Children parent = new Children();
        parent.setId(123L);
        parent.setParentId(123L);
        System.out.println(parent.getId());
        System.out.println(parent.hashCode());
    }

}
