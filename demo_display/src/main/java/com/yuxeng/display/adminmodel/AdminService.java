package com.yuxeng.display.adminmodel;

import com.yuxeng.display.bookmodel.Book;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  @Resource
  private AdminDao adminDao;

  /**
   * 用户登录服务
   *
   * @param loginInfo 用户名和密码
   * @return -1：用户不存在；0：密码错误；1：密码正确
   */
  int login(Map<String, String> loginInfo) {
    String username = loginInfo.get("username");
    String password = loginInfo.get("password");
    if (username == null) {
      return -1;
    }

    Admin admin = adminDao.selectAdminByUsername(username);
    if (admin == null) {
      return -1;
    }
    if (admin.getPassword().equals(password)) {
      return 1;
    }
    return 0;
  }

  /**
   * 修改管理员信息
   * @param id 管理员 id
   * @param info 与修改相关的信息
   * @return 被修改的管理员 id
   */
  int updateInfo(int id, Map<String, String> info) {
    Admin admin = new Admin();
    admin.setId(id);
    admin.setUsername(info.get("username"));
    admin.setName(info.get("name"));
    admin.setPhone(info.get("phone"));
    admin.setEmail(info.get("email"));
    if (adminDao.selectAdminById(id).getUsername().equals(admin.getUsername())) {
      admin.setUsername(null);
    }
    adminDao.updateAdmin(admin);
    return admin.getId();
  }

  Admin getAdminById(Integer id) {
    return adminDao.selectAdminById(id);
  }

  Admin getAdminByUsername(String username) {
    return adminDao.selectAdminByUsername(username);
  }

  int updatePassword(Integer id, Map<String, String> passwords) {
    Admin admin = adminDao.selectAdminById(id);
    String originPassword = passwords.get("origin_password");
    String newPassword = passwords.get("new_password");
    if (admin == null) {
      return -1;
    }
    if (newPassword != null && admin.getPassword().equals(originPassword)) {
      admin.setPassword(newPassword);
      return adminDao.updateAdmin(admin);
    }
    return 0;
  }

}
