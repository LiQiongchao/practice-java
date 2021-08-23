package com.oio.practice.interview.atguigu.second.ch03juc.p01volatil;

/**
 * 单例模式
 * @Author: LiQiongchao
 * @Date: 2020/5/17 15:45
 */
public class SingletonDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                Singleton.getInstance();
            }, i + "").start();
        }
    }

}

class Singleton {

    /**
     * 此处的volatile是为了防止指令重排
     * new一个对象的时候会有三步
     *  1- memory=allocate();//1.分配对象内存空间
     *  2- instance(memory);//2.初始化对象
     *  3- instance=memory;//3.设置instance的指向刚分配的内存地址,此时instance!=null
     *  一旦指令重排，把2和3交换后，会出现另外一个线程去取的时候可以取到对象，但是对象并没有初始化，
     *  对象数据还不存在，只是分配了内存空间。所以加volatile可以防止这种现象出现。
     */
    public static volatile Singleton singleton = null;

    private Singleton() {
        System.out.println(Thread.currentThread().getName() + " constructor instance..");
    }

    // DCL (double check lock)
    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
