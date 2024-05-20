package com.yuxeng.display.usermodel;

import jakarta.annotation.Resource;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Resource
    private UserDao userDao;
    User register(User registry_user) {
        registry_user.setId(-1);
        userDao.insert(registry_user);
        return userDao.findById(registry_user.getId());
    }

    User login(User login_user) {
        User user = userDao.findByUsername(login_user.getUsername());
        if (user == null ||
            !Objects.equals(user.getPassword(), login_user.getPassword())) {
            user = new User();
            user.setId(-1);
            return  user;
        }
        return user;
    }

    User findById(Integer id) {
        return userDao.findById(id);
    }
}
