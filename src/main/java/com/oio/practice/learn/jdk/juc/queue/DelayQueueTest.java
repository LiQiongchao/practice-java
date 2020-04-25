package com.oio.practice.learn.jdk.juc.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 测试 DelayQueueTest
 * @Author: LiQiongchao
 * @Date: 2020/4/25 23:44
 */
public class DelayQueueTest {
    public static void main(String[] args) {
        try {
            final DelayQueue<Student> queue = new DelayQueue<Student>();
            for (int i = 0; i < 5; i++) {
                Student student = new Student("学生" + i, Math.round(Math.random()*10+i));
                queue.put(student);
            }
            // queue.peek() 获取第一个元素但不移除
            System.out.println("queue.peek()" + queue.peek().getName());
            Student poll = queue.poll(1, TimeUnit.SECONDS);
            System.out.println("poll student " + poll.getName());
            System.out.println("end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*
        学生0 交卷，用时2
        学生1 交卷，用时9
        学生2 交卷，用时5
        学生3 交卷，用时9
        学生4 交卷，用时12
        queue.peek()学生0 交卷，用时2
        poll student 学生0 交卷，用时2
        end
         */
    }
}

class Student implements Delayed {

    private String name;
    // 交卷时间
    private long submitTime;
    /**
     * 考试时间
     */
    private long workTime;

    public Student(String name, long submitTime) {
        this.name = name;
        this.workTime = submitTime;
        this.submitTime = TimeUnit.NANOSECONDS.convert(submitTime, TimeUnit.MILLISECONDS) + System.nanoTime();
        System.out.println(this.name + " 交卷，用时" + workTime);
    }

    public String getName() {
        return this.name + " 交卷，用时" + workTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(submitTime - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        Student student = (Student) o;
        return submitTime > student.submitTime? 1: (submitTime < student.submitTime? -1: 0);
    }
}

