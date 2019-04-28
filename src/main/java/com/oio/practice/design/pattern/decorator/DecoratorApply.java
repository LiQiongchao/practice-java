package com.oio.practice.design.pattern.decorator;

import lombok.Data;

/**
 * 装饰模式的应用
 * @author Liqc
 * @date 2019/4/25 13:46
 */
public class DecoratorApply {
    public static void main(String[] args) {
        Person person = new Person("小明");
        Finery a = new TShirt();
        Finery b = new BigTrouser();
        a.setComponent(person);
        b.setComponent(a);
        b.show();
    }

}

/**
 * 相当于 ConcreteComponent
 * 实现具体的职责
 */
@Data
class Person {

    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("装扮的" + name);
    }
}

/**
 * 服装类
 * 相当于Decorator
 */
@Data
class Finery extends Person {

    protected Person component;

    @Override
    public void show() {
        if (component != null) {
            component.show();
        }
    }
}

/**
 * 相当于ComponentDecoratorA
 */
class TShirt extends Finery {

    @Override
    public void show() {
        System.out.println("大T恤");
        component.show();
    }
}

class BigTrouser extends Finery {
    @Override
    public void show() {
        System.out.println("大裤衩");
        component.show();
    }
}

