package com.oio.practice.thread.chapter02.t3.dirty.read;

import lombok.Data;

/**
 * 脏读测试
 * 脏读：取到了被其它线程修改后的数据, 此数据为不完整的数据。
 * 通过给取值方法加同步锁则可以避免脏读。
 * @author Liqc
 * @date 2019/12/4 13:11
 */
public class DirtyReadTest {
    public static void main(String[] args) {
        try {
            PublicVar publicVar = new PublicVar();
            new Thread(() -> publicVar.setAccount("B", "BB")).start();
            // 这里睡眠的值要少于setAccount()里的睡眠值
            Thread.sleep(1000);
            publicVar.printAccount();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    setAccount method thread name=main username=B password=AA
    setAccount method thread name=Thread-0 username=B password=BB
     */
}

@Data
class PublicVar {
    private String username = "A";
    private String password = "AA";

    synchronized public void setAccount(String username, String password) {
        try {
            this.username = username;
            Thread.sleep(5000);
            this.password = password;
            System.out.println("setAccount method thread name=" + Thread.currentThread().getName() + " username="
                    + username + " password=" + password);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void printAccount() {
        System.out.println("setAccount method thread name=" + Thread.currentThread().getName() + " username="
                + username + " password=" + password);
    }
}
