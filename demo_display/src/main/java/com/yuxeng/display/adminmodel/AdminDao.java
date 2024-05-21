package com.yuxeng.display.adminmodel;

import java.util.List;

public interface AdminDao {

  List<Admin> getAllAdmin();

  List<Admin> findAdminById(Integer id);

  List<Admin> findAdmin(Admin condition);
}
