package com.oio.practice.thread.chapter02.t3.syn.cant.extend;


/**
 * 子类不能继承父类方法的synchronized字段，需要自己再单独添加
 * @author Liqc
 * @date 2019/12/5 12:55
 */
public class SynNotExtendTest {
    public static void main(String[] args) {
        SynNotExtendSub sub = new SynNotExtendSub();
        Thread threadA = new Thread(() -> sub.service());
        Thread threadB = new Thread(() -> sub.service());
        threadA.setName("a");
        threadA.start();
        threadB.setName("b");
        threadB.start();
    }
    /*
    子类重写方法不加synchronized
        in sub 下一步 sleep start ThreadName=a run beginTime=1575522163895
        in sub 下一步 sleep start ThreadName=b run beginTime=1575522163895
        in sub 下一步 sleep end ThreadName=a run beginTime=1575522166896
        in sub 下一步 sleep end ThreadName=b run beginTime=1575522166896
     子类重写方法加synchronized
        in sub 下一步 sleep start ThreadName=a run beginTime=1575522201115
        in sub 下一步 sleep end ThreadName=a run beginTime=1575522204116
        in sub 下一步 sleep start ThreadName=b run beginTime=1575522204116
        in sub 下一步 sleep end ThreadName=b run beginTime=1575522207116
     */
}

class SynNotExtend {
    synchronized public void service() {
        try {
            System.out.println("in parent 下一步 sleep start ThreadName=" + Thread.currentThread().getName() + " run beginTime="
                    + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("in parent 下一步 sleep end ThreadName=" + Thread.currentThread().getName() + " run beginTime="
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class SynNotExtendSub extends SynNotExtend {
    @Override
    synchronized public void service() {
        try {
            System.out.println("in sub 下一步 sleep start ThreadName=" + Thread.currentThread().getName() + " run beginTime="
                    + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("in sub 下一步 sleep end ThreadName=" + Thread.currentThread().getName() + " run beginTime="
                    + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
