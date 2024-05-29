package com.yuxeng.display.bookmodel.Controller;

import com.yuxeng.display.bookmodel.Pojo.Book;
import com.yuxeng.display.bookmodel.Service.BookService;
import com.yuxeng.display.usermodel.UserController;
import com.yuxeng.display.util.PageBean;
import com.yuxeng.display.util.ResponseCode;
import com.yuxeng.display.util.Responses;
<<<<<<< HEAD
=======
import java.sql.Timestamp;
>>>>>>> serendipity
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

  @Autowired
  private BookService bookService;
  @Autowired
  private UserController user;

  @GetMapping
<<<<<<< HEAD
  // TODO： 没有书籍和关键词匹配的情况
  public Responses<PageBean<Book>> queryBook(
      @RequestParam(name = "keywords") List<String> paramMap) {
    return new Responses<>(ResponseCode.SUCCESS, "书籍查询成功",
        bookService.listBooksByPage(paramMap));
=======
  public Responses<PageBean<Book>> queryBook(
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "author", required = false) String author,
      @RequestParam(value = "category_id", required = false) Integer categoryId,
      @RequestParam(value = "publisher", required = false) String publisher,
      @RequestParam(value = "start_page",  defaultValue = "1") Integer startIndex,
      @RequestParam(value = "page_size", defaultValue = "10") Integer pageSize) {
      PageBean<Book> books = bookService.listBooksByPage(title,author,categoryId,publisher,startIndex,pageSize);
    if (books != null) {
      return new Responses<>(ResponseCode.SUCCESS, "分类列表获取成功", books);
    } else {
      return new Responses<>(ResponseCode.CATEGORY_NOT_EXIST, "分类列表为空", null);
    }

>>>>>>> serendipity
  }

  @PostMapping
<<<<<<< HEAD
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
=======
  public Responses<Integer> addBook(@RequestBody Map<String, Object> bookMap) {
    int bookId = bookService.saveBook(bookMap);
    return new Responses<>(ResponseCode.SUCCESS, "图书添加成功",bookId);
>>>>>>> serendipity
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
<<<<<<< HEAD
    int res = bookService.updateBook(id, book);
    return switch (res) {
      case -1 -> new Responses<>(ResponseCode.BOOK_NOT_EXIST, "图书不存在", new Book());
      case 1 ->
          new Responses<>(ResponseCode.SUCCESS, "图书信息更新成功", bookService.getBookById(id));
      default -> new Responses<>(ResponseCode.FAILED, "系统错误", new Book());
    };
=======
    // 更新图书信息
    book.setId(id);
    bookService.updateBook(book);
    book.setCreated_at(new Timestamp(System.currentTimeMillis()));
    return new Responses<>(ResponseCode.SUCCESS, "图书信息更新成功", book);
>>>>>>> serendipity
  }

  @DeleteMapping("/{id}")
  public Responses<String> deleteBook(@PathVariable int id) {
    // 删除图书
    bookService.removeBook(id);
    return new Responses<>(ResponseCode.SUCCESS, "图书删除成功", null);
  }

  @GetMapping("/recommends")
  public Responses<PageBean<Book>> getRecommendedBooks(
      @RequestParam (value = "user_id")Integer userId,
      @RequestParam (value = "start_page",defaultValue = "1")Integer startPage,
      @RequestParam (value = "page_size",defaultValue = "10" ) Integer pageSize
      ) {
    PageBean<Book> listRecommendBook = bookService.recommendBook(userId,startPage,pageSize);
    if(listRecommendBook.getTotalSize() == 0){
      return new Responses<>(ResponseCode.BORROW_NOT_EXIST,"还未借阅书籍，无法推荐",null);
    }else{
      return new Responses<>(ResponseCode.SUCCESS,"推荐书籍成功",listRecommendBook);
    }
  }
}
