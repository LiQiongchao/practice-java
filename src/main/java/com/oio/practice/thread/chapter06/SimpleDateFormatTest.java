package com.oio.practice.thread.chapter06;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormat类测试 - 此类为线程不完全的类。
 *  每次解析时都创建一个SimpleDateFormat的实例就不会有问题。
 * @author Liqc
 * @date 2020/4/22 13:39
 */
public class SimpleDateFormatTest {


    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String[] arr = new String[]{"2020-09-10","2020-09-1","2020-09-2","2020-09-3","2020-09-4","2020-09-5"
                ,"2020-09-6","2020-03-10","2020-04-10","2020-06-10","2020-07-10"};
        for (int i = 0; i < 10; i++) {
            int aa = i;
            new Thread(() -> {
                try {
                    Date parse = sdf.parse(arr[aa]);
                    System.out.println(parse);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
