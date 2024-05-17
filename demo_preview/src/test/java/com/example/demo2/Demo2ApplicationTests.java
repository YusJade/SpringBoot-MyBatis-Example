package com.example.demo2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class Demo2ApplicationTests {

    @Resource
    DemoDao demoDao;

    @Test
    void contextLoads() {
        List<User> list = demoDao.getAllUsers();
        System.out.println(list);
    }
}
