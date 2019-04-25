package com.oio.practice.design.pattern.strategy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 策略模式结合工厂模式使用
 * @Author: LiQiongchao
 * @Date: 2019/4/21 0:02
 */
public class StrategyAndFactory {

    public static void main(String[] args) {
//        String type = "正常收费";
        String type = "8折";
        CashFactoryContext context = new CashFactoryContext(type);
        System.out.println(context.getResult(400));
    }

}

class CashFactoryContext {

    CashSuper cashSuper;
    public CashFactoryContext(String type) {
        switch (type) {
            case "正常收费":
                cashSuper = new CashRate(0);
                break;
            case "300-100":
                cashSuper = new CashReturn(300, 100);
                break;
            case "8折":
                cashSuper = new CashRate(0.8);
                break;
            default:
                return;
        }
    }

    public double getResult(double money) {
        return cashSuper.acceptCash(money);
    }

}


@Data
@NoArgsConstructor
@AllArgsConstructor
class CashFactoryRate extends CashSuper {

    private double moneyRate = 1;

    @Override
    public double acceptCash(double money) {
        if (money > 0) {
            return new BigDecimal(money).multiply(new BigDecimal(moneyRate)).doubleValue();
        }
        return 0;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class CashFactoryReturn extends CashSuper {

    private double moneyCondition = 0;
    private double returnMoney = 0;

    @Override
    public double acceptCash(double money) {
        if (money > 0 && moneyCondition > 0 && money >= moneyCondition) {
            // 每满多少返returnMoney
            BigDecimal returnTotalMoney = new BigDecimal(Math.floor(returnMoney / moneyCondition)).multiply(new BigDecimal(returnMoney));
            return new BigDecimal(money).subtract(returnTotalMoney).doubleValue();
        }
        return 0;
    }
}


