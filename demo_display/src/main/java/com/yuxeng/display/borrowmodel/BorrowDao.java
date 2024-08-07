package com.yuxeng.display.borrowmodel;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BorrowDao {

  Borrow selectBorrowById(int id);
  List<Borrow> selectBorrow(Map<String, Object> map, boolean isContainFinished);
  void insertBorrow(Borrow borrow);
  int updateBorrowById(Map<String, Object> map);
  int countBorrow(Map<String, Object> map, boolean isContainFinished);
}
