package com.oio.practice.classes;

/**
 * @Author: LiQiongchao
 * @Date: 2019/5/19 15:21
 */
public class Sjx {

    private Integer a, b, c;

    public Sjx(Integer a, Integer b, Integer c) {
        this.a = a; this.b = b; this.c = c;
    }

    public Integer getA() {
        return this.a;
    }
    public Integer getB() {
        return this.b;
    }
    public Integer getC() {
        return this.c;
    }
    public Double area() {
        Double sum = (double)(a + b + c);
        double s = sum/2;
        return Math.sqrt(s*(s-a)*(s-b)*(s-c));
    }
    public Double sjxLength() {
        return (double) (a + b + c);
    }

}
class SjxComputer{
    public static void main(String[] args) {
        int a=3,b=6,c=8;
        Sjx sjx = new Sjx(3,6,8);
        System.out.println("a b c 面积 周长");
        System.out.println(a + " " + b + " " + c + " " + sjx.area() + " " + sjx.sjxLength());
    }
}
