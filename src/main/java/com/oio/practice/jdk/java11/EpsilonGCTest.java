package com.oio.practice.jdk.java11;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用默认GC会回收500个对象。处理效率低。
 *
 * EpsilonGc
 * 开启：-XX:+UnlockExperimentalVMOptions -XX:+UseEpsilonGC
 *  - 不会回收任何对象，不做回收处理。
 *  - 效率高
 * EpsilonGC主要用途
 *  - 性能测试（它可以帮助过滤掉GC引起的性能假象）
 *  - 内存压力测试（例如：知道测试用例分配不超过1G内存，我们可以使用-Xmx1g -XX:+UseEpsilonGC, 如果程序有问题，则程序OOM退出。）
 *  - 非常短的JOB任务（对于这种任务，接收GC就是浪费时间）
 *  - VM接口测试
 *  - Last-drop 延迟&吞吐改进
 * @author Liqc
 * @date 2020/5/14 13:52
 */
public class EpsilonGCTest {
    public static void main(String[] args) {
        List<Garbage> list = new ArrayList<>();
        int count = 0;
        while (true) {
            list.add(new Garbage());
            if (count == 500) {
                list.clear();
            }
            count++;
        }
    }
}

class Garbage {

    int n = (int) (Math.random()*100);

    @Override
    protected void finalize() throws Throwable {
        System.out.println(this + " - " + n + " is recycling.");
    }
}
