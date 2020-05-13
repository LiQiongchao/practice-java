package com.oio.practice.jdk.java11;

import org.junit.Test;

import java.io.*;

/**
 * @Author: LiQiongchao
 * @Date: 2020/5/13 23:34
 */
public class InputStreamTest {

    @Test
    public void inputStream2() throws IOException {
        // InputStream使用lines()方法读取
        var cl = this.getClass().getClassLoader();
        // getResourceAsStream()可以读取项目根目录下的文件，读取read文件
        var inputStream = cl.getResourceAsStream("read");
        try(var os = new FileOutputStream("src/main/resources/read2")) {
            // 直接转换到输出流中，transferTo()方法来处理
            inputStream.transferTo(os);
        }
        inputStream.close();
    }

    @Test
    public void inputStream() throws IOException {
        // InputStream使用lines()方法读取
        FileInputStream fis = new FileInputStream("D:\\WorkSpaces\\practise-projects\\practice-java\\src\\main\\java\\com\\oio\\practice\\interview\\base\\InputStreamTest.java");
        // available() 获取流的有效长度
        byte[] buffer = new byte[fis.available()];
        fis.read(buffer);
        fis.close();
        String str = new String(buffer);
        // 按行打印文件中的内容
        str.lines().forEach(System.out::println);
    }

}
