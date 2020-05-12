package com.oio.practice.jdk.io;

import java.io.*;

/**
 * @Author: LiQiongchao
 * @Date: 2019/4/14 15:32
 */
public class FileReadLine {
    public static void main(String[] args) {
        try (FileReader fileReader = new FileReader(new File("test.txt"));
             BufferedReader bufferedReader = new BufferedReader(fileReader);
             FileWriter fileWriter = new FileWriter(new File("test-copy.txt"));
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ){
            String red = null;
            int num = 1;
            while ((red = bufferedReader.readLine())!=null) {
                bufferedWriter.write(String.format("%s:%s\r", num, red));
                num ++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
