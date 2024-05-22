package com.yuxeng.display.adminmodel;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {

//  List<Admin> getAllAdmin();

//  List<Admin> findAdminById(Integer id);

  List<Admin> findAdmin(Admin condition);
}
