package com.yuxeng.display.usermodel;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

  /* INSERT */
  void insertUser(User db);

  /* SELECT */
  List<User> getAllUser();

  User getUserById(Long id);

  User getUserByUsername(String username);

  User getUserByEmail(String mail);

  User getUserByPhone(String phone);

  /* UPDATE */
  // Return：更新的记录
  int updateUserPasswordById(Long id, String password);

  int updateUserPasswordByUsername(String username, String password);

  int updateUserBasicInfo(User user);

  /* DELETE */
  int deleteUserById(Long id);


}
