package com.yuxeng.display.usermodel;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Resource
    UserService userService;
    @PostMapping("/register")
    User register(@RequestBody User registry_user) {
        // TODO: 验证用户的注册信息
        userService.register(registry_user);
        return registry_user;
    }

    @GetMapping("/user-info/{id}")
    User getUserById(@PathVariable Integer id) {
        return userService.findById(id);
    }
}
