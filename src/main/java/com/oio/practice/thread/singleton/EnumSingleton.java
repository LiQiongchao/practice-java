package com.oio.practice.thread.singleton;

/**
 * 利用枚举实现单例模式
 * @author Liqc
 * @date 2020/3/17 11:42
 */
public class EnumSingleton {

    /**
     * 私有化，防止在外部创建实例
     */
    private EnumSingleton() {
    }

    public enum SingletonEnum {
        // 定义的枚举实例
        SEED;

        private EnumSingleton singleton;

        SingletonEnum() {
            this.singleton = new EnumSingleton();
        }

        public EnumSingleton getInstance() {
            return singleton;
        }
    }

    public static void main(String[] args) {
        // 测试
        EnumSingleton instance = SingletonEnum.SEED.getInstance();
        EnumSingleton instance1 = SingletonEnum.SEED.getInstance();
        System.out.println(instance.equals(instance1));
        // true
    }
}
