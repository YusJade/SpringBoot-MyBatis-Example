package com.yuxeng.display.adminmodel;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {

  /**
   *
   * @param condition 筛选条件，属性为 null 则忽略
   * @return 符合条件的管理员信息
   */
  List<Admin> selectAdmin(Admin condition);
  Admin selectAdminById(int id);
  Admin selectAdminByUsername(String username);
  int updateAdmin(Map<String, Object> content);
}
