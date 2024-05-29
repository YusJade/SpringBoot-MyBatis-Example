package com.yuxeng.display.bookmodel.Service;

import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import java.util.ArrayList;

/**
 * 用于管理图书类别的服务接口。
 */
public interface BookCategoryService {
  /**
   * 获取所有图书类别的列表。
   *
   * @return 图书类别的列表。
   */
  ArrayList<BookCategory> listCategory();

  /**
   * 更新图书类别信息。
   *
   * @param category 要更新的图书类别对象。
   */
  void updateBookCategory(BookCategory category);

  /**
   * 移除指定ID的图书类别。
   *
   * @param categoryId 要移除的图书类别ID。
   */
  void removeBookCategory(Integer categoryId);

  /**
   * 保存新的图书类别。
   *
   * @param categoryName 要保存的图书类别名称。
   */
  void saveBookCategory(String categoryName);
}
