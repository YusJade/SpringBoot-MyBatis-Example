package com.example.demo2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class Demo2ApplicationTests {

    @Resource
    HelloService helloService;

    @Test
    void contextLoads() {
        System.out.println(helloService.sayHello(0));
        System.out.println(helloService.sayHello(1));
        System.out.println(helloService.sayHello(2));
    }
}
