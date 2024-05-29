package com.yuxeng.display.bookmodel.Controller;

import com.yuxeng.display.bookmodel.Pojo.Book;
import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import com.yuxeng.display.bookmodel.Service.BookService;
import com.yuxeng.display.util.PageBean;
import com.yuxeng.display.util.ResponseCode;
import com.yuxeng.display.util.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

  @Autowired
  private BookService bookService;

  @GetMapping
  // TODO： 没有书籍和关键词匹配的情况
  public Responses<PageBean<Book>> queryBook(@RequestParam List<String> paramMap) {
    return new Responses<>(ResponseCode.SUCCESS,"书籍查询成功",
        bookService.listBooksByPage(paramMap));
  }


  @PostMapping
  public Responses<Book> addBook(@RequestBody Book book) {
    // 先根据图书id查看图书是否存在
    Book existingBook = bookService.getBookById(book.getId());
    if (existingBook != null) {
      // 图书已存在，则增加数量
      int newQuantity = existingBook.getQuantity() + book.getQuantity();
      existingBook.setQuantity(newQuantity);
        return new Responses<>(ResponseCode.SUCCESS, "图书数量增加成功", existingBook);
    } else {
      // TODO 固定输入的信息
      // 图书不存在，依次输入图书信息并保存
      return new Responses<>(ResponseCode.SUCCESS, "图书添加成功", book);
    }
  }


  @GetMapping("/{id}")
  public Responses<Book> getBook(@PathVariable int id) {
    // 根据图书ID获取图书详情
    Book book = bookService.getBookById(id);
    if (book != null) {
      return new Responses<>(ResponseCode.SUCCESS, "图书查询成功", book);
    } else {
      return new Responses<>(ResponseCode.BOOK_NOT_EXIST, "图书不存在", null);
    }
  }

  @PutMapping("/{id}")
  public Responses<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
    // 更新图书信息
    book.setId(id);
    bookService.updateBook(book);
    return new Responses<>(ResponseCode.SUCCESS, "图书信息更新成功", book);
  }

  @DeleteMapping("/{id}")
  public Responses<String> deleteBook(@PathVariable int id) {
    // 删除图书
    bookService.removeBook(id);
    return new Responses<>(ResponseCode.SUCCESS, "图书删除成功", null);
  }

  @GetMapping("/recommend")
  public Responses<List<Book>> getRecommendedBooks() {
    // TODO：获取推荐图书，实现逻辑在BookService中
    return null; // 占位符
  }
}
