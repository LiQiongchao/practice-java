package com.oio.practice.thread.chapter01.t14.synchronize.in.stop.method;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.SQLOutput;

/**
 * 测试使用stop()方法后，会使同步锁的内的数据不一致
 * @author Liqc
 * @date 2019/11/27 12:48
 */
public class StopSynchronizedObject {

    public static void main(String[] args) {
        try {
            SynchronizedObject object = new SynchronizedObject();
            RunStopObject runStopObject = new RunStopObject(object);
            runStopObject.start();
            Thread.sleep(500);
            runStopObject.stop();
            System.out.println(object.getUsername() + " " + object.getPassword());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /* 产生了数据不一致。
    b aa
    */
}
@AllArgsConstructor
class RunStopObject extends Thread {
    private SynchronizedObject object;
    @Override
    public void run() {
        object.printString("b", "bb");
    }
}

@Data
class SynchronizedObject {
    private String username = "a";
    private String password = "aa";
    synchronized public void printString(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(1000);
            this.password = password;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
