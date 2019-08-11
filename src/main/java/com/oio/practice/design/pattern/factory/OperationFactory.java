package com.oio.practice.design.pattern.factory;

/**
 * 简单工厂
 * @author Liqc
 * @date 2019/4/19 12:45
 */
public class OperationFactory {

    public static Operation createOperationFactory(String operate) {
        switch (operate) {
            case "+":
                return new OperationAdd();
            case "-":
                return new OperationSub();
            case "*":
                return new OperationMul();
            case "/":
                return new OperationDiv();
                default:
        }
        return null;
    }

}

class OperationClient {
    public static void main(String[] args) {
        Operation operationFactory = OperationFactory.createOperationFactory("+");
        operationFactory.setNumberA(5);
        operationFactory.setNumberB(6);
        System.out.println("result: " + operationFactory.getResult());
    }
}
