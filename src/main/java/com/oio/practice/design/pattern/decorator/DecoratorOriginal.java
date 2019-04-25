package com.oio.practice.design.pattern.decorator;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 装饰模式原型
 * @author Liqc
 * @date 2019/4/25 13:27
 */
public class DecoratorOriginal {

    public static void main(String[] args) {
        ConcreteComponent cc = new ConcreteComponent();
        ConcreteDecoratorA ca = new ConcreteDecoratorA();
        ConcreteDecoratorB cb = new ConcreteDecoratorB();
        ca.setComponent(cc);
        cb.setComponent(ca);
        cb.operation();
        /*
            ConcreteComponent的职责。。
            concrete decorator AAA
            concrete decoratorB behavior...
            concrete decorator BBB
        * */
    }

}

abstract class Component {
    /**
     * 对应职责
     */
    public abstract void operation();
}

class ConcreteComponent extends Component {

    @Override
    public void operation() {
        System.out.println("ConcreteComponent的职责。。");
    }
}

@Data
@NoArgsConstructor
abstract class Decorator extends Component {

    protected Component component;

    public Decorator (Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        if (component != null) {
            component.operation();
        }
    }
}

@Data
class  ConcreteDecoratorA extends Decorator {

    /**
     * 本类独有功能
     */
    private String addedState;

    @Override
    public void operation() {
        super.operation();
        addedState = "concrete decorator AAA";
        System.out.println(addedState);
    }
}

@Data
class  ConcreteDecoratorB extends Decorator {

    @Override
    public void operation() {
        super.operation();
        addedBehavior();
        System.out.println("concrete decorator BBB");
    }

    private void addedBehavior() {
        System.out.println("concrete decoratorB behavior...");
    }
}