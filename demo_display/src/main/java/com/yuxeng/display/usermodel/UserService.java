package com.yuxeng.display.usermodel;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Resource
    private UserDao userDao;
    User register(User registry_user) {
        registry_user.setId(-1);
        userDao.insert(registry_user);
        return registry_user;
    }

    User findById(Integer id) {
        return userDao.findById(id);
    }
}
