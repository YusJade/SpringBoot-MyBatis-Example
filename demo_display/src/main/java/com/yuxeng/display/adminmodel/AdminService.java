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
}
