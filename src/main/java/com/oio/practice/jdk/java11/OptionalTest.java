package com.oio.practice.jdk.java11;

import org.junit.Test;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Optional新增方法测试
 * @author Liqc
 * @date 2020/5/12 14:02
 */
public class OptionalTest {

    @Test
    public void optionalTest() {
        Optional<Object> optional = Optional.ofNullable(null);
        // 转换成一个Stream对象
        Stream<Object> stream = optional.stream();
        // 如果为null就抛出异常
        optional.orElseThrow();

    }

}
