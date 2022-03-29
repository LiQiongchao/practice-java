package com.oio.practice.jdk.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @Author: Liqc
 * @Date: 2022/2/8 14:51
 */
public class StreamTest {

    @Test
    public void flatMapTest() {
        List<List<Integer>> list = new ArrayList();
        list.add(Arrays.asList(1, 2, 3, 4));
        list.add(Arrays.asList(4,5,6,7));
        list.stream().flatMap(Collection::stream).forEach(System.out::println);


    }


}
