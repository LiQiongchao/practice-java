package com.oio.practice.learn.jdk.exception;

/**
 * @Author: LiQiongchao
 * @Date: 2019/3/31 13:26
 */
public class MyException extends RuntimeException {

    public int a = 0;

    public void release() throws Throwable {
        this.finalize();
    }

}
