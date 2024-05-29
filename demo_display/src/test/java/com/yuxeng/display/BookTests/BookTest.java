package com.yuxeng.display.BookTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.yuxeng.display.bookmodel.Dao.BookDao;
import com.yuxeng.display.bookmodel.Pojo.Book;
import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import com.yuxeng.display.bookmodel.Service.impl.BookServiceImpl;
import com.yuxeng.display.util.PageBean;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class BookTest {

  @Mock
  private BookDao bookDao;

  @InjectMocks
  private BookServiceImpl bookService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testListBooksByPage() {
    // 准备测试参数
    List<String> params = new ArrayList<>();
    params.add("1"); // pageOn
    params.add("10"); // pageSize

    // 准备模拟数据
    List<Book> books = Arrays.asList(new Book(), new Book());
    when(bookDao.selectBook(any())).thenReturn(books);
    when(bookDao.selectBookCount(any())).thenReturn(2);

    // 调用被测试的方法
    PageBean<Book> result = bookService.listBooksByPage(params);

    // 验证结果
    assertNotNull(result);
    assertEquals(2, result.getTotalSize());
    assertEquals(books, result.getDatas());
  }


  @Test
  void testListCategory() {
    List<BookCategory> categories = Arrays.asList(new BookCategory(), new BookCategory());
    when(bookDao.selectBookByCategory()).thenReturn(categories);

    List<BookCategory> result = bookService.listCategory();

    assertNotNull(result);
    assertEquals(categories, result);
  }

  @Test
  void testSaveBook() {
    Book book = new Book();
    doNothing().when(bookDao).insertBook(book);

    bookService.saveBook(book);

    verify(bookDao, times(1)).insertBook(book);
  }

  @Test
  void testGetBookById() {
    Book book = new Book();
    when(bookDao.selectById(1)).thenReturn(book);

    Book result = bookService.getBookById(1);

    assertNotNull(result);
    assertEquals(book, result);
  }

  @Test
  void testUpdateBook() {
    Book book = new Book();
    doNothing().when(bookDao).updateBook(book);

    bookService.updateBook(book);

    verify(bookDao, times(1)).updateBook(book);
  }

  @Test
  void testRemoveBook() {
    doNothing().when(bookDao).deleteBook(1);

    bookService.removeBook(1);

    verify(bookDao, times(1)).deleteBook(1);
  }
}
