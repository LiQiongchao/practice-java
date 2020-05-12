package com.oio.practice.design.pattern.factory;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author: LiQiongchao
 * @Date: 2019/4/20 23:34
 */
@Data
@NoArgsConstructor
public abstract class Operation {

    private double numberA = 0;
    private double numberB = 0;

    public double getResult() {
        return 0;
    }
}

class OperationAdd extends Operation {

    @Override
    public double getResult() {
        return new BigDecimal(getNumberA()).add(new BigDecimal(getNumberB())).doubleValue();
    }
}
class OperationSub extends Operation {

    @Override
    public double getResult() {
        return new BigDecimal(getNumberA()).subtract(new BigDecimal(getNumberB())).doubleValue();
    }
}
class OperationMul extends Operation {

    @Override
    public double getResult() {
        return new BigDecimal(getNumberA()).multiply(new BigDecimal(getNumberB())).doubleValue();
    }
}
class OperationDiv extends Operation {

    @Override
    public double getResult() {
        return new BigDecimal(getNumberA()).divide(new BigDecimal(getNumberB())).doubleValue();
    }
}
