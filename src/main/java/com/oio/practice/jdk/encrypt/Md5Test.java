package com.oio.practice.jdk.encrypt;

import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密测试
 * @author Liqc
 * @date 2020/5/13 12:19
 */
public class Md5Test {

    @Test
    public void md5Encrypt() throws NoSuchAlgorithmException {
        String str = "200dsjeowif99209402";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(str.getBytes());
        byte[] digest = md5.digest();
        System.out.println(digest);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < digest.length; i++) {
            String s = Integer.toHexString(digest[i] & 0xFF);
            if (s.length() == 1) {
                sb.append("0");
            }
            sb.append(s);
        }
        System.out.println(sb.toString());
        // b18ecec9210be9c1c3bffa5c09dd2553
    }

    @Test
    public void md5Encrypt2() throws NoSuchAlgorithmException {
        String str = "00dsjeowif99209402";
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(str.getBytes());
        byte[] digest = md5.digest();
        String s = new BigInteger(1, digest).toString(16);
        // 防止加密串小于32位，因为BigInteger会把首位的0去掉
        if (s.length() < 32) {
            int dif = 32 - s.length();
            String difStr = "0";
            if (dif > 1) {
                difStr = String.format("%0" + dif + "d", 0);
            }
            s = difStr + s;
        }
        System.out.println(s);
        // b18ecec9210be9c1c3bffa5c09dd2553
    }

    @Test
    public void byteTest() {
        System.out.println("127".getBytes());
        System.out.println(Integer.toHexString(127));
        System.out.println("1".getBytes());
        System.out.println(Integer.toHexString("1".getBytes()[0]));
        System.out.println(Integer.toHexString("1".getBytes()[0] & 0xFF));
        System.out.println(new BigInteger(new byte[]{18,50,60,8,0}).toString(16));
        System.out.println(new BigInteger(new byte[]{8,50,60,8,0}).toString(16));
        System.out.println(new BigInteger(new byte[]{0,50,60,8,0}).toString(16));
        System.out.println(String.format("%032s", new BigInteger(new byte[]{0,50,60,8,0}).toString(16)));
        System.out.println();
    }

    @Test
    public void formatTest() {
        String s = "3081432D095470560F63DCBF5979B";
        if (s.length() < 32) {
            int dif = 32 - s.length();
            String difStr = "0";
            if (dif > 1) {
                difStr = String.format("%0" + dif + "d", 0);
            }
            s = difStr + s;
        }
        System.out.println(s);
    }

}
