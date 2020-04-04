package com.oio.practice.thread.chapter03.t3;

/**
 * 继承线程测试
 * 并定制子线程的值
 * @author Liqc
 * @date 2020/4/4 14:32
 */
public class InheritableThreadLocalTest {
    private static InheritableThreadLocalExt local = new InheritableThreadLocalExt();

    public static void main(String[] args) {
        local.set("time1: " + System.currentTimeMillis());

        new Thread(() -> {
            System.out.println("子线程get: " + local.get());
        }).start();

        local.set("time2: " + System.currentTimeMillis());

        System.out.println("父线程get: " + local.get());
    }
    /*
    父线程get: time: 1585982567085
    子线程get: time: 1585982567085子线程添加的！
     */
}

class InheritableThreadLocalExt extends InheritableThreadLocal {
    @Override
    protected Object childValue(Object parentValue) {
        return parentValue + "子线程添加的！";
    }
}
