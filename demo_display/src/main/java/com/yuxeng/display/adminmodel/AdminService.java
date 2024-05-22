package com.yuxeng.display.adminmodel;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  @Resource
  AdminDao adminDao;

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
