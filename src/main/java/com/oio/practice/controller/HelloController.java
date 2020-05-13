package com.oio.practice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LiQiongchao
 * @Date: 2020/5/10 19:09
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/world")
    public String hello() {
        return "hello world";
    }

}
