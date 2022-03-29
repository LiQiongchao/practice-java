package com.oio.practice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Liqc
 * @Date: 2021/12/18 16:55
 */
public class DistinctSubcodeTest {


    @Test
    public void distinctTest() {
        /**
         * 如果有短的是长的字符的开头，只保留短的
         */
        ArrayList<String> strings = new ArrayList<>(
                Arrays.asList("10", "1010", "101010", "1110", "1011", "101110"));
        List<String> collect = strings.stream().sorted().collect(Collectors.toList());
        System.out.println(collect);
        Iterator<String> iterator = collect.iterator();
        String last = collect.get(0);
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (s.startsWith(last) && !s.equals(last)) {
                iterator.remove();
            } else {
                last = s;
            }
        }
        System.out.println(collect);
    }

}
