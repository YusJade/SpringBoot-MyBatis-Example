package com.yuxeng.display.bookmodel.Controller;

import com.yuxeng.display.bookmodel.Pojo.Book;
import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import com.yuxeng.display.bookmodel.Service.BookCategoryService;
import com.yuxeng.display.util.PageBean;
import com.yuxeng.display.util.ResponseCode;
import com.yuxeng.display.util.Responses;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/book-category")
public class BookCategoryController {

  @Autowired
  private BookCategoryService bookCategoryService;

  @GetMapping
  public Responses<PageBean<BookCategory>> queryCategories(
      @RequestParam (value = "start_page",defaultValue = "0")int startPage,
      @RequestParam (value = "page_size",defaultValue = "10")int pageSize
  ) {
    PageBean<BookCategory> categories = bookCategoryService.listCategoryByPage(startPage,pageSize);
    if (categories != null) {
      return new Responses<>(ResponseCode.SUCCESS, "分类列表获取成功", categories);
    } else {
      return new Responses<>(ResponseCode.CATEGORY_NOT_EXIST, "分类列表为空", null);
    }
  }

  @PostMapping
  public Responses<String> addCategory(@RequestParam String categoryName) {
    bookCategoryService.saveBookCategory(categoryName);
    return new Responses<>(ResponseCode.SUCCESS, "分类添加成功", categoryName);
  }

  @PutMapping("/{id}")
  public Responses<BookCategory> updateCategory(@PathVariable int id, @RequestParam String categoryName) {
    bookCategoryService.updateBookCategory(id,categoryName);
    BookCategory bookCategory = bookCategoryService.getCategoryById(id);
    return new Responses<>(ResponseCode.SUCCESS, "分类信息更新成功", bookCategory);
  }

  @DeleteMapping("/{id}")
  public Responses<String> deleteCategory(@PathVariable int id) {
    bookCategoryService.removeBookCategory(id);
    return new Responses<>(ResponseCode.SUCCESS, "分类删除成功", null);
  }
}
