package com.oio.practice.jdk.java11;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * Stream API测试
 * @author Liqc
 * @date 2020/5/12 12:56
 */
public class StreamApiTest {

    /**
     * 新API ofNullable()方法测试
     */
    @Test
    public void streamOfTest() {
        // 创建流
        Stream<Integer> integerStream = Stream.of(1, 3, 5, 3);
        integerStream.forEach(System.out::println);

        // 会有空指针
        Stream<Object> hasException = Stream.of(null);

        // 不会有空指针异常
        Stream<Object> notException = Stream.ofNullable(null);
    }

}
