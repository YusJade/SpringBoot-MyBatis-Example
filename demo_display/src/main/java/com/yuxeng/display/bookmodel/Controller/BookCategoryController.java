package com.yuxeng.display.bookmodel.Controller;

import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import com.yuxeng.display.bookmodel.Service.BookCategoryService;
import com.yuxeng.display.util.ResponseCode;
import com.yuxeng.display.util.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/book-category")
public class BookCategoryController {

  @Autowired
  private BookCategoryService bookCategoryService;

  @GetMapping
  public Responses<ArrayList<BookCategory>> queryCategories() {
    ArrayList<BookCategory> categories = bookCategoryService.listCategory();
    if (!categories.isEmpty()) {
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
  public Responses<String> updateCategory(@PathVariable long id, @RequestBody BookCategory category) {
    category.setId(id);
    bookCategoryService.updateBookCategory(category);
    return new Responses<>(ResponseCode.SUCCESS, "分类信息更新成功", null);
  }

  @DeleteMapping("/{id}")
  public Responses<String> deleteCategory(@PathVariable Integer id) {
    bookCategoryService.removeBookCategory(id);
    return new Responses<>(ResponseCode.SUCCESS, "分类删除成功", null);
  }
}
