package com.oio.practice.interview.base;

/**
 * 局部变量与成员变量的测试
 * @author Liqc
 * @date 2020/5/9 13:19
 */
public class VarAndConstVarTest {

    static int s;
    int i, j;
    {
        int i = 1;
        i++;
        j++;
        s++;
    }
    public void test(int j) {
        j++;
        i++;
        s++;
    }

    public static void main(String[] args) {
        VarAndConstVarTest test1 = new VarAndConstVarTest();
        VarAndConstVarTest test2 = new VarAndConstVarTest();
        test1.test(10);
        test1.test(20);
        test2.test(30);
        System.out.println(test1.i + "," + test1.j + "," + test1.s);
        System.out.println(test2.i + "," + test2.j + "," + test2.s);
        /*
        2,1,5
        1,1,5
         */
    }
}
