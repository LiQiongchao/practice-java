package com.oio.practice.jdk.java11;

import org.junit.Test;

/**
 * java11字符新增方法测试
 * @author Liqc
 * @date 2020/5/12 13:51
 */
public class StringTest {

    @Test
    public void strTest() {
        String str = "  \n\tbbb  \n";
        // isBlank()判断字符是否为空白（包括空格、回车、制表符等）
        System.out.println(str.isBlank()); // true

        // trim()方法只能去除首尾的英文空格，ascii码值小于32的空白符
        System.out.println(str.trim());

        // strip() 去除字符前后的空白（包括空格、回车、制表符等包括非英文的空白符，如中文回车）
        System.out.println(str.strip());

        // stripLeading()去除头部的空白
        System.out.println(str.stripLeading());
        // stripTrailing() 去除尾部的空白
        System.out.println(str.stripTrailing());

    }

}
