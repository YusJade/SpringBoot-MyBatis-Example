package com.yuxeng.display.usermodel;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

  /* INSERT */
  // Return：插入用户的ID
  int insertUser(String username, String password, String name, String gender, String phone,
      String email, Long max_borrow_days, Long max_borrow_books);

  /* SELECT */
  List<User> getAllUser();

  User getUserById(Long id);

  User getUserByUsername(String username);

  User getUserByEmail(String email);

  User getUserByPhone(String phone);

  /* UPDATE */
  // Return：更新的记录数
  int updateUserAllById(Long id, String username, String password, String name, String gender,
      String phone, String email, Long max_borrow_days, Long max_borrow_books);
  int updateUserPasswordById(Long id, String password);
  int updateUserPasswordByUsername(String username, String password);

  /* DELETE */
  int deleteUserById(Long id);
}
