package com.yuxeng.display.adminmodel;

import jakarta.annotation.Resource;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/admin")
@RestController
public class AdminController {
  @Resource
  AdminDao adminDao;

  @PostMapping("/login")
  public Admin login(@RequestBody Admin loginAdmin) {
    List<Admin> queryList = adminDao.findAdmin(loginAdmin);
    if (!queryList.isEmpty()) {
      return  queryList.get(0);
    }
    return new Admin();
  }
}
