package com.yuxeng.display.BookTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.yuxeng.display.bookmodel.Dao.BookCategoryDao;
import com.yuxeng.display.bookmodel.Pojo.BookCategory;
import com.yuxeng.display.bookmodel.Service.impl.BookCategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class BookCategoryServiceTest {

  @Mock
  private BookCategoryDao bookCategoryDao;

  @InjectMocks
  private BookCategoryServiceImpl bookCategoryService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testListCategory() {
    ArrayList<BookCategory> categories = new ArrayList<>();
    when(bookCategoryDao.selectCategory()).thenReturn(categories);

    List<BookCategory> result = bookCategoryService.listCategory();

    assertNotNull(result);
    assertEquals(categories, result);
  }

  @Test
  void testUpdateBookCategory() {
    BookCategory category = new BookCategory();
    doNothing().when(bookCategoryDao).updateBookCategory(category);

    bookCategoryService.updateBookCategory(category);

    verify(bookCategoryDao, times(1)).updateBookCategory(category);
  }

  @Test
  void testRemoveBookCategory() {
    doNothing().when(bookCategoryDao).deleteBookCategory(1);

    bookCategoryService.removeBookCategory(1);

    verify(bookCategoryDao, times(1)).deleteBookCategory(1);
  }

  @Test
  void testSaveBookCategory() {
    doNothing().when(bookCategoryDao).insertBookCategory("Test Category");

    bookCategoryService.saveBookCategory("Test Category");

    verify(bookCategoryDao, times(1)).insertBookCategory("Test Category");
  }
}
