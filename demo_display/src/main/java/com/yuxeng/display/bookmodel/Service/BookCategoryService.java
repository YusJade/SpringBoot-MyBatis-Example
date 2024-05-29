package com.yuxeng.display.bookmodel.Service;

import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import com.yuxeng.display.util.PageBean;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于管理图书类别的服务接口。
 */
public interface BookCategoryService {
  /**
   * 获取所有图书类别的列表。
   *
   * @return 图书类别的列表。
   */
  PageBean<BookCategory> listCategoryByPage(int startIndex,int pageSize);

  BookCategory getCategoryById(int categoryId);

  BookCategory getCategoryByName(String categoryName);

  /**
   * 更新图书类别信息。
   *
   * @param categoryName 修改的新类别名字
   * @param id 要更新的图书类别对象。
   */
  void updateBookCategory(int id,String categoryName);

  /**
   * 移除指定ID的图书类别。
   *
   * @param categoryId 要移除的图书类别ID。
   */
  void removeBookCategory(int categoryId);

  /**
   * 保存新的图书类别。
   *
   * @param categoryName 要保存的图书类别名称。
   */
  void saveBookCategory(String categoryName);

}
