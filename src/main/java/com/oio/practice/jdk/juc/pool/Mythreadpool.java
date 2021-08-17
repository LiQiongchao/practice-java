package com.oio.practice.jdk.juc.pool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Mythreadpool {
    LinkedList<Task> taskList = new LinkedList<Task>();
    
    class Task { //任务类
        int id;
        Task(int id){
            this.id=id;
            System.out.println("第"+id+"个任务产生");
        }
        public void run() {//具体的工作
            System.out.println("第"+id+"个任务正在执行");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第"+id+"个任务执行完毕");
        }
    }
    
    class Worker extends Thread { //工人实体
        String name;
        Worker(String name) {
            this.name = name;
        }
        
        public void run() {
            while(true) {
                if(taskList.size() == 0) {
                    try {
                        synchronized (taskList) {
                            System.out.println("Worker " + name+" 没有任务");
                            taskList.wait(); //没得到任务，进入tasklist的等待队列
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (taskList) {
                    System.out.println("Worker " + name+" 得到任务");
                    taskList.removeFirst().run();
                }
            }
        }
    }
    
    void pool() {  //工人。只生产了两个工人
         ArrayList<Worker> wokerlist=new ArrayList<Worker>();
         for(int i=0;i<2;i++) {
             Worker k = new Worker("第"+(i+1)+"个工人");
             k.start();
             wokerlist.add(k);//
         }
    }
    
    class Factory extends Thread{ //生产任务的线程，总共会生产10个任务
        public void run() {
            for(int i=0;i<10;i++) {
                synchronized(taskList) {
                    taskList.addLast(new Task(i+1));
                    taskList.notify();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Mythreadpool mythreadpool = new Mythreadpool();
        mythreadpool.pool(); //初始化工人
        Mythreadpool.Factory m= mythreadpool.new Factory();
        m.start();
    }
}