package com.oio.practice.jdk.lambda;

/**
 * lambda中的this与当前对象的this是同一个，因为lambda表达式编译后是类中一个私有内部静态方法
 * 而匿名内部类编译后会，JDK会自动为其包装成一个类，所以一个类中有多少个匿名内部类，编译后就会有N+1个class文件，所以他们的this是不同的。
 * @author Liqc
 * @date 2020/1/17 10:21
 */
public class LambdaThisTest {

    Runnable r1 = () -> System.out.println(this);
    Runnable r2 = () -> System.out.println(toString());

    public static void main(String[] args) {
        new LambdaThisTest().r1.run();
        new LambdaThisTest().r2.run();
    }

    @Override
    public String toString() {
        return "Hello Lee";
    }
}
