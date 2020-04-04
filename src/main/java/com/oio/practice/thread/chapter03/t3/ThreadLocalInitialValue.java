package com.oio.practice.thread.chapter03.t3;

/**
 * 重写ThreadLocal的initialValue的方法，实现对get实现初始化的效果
 * @author Liqc
 * @date 2020/4/4 14:14
 */
public class ThreadLocalInitialValue {
    public static void main(String[] args) {
        ThreadLocalExt threadLocal = new ThreadLocalExt();
        if (threadLocal.get() == null) {
            System.out.println("no value");
            threadLocal.set("first set value");
        }
        System.out.println(threadLocal.get());
    }
    /*
    Thread default value.
     */
}
class ThreadLocalExt<T> extends ThreadLocal {
    @Override
    protected Object initialValue() {
        return "Thread default value.";
    }
}
