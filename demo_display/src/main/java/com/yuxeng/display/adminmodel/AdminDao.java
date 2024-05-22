package com.yuxeng.display.adminmodel;

import com.yuxeng.display.bookmodel.Book;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
  List<Admin> findAdmin(Admin condition);

  List<Book> findBookByKeyword(String keyword);
}
