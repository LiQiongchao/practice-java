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
     * 测试
     * takeWhile(Predicate<? super T> predicate)
     * dropWhile(Predicate<? super T> predicate)
     */
    @Test
    public void whileTest() {
        Stream<Integer> stream = Stream.of(1, 3, 5, 6, 8, 3);
        // 根据条件取流中的元素，直到为假时停止。
        Stream<Integer> takeStream = stream.takeWhile(s -> s < 6);
        takeStream.forEach(System.out::print);
        // 135
        System.out.println("\n *******************************");
        Stream<Integer> streamOr = Stream.of(1, 3, 5, 6, 8, 3);
        // 根据条件删除流中的数据，直到遇到不符合条件的数据时，停止操作。
        Stream<Integer> dropStream = streamOr.dropWhile(s -> s < 6);
        dropStream.forEach(System.out::print);
        // 683
    }

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
