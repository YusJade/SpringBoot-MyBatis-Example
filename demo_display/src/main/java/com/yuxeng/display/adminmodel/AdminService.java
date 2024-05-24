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
   * @param loginInfo 用户名和密码
   * @return -1：用户不存在；0：密码错误；1：密码正确
   */
  int login(Map<String, String> loginInfo) {
    String username = loginInfo.get("username");
    String password = loginInfo.get("password");
    if (username == null)
      return -1;

    Admin admin = adminDao.selectAdminByUsername(username);
    if (admin == null)
      return -1;
    if (admin.getPassword().equals(password)) {
      return 1;
    }
    return 0;
  }

  int updateInfo(int idUpdated, Map<String, String> info) {
    Admin admin = new Admin();
    admin.setId(idUpdated);
    admin.setName(info.get("name"));
    admin.setPhone(info.get("phone"));
    admin.setEmail(info.get("email"));
    adminDao.updateAdmin(admin);
    return admin.getId();
  }

  Admin getAdminById(Integer id) {
    return adminDao.selectAdminById(id);
  }

  Admin getAdminByUsername(String username) {
    return adminDao.selectAdminByUsername(username);
  }

  List<Book> getAdminByKeyword(String keyword) {
    if (keyword != null) {
      return adminDao.findBookByKeyword(keyword);
    }
    return adminDao.findBookByKeyword("");
  }

  int insertBook(Book book) {
    try {
      return adminDao.insertBook(book);
    } catch (Exception e) {
      System.out.println(e);
    }
    return -1;
  }

  int deleteBookById(Integer id) {
    int row = 0;
    try {
      row = adminDao.deleteBookById(id);
    } catch (Exception e) {
      System.out.println(e);
    }
    return row;
  }

  int updateBook(Book book) {
    return 0;
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
