package com.oio.practice.jdk.exception;

/**
 * @Author: LiQiongchao
 * @Date: 2019/3/31 13:13
 */
public class ExceptionTest {

    public static void main(String[] args) throws Throwable {

        MyException myException = new MyException();
        myException.a = 10;
        System.out.println(myException.a);
        myException.release();
        System.out.println(myException.a);

        // jdk8 exception
//        try(InputStream stream = new FileInputStream("")) {
        try {
            System.out.println("jdk8 exception");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
