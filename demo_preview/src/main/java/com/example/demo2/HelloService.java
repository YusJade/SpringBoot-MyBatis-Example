package com.example.demo2;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HelloService {
    @Resource
    private DemoDao demoDao;

    public String sayHello(Integer index) {
        List<User> list = demoDao.getAllUsers();
        if (index >= list.size() || index < 0) {
            return "你要打招呼的人不在这里！";
        }
        return String.format("你好，%s！:>", list.get(index).getName());
    }
}
