package com.yuxeng.display.usermodel;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

  User findById(Integer id);

  Integer insert(User user);

  void update(User user);

  void deleteById(Integer id);

  User findByUsername(String username);
}
