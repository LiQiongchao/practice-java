package com.oio.practice.thread.chapter01.t4.threadsafe;

/**
 * 测试线程安全问题
 * @author Liqc
 * @date 2019/8/14 13:18
 */
public class LoginServer {

    private static String usernameRef;
    private static String passwordRef;

//    public static void doPost(String username, String password) {
    synchronized public static void doPost(String username, String password) {
        try {
            usernameRef = username;
            if (("a").equals(username)) {
                Thread.sleep(5000);
            }
            passwordRef = password;
            System.out.println("usernameRef = " + usernameRef + ", passwordRef = " + passwordRef);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class run {
    public static void main(String[] args) {
        ALogin a = new ALogin();
        BLogin b = new BLogin();
        a.start();
        b.start();
        /*
        会出现线程不安全
        usernameRef = a, passwordRef = bb
        usernameRef = a, passwordRef = aa

        解决在doPost方法上加synchronized 关键字
        usernameRef = a, passwordRef = aa
        usernameRef = b, passwordRef = bb
        */
    }
}

class ALogin extends Thread {
    @Override
    public void run() {
        super.run();
        LoginServer.doPost("a", "aa");
    }
}
class BLogin extends Thread {
    @Override
    public void run() {
        super.run();
        LoginServer.doPost("b", "bb");
    }
}


