package com.yuxeng.display.borrowmodel;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BorrowDao {

  Borrow selectBorrowById(int id);

  /**
   * 根据条件查询借阅记录
   *
   * @param map 条件包括借阅记录的属性
   * @param isContainFinished 是否包含已完成的借阅记录
   * @return 符合条件的结果
   */
  List<Borrow> selectBorrow(@Param("map") Map<String, Object> map,
      @Param("isContainFinished") boolean isContainFinished);

  void insertBorrow(Borrow borrow);

  int updateBorrowById(Map<String, Object> map);

  int countBorrow(@Param("map") Map<String, Object> map,
      @Param("isContainFinished") boolean isContainFinished);
}
