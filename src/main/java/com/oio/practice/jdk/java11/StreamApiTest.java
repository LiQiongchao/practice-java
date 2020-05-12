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
     * Stream无限流方法iterate()增加了停止条件的判断hasNext参数
     * iterate(T seed, Predicate<? super T> hasNext, UnaryOperator<T> next)
     */
    @Test
    public void iterateTest() {
        // jdk8中，会一直执行下去，如果停止，使用limit方法，比如执行10次停止。
        Stream<Integer> iterateStream = Stream.iterate(1, t -> t * 2);
        iterateStream.limit(10).forEach(System.out::println);
        System.out.println("*************************************");
        // JDK11中增加根据条件终止无限流
        Stream<Integer> iterateStream2 = Stream.iterate(1, t -> t < 1000, t -> t * 2);
        iterateStream2.forEach(System.out::println);
    }

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
