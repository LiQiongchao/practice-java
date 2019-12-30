package com.oio.practice.thread.chapter02.t8.monitoring;

import lombok.Data;

/**
 * 当同步锁为同一个对象时，会阻塞其它线程
 * 当同步锁为不同对象时，不会阻塞其它线程
 * @author Liqc
 * @date 2019/12/20 12:53
 */
public class MonitoringServiceTest {
    public static void main(String[] args) {
        MonitoringService service = new MonitoringService();
        new Thread(()->service.setUsernamePassword("a", "aa")).start();
        new Thread(()->service.setUsernamePassword("b", "bb")).start();
    }
    /*
    使用相同的对象锁1：
        线程名为：Thread-0在1576817980399进入同步块
        线程名为：Thread-0在1576817983399离开同步块
        线程名为：Thread-1在1576817983399进入同步块
        线程名为：Thread-1在1576817986399离开同步块
    使用不同的对象锁2：
        线程名为：Thread-0在1576818224070进入同步块
        线程名为：Thread-1在1576818224070进入同步块
        线程名为：Thread-1在1576818227071离开同步块
        线程名为：Thread-0在1576818227071离开同步块
     */
}

@Data
class MonitoringService {
    private String usernameParam;
    private String passwordParam;
    // 1. 使用同一对象锁
//    private String anyString = new String();
    public void setUsernamePassword(String username, String password) {
        try {
            // 2. 使用不同的对象锁
            String anyString = new String();
            synchronized (anyString) {
                System.out.println("线程名为：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "进入同步块");
                this.usernameParam = username;
                Thread.sleep(3000);
                this.passwordParam = password;
                System.out.println("线程名为：" + Thread.currentThread().getName() + "在" + System.currentTimeMillis() + "离开同步块");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
