package com.oio.practice.thread.chapter01.t15.suspend;

import lombok.Data;

/**
 * 测试使用suspend()导致数据不一致
 * @author Liqc
 * @date 2019/11/27 18:41
 */
public class SuspendResumeNoSameValue {
    public static void main(String[] args) {
        try {
            SuspendNoSameValue noSameValue = new SuspendNoSameValue();
            Thread thread = new Thread(()->{
                noSameValue.setAccount("b", "bb");
            });
            thread.start();
            Thread.sleep(1000);
            noSameValue.printAccount();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    暂停线程a
    username: b - password: aa
     */
}

@Data
class SuspendNoSameValue {
    private String username = "a";
    private String password = "aa";
    synchronized public void setAccount(String username, String password) {
        this.username = username;
        if ("b".equals(username)) {
            System.out.println("暂停线程a");
            Thread.currentThread().suspend();
        }
        this.password = password;
    }
    public void printAccount() {
        System.out.println("username: " + username +  " - password: " + password );
    }
}
