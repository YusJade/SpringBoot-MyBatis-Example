package com.yuxeng.display.adminmodel;

import com.yuxeng.display.bookmodel.Book;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminDao {
  List<Admin> findAdmin(Admin condition);

  Admin findAdminById(int id);
  List<Book> findBookByKeyword(String keyword);

  int updateAdmin(Admin admin);

  int deleteBookById(int id);

  int insertBook(Book book);

  int updateBook(Book book);
}
