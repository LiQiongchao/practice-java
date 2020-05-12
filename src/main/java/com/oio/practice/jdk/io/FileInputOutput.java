package com.oio.practice.jdk.io;

import java.io.*;

/**
 * @Author: LiQiongchao
 * @Date: 2019/4/14 15:21
 */
public class FileInputOutput {

    public static void main(String[] args) {
        try (FileInputStream fis = new FileInputStream(new File("test.txt"));
             FileOutputStream fos = new FileOutputStream(new File("test-copy.txt"))
        ){
            byte[] read = new byte[80];
            fis.read(read);
            fos.write(read);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
