package com.oio.practice.design.pattern.proxy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author Liqc
 * @date 2019/5/8 13:23
 */
public class ProxyOriginal {

    public static void main(String[] args) {
        Proxy proxy = new Proxy(new RealSubject());
        proxy.request();
    }

}

interface Subject {
    void request();
}

class RealSubject implements Subject {

    @Override
    public void request() {
        System.out.println("real request...");
    }
}

@NoArgsConstructor
@AllArgsConstructor
class Proxy implements Subject {

    private RealSubject realSubject;

    @Override
    public void request() {
        if (realSubject != null) {
            realSubject.request();
        }
    }
}