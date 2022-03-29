package com.oio.practice;

import org.junit.Test;

/**
 * @Author: Liqc
 * @Date: 2022/3/11 11:18
 */
public class StringTest {

    private final static String LOG_BODY_PREFIX = "====Body=====";

    @Test
    public void substringTest() {
        String str = "\n" +
                "\n" +
                "================  Request Start  ================\n" +
                "===> {}: {}\n" +
                "================   Request End   ================\n";
        String replaceStr = str.substring(str.indexOf(LOG_BODY_PREFIX), str.indexOf("================   Request End   ================"));
        System.out.println(str.replace(replaceStr, "\n"));
    }


}
