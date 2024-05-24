package com.yuxeng.display.adminmodel;

import com.yuxeng.display.bookmodel.Book;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  @Resource
  private AdminDao adminDao;

  Integer updateBasicInfo(Admin admin) {
    adminDao.updateAdmin(admin);
    return admin.getId();
  }

  Admin getAdminById(Integer id) {
    return adminDao.findAdminById(id);
  }

  List<Book> getAdminByKeyword(String keyword) {
    if (keyword != null) {
      return adminDao.findBookByKeyword(keyword);
    }
    return adminDao.findBookByKeyword("");
  }

  Admin getAdminByUsername(String username) {
    Admin conditions = new Admin();
    conditions.setUsername(username);
    List<Admin> res = adminDao.findAdmin(conditions);
    if (res.isEmpty()) {
      return null;
    }
    return res.getFirst();
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

  boolean updatePassword(Integer id, String initPassword, String newPassword) {
    Admin admin = adminDao.findAdminById(id);
    if (admin != null && admin.getPassword().equals(initPassword)) {
      admin.setPassword(newPassword);
      int rowAffected = adminDao.updateAdmin(admin);
      return rowAffected == 1;
    }
    return false;
  }

}
