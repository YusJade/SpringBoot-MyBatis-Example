package com.example.demo2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/say-hello")
public class SayController {
    @Resource
    HelloService helloService;

    @RequestMapping("/to/{index}")
    String sayHelloTo(@PathVariable("index") int index) {
        return helloService.sayHello(index);
    }

    @RequestMapping
    String sayHello() {
        return "你好！";
    }
}
